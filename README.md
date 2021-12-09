# Excel支持大量图片导出

## 背景

用户在导出统计数据时需要导出大量图片.目前用的比较多的poi导出能支持批量导出大量数据(不包括自媒体).但是当需要导出大量图片时,即使设置了flushSize ,但是对于图片对象却没有效果,图片在内存中无法释放,写的图片越多,占用内存越大,导致频繁GC,甚至OOM

### 思路

excel文件由声明,表数据,单元格数据,媒体文件等等组件组成,
这些组件分别对应了不同的数据单元.只要把数据分别写入对应的组件,最后构建成一个需要的excel文件.

## 功能

采用流式方法写入文件，不会导致内存堆积而占用太多系统资源，有效避免频繁GC问题

1. 支持自动合并单元格
2. 使用流式处理,支持大量图片导出
3. 支持注解导出,在实体上添加注解,自动生成标题

### 测试

经测试，可以生成几个G的文件。（保证生成的文件没问题，文件是否能打开由使用者计算机决定）

## 快速使用

1. ### Maven导入

   在项目的pom.xml的dependencies中加入以下内容:

```xml
<dependency>
    <groupId>top.minwk</groupId>
    <artifactId>excel-x</artifactId>
    <version>1.0.4</version>
</dependency>
```

2. ### 示例

   - [excel含图片导出demo地址](https://gitee.com/mwk719/excel-batch-picture-support/tree/dev/src/test/java/com/ibiz/excel/picture/support/example)
   - [微云-6767张图片共800mb资源.rar 可用于测试](https://minwk.top/big-size-img/) 
   - [项目中导出下载excel使用示例](https://gitee.com/mwk719/spring-learn/blob/master/src/main/java/com/mwk/external/controller/ExcelController.java)

3. ### 版本更迭

   #### 1.0.4(2021.12.08)

   - 添加使用注解导出含图片或文本的使用示例
   - 修复图片遮挡所在单元格边框线
   - 修复f使用注解导出图片所在下边框不是加粗实线

   #### 1.0.3(2021.02.26)

   - 简化使用示例
   - 修复flushSize = -1 时不刷新流
   - 修复其他未知问题

   #### 1.0.2(2021.01.26)

   -  修复MD5时未关闭流

   #### 1.0.1(2021.01.23)

   - 添加合并单元列值
   - 添加设置单元格背景色
   - 添加可自定义单元格宽度
   - 添加设置字体，目前有默认字体
   - 修复office打开提示需修复的问题

## 组件介绍

EXCEL由几大组件构建而成

### 具体部分：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200715114523625.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3ljaGFuZzU3Nw==,size_16,color_FFFFFF,t_70)

### 工作文件实例

1. workbook代表一个excel工作文件
2. Sheet对应文件中多个sheet页
3. Row 为sheet页中的行数据，包含多个Cell单元格
4. Cell 具体单元格数据
5. MergeCell 合并单元格信息，指定了合并起始行和结束行，起始列和结束列
6. Picture 为图片信息，一个Sheet有n个Picture

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200715114920961.png)

**注解**
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200715114042664.png)

需要导出的字段使用ExportModel注解，程序会自动识别标题，合并单元格，插入图片

```java

/**
 * 导出模型
 * 使用该注解程序会根据相应属性对单元格做设置
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ExportModel {
    /**排序*/
    int sort() default 0;
    /**是否是图片*/
    boolean isPicture() default false;
    /**表头*/
    String title() default "";
    /**已这列为准进行合并列*/
    boolean mergeMaster() default false;
    /**这一列是否要合并*/
    boolean merge() default false;
}
```
创建一个工作文件，需要经过一个工作周期，分别对应init(初始化) ， write(写数据)， close(关闭)
> init 初始化各个组件，构建流并写入组件头部信息
> write 写入内容
> close 写入剩余内存中的数据到流， 写入组件尾部信息，关闭工作文件

init, write, close分别对应了三种事件，程序使用事件驱动机制，当监听器监听到组件在执行对应注册事件时分别进行相应操作

**事件**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200715122312288.png)

**监听器**

![在这里插入图片描述](https://img-blog.csdnimg.cn/20200715122604342.png)

部分代码

```java
public interface WorkbookEvent<E extends ContentListener> {
    WorkbookEvent registry(E listener);
    void onEvent(Sheet sheet);
}
```

```java
public class InitListener extends AbstractContentListener {
    @Override
    public void invoke(Sheet sheet) {
        repositories.forEach(r -> r.write(sheet));
    }
}
```
触发init事件，最后一行

```java
void init() {
            sheetContext = SheetContext.getInstance(Sheet.this);
            ContentListener init = ListenerFactory.getInstance(InitListener.class);
            ContentListener flush = ListenerFactory.getInstance(FlushListener.class);
            ContentListener close = ListenerFactory.getInstance(CloseListener.class);
            sheetContext.getRepositoryHolder().forEach((alias, rep) -> {
                if (!closeAlias.contains(alias)) {
                    //app.xml workbook.xml最后写,考虑getSheetName
                    init.addRepository(rep);
                    flush.addRepository(rep);
                }
                close.addRepository(rep);
            });
            sheetContext.getEvents().add(EventFactory.getInstance(InitEvent.class).registry(init));
            sheetContext.getEvents().add(EventFactory.getInstance(FlushEvent.class).registry(flush));
            sheetContext.getEvents().add(EventFactory.getInstance(CloseEvent.class).registry(close));
            //创建组件文件
            sheetContext.getEvents().stream().filter(e -> e instanceof InitEvent).forEach(e -> e.onEvent(Sheet.this));
        }
```

Write方法很简单，只是把内存中的缓存写入流。

```java
public void write(Sheet sheet) {
        try {
            if (null == write) {
                return;
            }
            writeToStream();
        } catch (Exception e) {
            throw new RuntimeException("写文件异常 :" + file.getAbsolutePath(), e);
        }
    }

    private void writeToStream() {
        try {
            if (!hasWriteXmlHead && writeXmlHead) {
                write.write(AutoXmlHeadEndContent.XML_HEAD);
                hasWriteXmlHead = true;
            }
            write.write(content.toString());
            clearContent();
        } catch (IOException e) {
            throw new RuntimeException("写文件异常 :" + file.getAbsolutePath(), e);
        }
    }
```
在写入流之前，需要把写入的数据先写入缓存中，这里使用代理。部分代码：

```java
public class Sheet1Handler implements InvocationHandler {
    private IRepository target;
    public Sheet1Handler(IRepository proxy) {
        this.target = proxy;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Sheet sheet = (Sheet)args[0];
        if (method.getName().equals("write")) {
            List<Row> rows = sheet.getRows();
            if (!rows.isEmpty()) {
                //未刷新过说明没有写入过流,这里主要为了写表头
                //如果写过了,则从脚标1开始,原因是为了对比合并单元格在row1中保存上一次刷新的最后一条数据
                int subIndex = !sheet.hasFlush() ? 0 : 1;
                setMergeCell(sheet, rows);
                rows.subList(subIndex, rows.size()).stream().forEach(r -> 	          writeSheetXML(r));
            }
        } else if (method.getName().equals("close")) {
            setEndSheetData();
            setMergeContent(sheet);
        }
        return method.invoke(target, args);
    }
}
```

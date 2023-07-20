# Excel支持大量图片导出
[![Build Status](https://travis-ci.com/mwk719/excel-batch-picture-support.svg?branch=master)](https://app.travis-ci.com/github/mwk719/excel-batch-picture-support)
[![codecov](https://codecov.io/gh/mwk719/excel-batch-picture-support/branch/master/graph/badge.svg?token=40375Bb4s5)](https://codecov.io/gh/mwk719/excel-batch-picture-support)
<a target="_blank" href="https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html">
<img src="https://img.shields.io/badge/JDK-8+-green.svg" /></a>
<a target="_blank" href="https://search.maven.org/artifact/top.minwk/excel-x">
<img src="https://img.shields.io/maven-central/v/top.minwk/excel-x.svg?label=Maven%20Central" /></a>

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

## 问题反馈

- [Gitee issue](https://gitee.com/mwk719/excel-batch-picture-support/issues)

## 快速使用

### Maven导入

在项目的pom.xml的dependencies中加入以下内容: 点击查看[最新版本 ${excel-x.version}](https://search.maven.org/artifact/top.minwk/excel-x)

```xml
<dependency>
    <groupId>top.minwk</groupId>
    <artifactId>excel-x</artifactId>
    <version>${excel-x.version}</version>
</dependency>
```

### 示例

*[具体功能使用示例可见版本更迭](https://gitee.com/mwk719/excel-batch-picture-support#%E7%89%88%E6%9C%AC%E6%9B%B4%E8%BF%AD)*

- #### 注解使用示例代码 

  ```java
  @GetMapping("/export/lastversion/{row}")
  public void exportLastVersion(HttpServletResponse response, @PathVariable int row) throws IOException {
       /*
       操作窗口
       当写入excel数据行数大于flushSize时{@link Sheet.SheetHandler#createRow(int)},
       会刷新数据到流,调用该方法
       {@link  com.ibiz.excel.picture.support.flush.DrawingXmlRelsHandler#copyPictureAppendDrawingRelsXML(Sheet, Picture)}
       将图片刷新在磁盘中
       不会占用内存空间
       flushSize = -1 时不刷新流
       */
       Workbook workBook = Workbook.getInstance(1);
       Sheet sheet = workBook.createSheet("测试");
       // 给标题行加上背景色，加颜色时，会对字体加粗
       sheet.addCellStyle(new CellStyle(0, "66cc66"));
       List<UserPicture> list = new ArrayList<>();
       UserPicture userPicture;
       for (int r = 0; r < row; r++) {
           userPicture = new UserPicture();
           userPicture.setAge(15);
           userPicture.setName("测试-" + r);
           // 导出本地单张图片
           userPicture.setPicture("E:\\test\\img\\1.jpg");
           // 导出url单张图片
           userPicture.setHeaderPicture("https://portrait.gitee.com/uploads/avatars/user/552/1657608_mwk719_1641537497.png");
           // 导出本地图片集合
           userPicture.setPictures(Arrays.asList("E:\\test\\img\\1.jpg","E:\\test\\img\\2.jpg"));
           // 导出url图片集合
           userPicture.setUrlPictures(Arrays.asList("https://portrait.gitee.com/uploads/avatars/user/552/1657608_mwk719_1641537497.png",
                   "https://img2.baidu.com/it/u=2602880481,728201544&fm=26&fmt=auto"));
           list.add(userPicture);
       }
       sheet.write(UserPicture.class).createRow(list);
       WebUtil.writeExcel(workBook, "最新使用示例代码导出".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), response);
   }
  ```

  ```java
  /**
   * @auther 喻场
   * @date 2020/7/813:41
   */
  public class UserPicture {
  
      public UserPicture() {
      }
  
      @ExportModel( sort = 0, title = "姓名")
      private String name;
      @ExportModel(sort = 1, title = "年龄")
      private Integer age;
      @ExportModel(sort = 3, title = "部门")
      private String department;
      @ExportModel(sort = 2, isPicture = true, title = "图片1")
      private String picture;
      @ExportModel(sort = 4, isPicture = true, title = "图片2")
      private String headerPicture;
      @ExportModel(sort = 5, isPicture = true, title = "多图片")
      private List<String> pictures;
      @ExportModel(sort = 6, isPicture = true, title = "url多图片")
      private List<String> urlPictures;
  
      public UserPicture(String name, Integer age, String department, String picture) {
          this.name = name;
          this.age = age;
          this.department = department;
          this.picture = picture;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public Integer getAge() {
          return age;
      }
  
      public void setAge(Integer age) {
          this.age = age;
      }
  
      public String getDepartment() {
          return department;
      }
  
      public void setDepartment(String department) {
          this.department = department;
      }
  
      public String getPicture() {
          return picture;
      }
  
      public void setPicture(String picture) {
          this.picture = picture;
      }
  
      public String getHeaderPicture() {
          return headerPicture;
      }
  
      public void setHeaderPicture(String headerPicture) {
          this.headerPicture = headerPicture;
      }
  
      public List<String> getPictures() {
          return pictures;
      }
  
      public void setPictures(List<String> pictures) {
          this.pictures = pictures;
      }
  
      public List<String> getUrlPictures() {
          return urlPictures;
      }
  
      public void setUrlPictures(List<String> urlPictures) {
          this.urlPictures = urlPictures;
      }
  }
  
  ```

- #### 动态配置表头excel导出示例代码 

  *实体对象需要实现BizExcelPojoInterface接口*

  ```java
  @GetMapping("/export/dynamic-config-header")
      public void exportDynamicConfigHeader(HttpServletResponse response) throws IOException {
          // 模拟需要导出的数据集合
          List<Student> students = new ArrayList<>();
          students.add(new Student("李四", 16, null, null, 0));
          students.add(new Student("张三", 17, null,
                  Arrays.asList("https://portrait.gitee.com/uploads/avatars/user/552/1657608_mwk719_1641537497.png",
                  "https://img2.baidu.com/it/u=2602880481,728201544&fm=26&fmt=auto"), 1));
          students.add(new Student("王五", 15, IMG_PATH_1, null, 2));
  
          // 配置导出excel的表头、顺序、对应导出的数据集合的字段、是否是图片、单元格宽度等
          List<BizExcelRel> excels = new ArrayList<>();
          excels.add(new BizExcelRel("姓名", "name", 2));
          excels.add(new BizExcelRel("年龄", "age", 3));
          excels.add(new BizExcelRel("表现", "performance", 4));
          excels.add(new BizExcelRel("头像", "headPicture", 5, true, 20));
          excels.add(new BizExcelRel("相册", "album", 6, true));
  
          // 创建excel
          Workbook workBook = Workbook.getInstance(100);
          Sheet sheet = workBook.createSheet("测试");
          // 创建样式
          CellStyle cellStyle = new CellStyle(0, "F0F0F0");
          // 创建数据字典
          Map<String, String> performanceMap = new HashMap<>(3);
          performanceMap.put("0", "一般");
          performanceMap.put("1", "良好");
          performanceMap.put("2", "优秀");
  
          // 构建sheet
          ExcelTableProcessor.sheet(sheet)
                  // 添加样式
                  .addCellStyle(cellStyle)
                  // 添加对应属性字段的数据字典
                  .registryEnumMap("performance", performanceMap)
                  // 构建excel
                  .buildExcel(excels, students);
          WebUtil.writeExcel(workBook, "ExportExampleDynamicConfigHeader".concat(String.valueOf(System.currentTimeMillis())).concat(".xlsx"), response);
      }
  ```

  ```java
  public class Student implements BizExcelPojoInterface {
  
      public Student(String name, Integer age) {
          this.name = name;
          this.age = age;
      }
  
      public Student(String name, Integer age, String headPicture) {
          this.name = name;
          this.age = age;
          this.headPicture = headPicture;
      }
  
      public Student(String name, Integer age, String headPicture, List<String> album, Integer performance) {
          this.name = name;
          this.age = age;
          this.headPicture = headPicture;
          this.album = album;
          this.performance = performance;
      }
  
      private String name;
  
      private Integer age;
  
      private String headPicture;
  
      /**
       * 相册
       */
      private List<String> album;
  
      /**
       * 表现 0一般；1良好；2优秀
       */
      private Integer performance;
  
      public Integer getPerformance() {
          return performance;
      }
  
      public void setPerformance(Integer performance) {
          this.performance = performance;
      }
  
      public List<String> getAlbum() {
          return album;
      }
  
      public void setAlbum(List<String> album) {
          this.album = album;
      }
  
      public String getHeadPicture() {
          return headPicture;
      }
  
      public void setHeadPicture(String headPicture) {
          this.headPicture = headPicture;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public Integer getAge() {
          return age;
      }
  
      public void setAge(Integer age) {
          this.age = age;
      }
  }
  ```

- [excel含图片导出demo地址](https://gitee.com/mwk719/excel-batch-picture-support/tree/dev/src/test/java/com/ibiz/excel/picture/support/example)，具体使用以后缀最新日期为准，其他示例仅供测试

- [微云-6767张图片共800mb资源.rar 可用于测试](https://minwk.top/big-size-img/) 

- [项目中导出下载excel使用示例](https://gitee.com/mwk719/spring-learn/blob/master/src/main/java/com/mwk/external/controller/ExcelController.java)

#### 项目中测试使用

1. 设置项目jvm堆栈大小都是20m

   ```bash
   -Xms20m -Xmx20m -Dfile.encoding=UTF-8 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\log\springlearn.hprof
   ```

2. 复制上方 【最新使用示例代码】到项目中

3. 找一堆图片随机添加到UserPicture中

4. 导出一个5000条的记录，在最大堆栈占用为20m的情况下，导出excel大小为700m，未发生内存溢出情况

## 版本更迭

*点击可跳转链接可以查看功能使用示例*

### 2.4.3(2023.07.20)

- 修复单元格合并错误

#### 2.4.2(2023.07.17)

- [添加内容对齐操作(自动换行、上下左右)](https://gitee.com/mwk719/excel-batch-picture-support/blob/master/src/test/java/com/ibiz/excel/picture/support/example/ExportExample_20230713.java#L74)（[issues#I7IO5K](https://gitee.com/mwk719/excel-batch-picture-support/issues/I7IO5K)）
- 修复当添加列超过52列时出现异常

#### 2.4.1(2023.06.06)

- [添加对全局边框进行加粗、对单元格边框加粗配置](https://gitee.com/mwk719/excel-batch-picture-support/blob/dev/src/test/java/com/ibiz/excel/picture/support/example/ExportExample_20230110.java#L64)
- 修复Microsoft Excel2010打开单元格超宽问题
- 优化创建标题时控制列顺序

#### 2.4.0(2023.01.12)

- [添加可以给单个单元格设置样式](https://gitee.com/mwk719/excel-batch-picture-support/blob/dev/src/test/java/com/ibiz/excel/picture/support/example/ExportExample_20230110.java)

#### 2.3.1(2022.12.19)

- 修复渲染生成列元素A-Z坐标时，使用到Z时，下一列从AA开始
- 修复行元素为空时合并单元格错误

#### 2.3.0(2022.02.23)

- [添加可动态配置表头excel导出](https://gitee.com/mwk719/spring-learn/blob/master/src/main/java/com/mwk/external/controller/ExcelController.java#L285)

#### 2.2.2(2022.02.08)

- 修复合并后的单元格没有边框线

#### 2.2.1(2022.01.28)

- [优化对合并单元格的处理](https://gitee.com/mwk719/excel-batch-picture-support/blob/master/src/test/java/com/ibiz/excel/picture/support/example/ExportExample_20220128.java#L30)

#### 2.2.0(2022.01.27)

- [添加导出excel中字体设置](https://gitee.com/mwk719/spring-learn/blob/master/src/main/java/com/mwk/external/controller/ExcelController.java#L243)

#### 2.1.0(2022.01.14)

- [添加导出网络链接图片到excel中](https://gitee.com/mwk719/spring-learn/blob/master/src/main/java/com/mwk/external/controller/ExcelController.java#L146)
- [添加createRow集合列表生成excel方法](https://gitee.com/mwk719/spring-learn/blob/master/src/main/java/com/mwk/external/controller/ExcelController.java#L191)
- 修改CellStyle样式的使用

#### 2.0.0(2021.12.30)

- [添加用户可自定义背景色样式](https://gitee.com/mwk719/excel-batch-picture-support/blob/dev/src/test/java/com/ibiz/excel/picture/support/example/AnnotationPicturesExportExample.java)
- [添加使用注解可对图片集合进行导出](https://gitee.com/mwk719/excel-batch-picture-support/blob/dev/src/test/java/com/ibiz/excel/picture/support/example/AnnotationPicturesExportExample.java)
- 添加自定义图片的高度；图片高度和单元格高度自适应
- 修复导出图片集合变多时单元格宽度不够
- 修复导出数据行数大于100 excel打开异常
- 修复导出多组图片excel中缺失部分图片问题

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

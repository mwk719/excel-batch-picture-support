# excel-batch-picture-support
导出包含大量图片的excel
##背景
SXSSFWorkbook目前支持即时刷新缓存数据到写入流中，但是要写入大量图片时，由于图片是伴随Workbook不会即时写入。
在线上运行时遇上大量图片导出导致内存无法释放继而频繁GC,甚至OOM
##处理
自定义写入excel各个组件，最后构建为一个可使用的excel文件
由于上线时间紧急，组件暂支持一个sheet页


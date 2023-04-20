import net.lingala.zip4j.ZipFile

fun main() {

    val zipFile = ZipFile("C:\\Users\\10030648\\.gradle\\caches\\modules-2\\files-2.1\\com.android.databinding\\baseLibrary\\7.0.3\\d06d017f72515c791d9d16d8213ee1fcd5b8475c\\baseLibrary-7.0.3-2.jar")

    val fileHeader = zipFile.getFileHeader("android/databinding/Observable.class")
    if (fileHeader == null) {
        println("没找到")
    } else {
        println("找到${fileHeader.fileName}")
        zipFile.removeFile(fileHeader)
    }

}
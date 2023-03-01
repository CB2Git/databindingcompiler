import android.databinding.tool.LayoutXmlProcessor
import android.databinding.tool.util.RelativizableFile
import com.compiler.utils.LocalJavaFileWriter
import java.io.File

/**
 * xml->-layout.xml
 */
fun main(args: Array<String>) {

    val applicationPackage = "com.zzkko.si_falcon_android"
    val useAndroidX = true
    val isViewBindingEnabled = true
    val isDataBindingEnabled = true
    val inputFile =
        File("C:\\Users\\10030648\\Desktop\\shein_falcon_android\\falcon_sub_module\\src\\main\\res\\layout\\activity_sub_module.xml")
    val outputFile = File(out_data_binding_layout_info_type_remove, inputFile.name)
    val layoutInfoFiles = File(out_data_binding_layout_info_type_merge)

    val layout = LayoutXmlProcessor(
        applicationPackage, LocalJavaFileWriter("."),
        { _ -> null }, useAndroidX
    )


    val relativizableFile = RelativizableFile.fromAbsoluteFile(inputFile.canonicalFile, null)
    val removedDataBinding =
        layout.processSingleFile(relativizableFile, outputFile, isViewBindingEnabled, isDataBindingEnabled)
    if (!removedDataBinding) {
        layout.processFileWithNoDataBinding(inputFile.canonicalFile)
    }
    layout.writeLayoutInfoFiles(layoutInfoFiles)
}
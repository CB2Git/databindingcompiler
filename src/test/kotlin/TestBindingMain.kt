import android.databinding.tool.BaseDataBinder
import android.databinding.tool.store.LayoutInfoInput
import com.compiler.utils.LocalJavaFileWriter
import java.io.File

/**
 * -layout.xml  -> xxxxBinding.java
 *              -> xxxx-binding_classes.json
 */
fun main(args: Array<String>) {

    //xxxx-layout.xml文件所在的目录
    val layoutInfoDir = File(out_data_binding_layout_info_type_merge)

    //需要被合并到自己的文件中的-binding_classes.json 文件所在目录
//    val dependencyClassesFolder = File(getIn_data_binding_base_class_logs_dependency_artifacts)
    val dependencyClassesFolder = File("")
    // 从databinding复制过来的两个文件，兼容v1
    // androidx.databinding.library.baseAdapters-br.bin
    // androidx.databinding.library.baseAdapters-setter_store.json
    val v1ArtifactsFolder = File("")

    //-binding_classes.json 文件输出目录
    val classInfoBundleDir = File(out_data_binding_base_class_log_artifact)
    classInfoBundleDir.mkdirs()

    val applicationPackage = "com.zzkko.si_falcon_android"
    val useAndroidX = true
    val isViewBindingEnabled = true
    val isDataBindingEnabled = true

    val args1 = LayoutInfoInput.Args(
        outOfDate = emptyList(),
        removed = emptyList(),
        infoFolder = layoutInfoDir,
        dependencyClassesFolder = dependencyClassesFolder,
        logFolder = File(out_data_binding_base_class_log_artifact),//-binding_classes.json输出地址
        incremental = false,
        packageName = applicationPackage,
        artifactFolder = classInfoBundleDir,
        v1ArtifactsFolder = null,
        useAndroidX = useAndroidX,
        enableViewBinding = isViewBindingEnabled,
        enableDataBinding = isDataBindingEnabled
    )


    val layoutInfoInput = LayoutInfoInput(args1)

    val dataBinder = BaseDataBinder(layoutInfoInput, null)

    //xxxxBinding.java文件生成地址
    dataBinder.generateAll(LocalJavaFileWriter(out_data_binding_base_class_source_out))
}
package com.compiler

import android.databinding.tool.BaseDataBinder
import android.databinding.tool.store.LayoutInfoInput
import com.compiler.args.CompilerXmlArgs
import com.compiler.utils.LocalJavaFileWriter
import java.io.File

/**
 * -layout.xml  -> xxxxBinding.java
 *              -> xxxx-binding_classes.json
 */
class BindingCompiler {

    companion object {

        fun compiler(args: CompilerXmlArgs) {

            val outDataBindingLayoutInfoTypeMerge =
                "${args.outDir}${File.separator}data_binding_layout_info_type_merge"
            val outDataBindingBaseClassLogArtifact = "${args.outDir}${File.separator}data_binding_base_class_log_artifact"
            val outDataBindingBaseClassSourceOut = "${args.outDir}${File.separator}data_binding_base_class_source_out"

            //xxxx-layout.xml文件所在的目录
            val layoutInfoDir = File(outDataBindingLayoutInfoTypeMerge)

            //需要被合并到自己的文件中的-binding_classes.json 文件所在目录
            //val dependencyClassesFolder = File(getIn_data_binding_base_class_logs_dependency_artifacts)
            val dependencyClassesFolder = File("")
            // 从databinding复制过来的两个文件，兼容v1
            // androidx.databinding.library.baseAdapters-br.bin
            // androidx.databinding.library.baseAdapters-setter_store.json
            val v1ArtifactsFolder = File("")

            //-binding_classes.json 文件输出目录
            val classInfoBundleDir = File(outDataBindingBaseClassLogArtifact)
            classInfoBundleDir.mkdirs()

            val applicationPackage = args.applicationPackage
            val useAndroidX = args.useAndroidX
            val isViewBindingEnabled = args.isViewBindingEnabled
            val isDataBindingEnabled = args.isDataBindingEnabled

            val args1 = LayoutInfoInput.Args(
                outOfDate = emptyList(),
                removed = emptyList(),
                infoFolder = layoutInfoDir,
                dependencyClassesFolder = dependencyClassesFolder,
                logFolder = File(outDataBindingBaseClassLogArtifact),//-binding_classes.json输出地址
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
            dataBinder.generateAll(LocalJavaFileWriter(outDataBindingBaseClassSourceOut))
        }
    }
}
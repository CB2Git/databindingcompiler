package com.compiler

import android.databinding.tool.LayoutXmlProcessor
import android.databinding.tool.util.RelativizableFile
import com.compiler.args.CompilerXmlArgs
import com.compiler.utils.LocalJavaFileWriter
import java.io.File
/**
 * xxx.xml  -> xxxx-layout.xml
 *          -> remove <layout> xxx.xml
 */
class XmlCompiler {

    companion object {
        fun compiler(args: CompilerXmlArgs) {
            //布局文件转换为XXX-layout.xml位置
            val outDataBindingLayoutInfoTypeMerge =
                "${args.outDir}${File.separator}data_binding_layout_info_type_merge"
            //布局文件删除掉layout标签的位置
            val outDataBindingLayoutInfoTypeRemove =
                "${args.outDir}${File.separator}out_data_binding_layout_info_type_remove${File.separator}layout"

            val applicationPackage = args.applicationPackage
            val useAndroidX = args.useAndroidX
            val isViewBindingEnabled = args.isViewBindingEnabled
            val isDataBindingEnabled = args.isDataBindingEnabled

            val layoutInfoFiles = File(outDataBindingLayoutInfoTypeMerge)

            val layout = LayoutXmlProcessor(
                applicationPackage, LocalJavaFileWriter("."),
                { _ -> null }, useAndroidX
            )

            args.source.map { File(it) }.forEach { inputFile ->
                val relativizableFile = RelativizableFile.fromAbsoluteFile(inputFile.canonicalFile, null)
                val outputFile = File(outDataBindingLayoutInfoTypeRemove, inputFile.name)
                val removedDataBinding =
                    layout.processSingleFile(relativizableFile, outputFile, isViewBindingEnabled, isDataBindingEnabled)
                if (!removedDataBinding) {
                    layout.processFileWithNoDataBinding(inputFile.canonicalFile)
                }

            }
            layout.writeLayoutInfoFiles(layoutInfoFiles)
        }
    }
}
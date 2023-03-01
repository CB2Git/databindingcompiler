package com.compiler.main

import com.compiler.BindingCompiler
import com.compiler.DataBindingTriggerClassCompiler
import com.compiler.XmlCompiler
import com.compiler.args.CompilerXmlArgs
import org.apache.commons.io.FileUtils
import out_data_binding_base_class_log_artifact
import out_data_binding_layout_info_type_merge
import out_generated_path
import out_tmp
import java.io.File

fun main(args: Array<String>) {
    println(args.contentToString())
    if (CompilerXmlArgs.isMatchArgs(args)) {
        val parseArgs = CompilerXmlArgs.parseArgs(args)
        XmlCompiler.compiler(parseArgs)
        if (parseArgs.isDataBindingEnabled) {
            DataBindingTriggerClassCompiler.compiler(parseArgs)
        }
        BindingCompiler.compiler(parseArgs)
    }
}


fun generateCmd() {
//    -encoding utf-8
//    -source 1.8
//    -Aandroid.databinding.minApi=21
//    -Aandroid.databinding.classLogDir=C:\Users\10030648\Desktop\shein_falcon_android\app\build\intermediates\data_binding_base_class_log_artifact\sheinDebug\out
//    -Aandroid.databinding.aarOutDir=C:\Users\10030648\Desktop\out\test
//    -Aandroid.databinding.enableDebugLogs=1
//    -Aandroid.databinding.dependencyArtifactsDir=C:\Users\10030648\Desktop\shein_falcon_android\app\build\intermediates\data_binding_dependency_artifacts\sheinDebug
//    -Aandroid.databinding.sdkDir=D:\DEV\SDK
//    -Aandroid.databinding.enableForTests=0
//    -Aandroid.databinding.enableV2=1
//    -Aandroid.databinding.modulePackage=com.zzkko.si_falcon_android
//    -Aandroid.databinding.artifactType=APPLICATION
//    -Aandroid.databinding.isTestVariant=0
//    -Aandroid.databinding.incremental=0
//    -Aandroid.databinding.printEncodedErrorLogs=0
//    -Aandroid.databinding.layoutInfoDir=C:\Users\10030648\Desktop\shein_falcon_android\app\build\intermediates\data_binding_layout_info_type_merge\sheinDebug\out
    val minApi = 21
    val sdkDir = "D:\\DEV\\SDK"
    val modulePackage = "com.zzkko.si_falcon_android"
    val artifactType = "APPLICATION"//APPLICATION, LIBRARY, FEATURE
    val aarOutDir = out_tmp
    val javaCmd = """
        -encoding utf-8
        -source 1.8
        -Aandroid.databinding.minApi=$minApi
        -Aandroid.databinding.classLogDir=$out_data_binding_base_class_log_artifact
        -Aandroid.databinding.aarOutDir=$aarOutDir
        -Aandroid.databinding.enableDebugLogs=1
        -Aandroid.databinding.dependencyArtifactsDir=.
        -Aandroid.databinding.sdkDir=$sdkDir
        -Aandroid.databinding.enableForTests=0
        -Aandroid.databinding.enableV2=1
        -Aandroid.databinding.modulePackage=$modulePackage
        -Aandroid.databinding.artifactType=$artifactType
        -Aandroid.databinding.isTestVariant=0
        -Aandroid.databinding.incremental=0
        -Aandroid.databinding.printEncodedErrorLogs=0
        -Aandroid.databinding.layoutInfoDir=$out_data_binding_layout_info_type_merge
    """.trimIndent()

    println(javaCmd)

    //一行一个java文件
    val allJavaFile = FileUtils.listFiles(File(out_generated_path), arrayOf("java"), true)
    allJavaFile.forEach {
        println(it.path)
    }


}
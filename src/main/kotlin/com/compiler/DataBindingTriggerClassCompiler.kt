package com.compiler

import com.compiler.args.CompilerXmlArgs
import org.apache.commons.io.FileUtils
import java.io.File

class DataBindingTriggerClassCompiler {

    companion object {
        private const val DATA_BINDING_TRIGGER_CLASS = "DataBindingTriggerClass"
        fun compiler(args: CompilerXmlArgs) {
            if (!args.useAndroidX) {
                return
            }
            val applicationPackage = args.applicationPackage
            val annotation: Class<*> = androidx.databinding.BindingBuildInfo::class.java

            val fileContents =
                """
            package ${applicationPackage};

            @${annotation.canonicalName}
            public class $DATA_BINDING_TRIGGER_CLASS {}
            """.trimIndent()

            val outDataBindingTrigger = "${args.outDir}${File.separator}outDataBindingTrigger"

            val outputFile =
                File(
                    outDataBindingTrigger,
                    "${applicationPackage.replace('.', '/')}/$DATA_BINDING_TRIGGER_CLASS.java"
                )

            FileUtils.forceMkdir(outputFile.parentFile)
            outputFile.writeText(fileContents)
        }
    }
}
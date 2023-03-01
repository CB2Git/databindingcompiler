package com.compiler.args

class CompilerXmlArgs(
    val applicationPackage: String,
    val useAndroidX: Boolean,
    val isViewBindingEnabled: Boolean,
    val isDataBindingEnabled: Boolean,
    val source: List<String>,
    val outDir: String,
) {


    companion object {

        //"-a=xml -p=$pkgId --useAndroidX=$useAndroidX --isViewBindingEnabled=$isViewBindingEnabled --isDataBindingEnabled=$isDataBindingEnabled --input=$input -o $outDir"
        fun parseArgs(args: Array<String>): CompilerXmlArgs {
            var applicationPackage: String = ""
            var useAndroidX: Boolean = false
            var isViewBindingEnabled: Boolean = false
            var isDataBindingEnabled: Boolean = false
            var source: List<String> = arrayListOf()
            var outDir: String = ""
            args.forEach {
                val strings = it.split("=")
                when (strings[0]) {
                    "-p" -> {
                        applicationPackage = strings[1]
                    }

                    "--useAndroidX" -> {
                        useAndroidX = "True".equals(strings[1], ignoreCase = true)
                    }

                    "--isViewBindingEnabled" -> {
                        isViewBindingEnabled = "True".equals(strings[1], ignoreCase = true)
                    }

                    "--isDataBindingEnabled" -> {
                        isDataBindingEnabled = "True".equals(strings[1], ignoreCase = true)
                    }

                    "--input" -> {
                        source = strings[1].split(",").toList()
                    }

                    "-o" -> {
                        outDir = strings[1]
                    }
                }
            }
            return CompilerXmlArgs(
                applicationPackage,
                useAndroidX,
                isViewBindingEnabled,
                isDataBindingEnabled,
                source,
                outDir
            )
        }

        fun isMatchArgs(args: Array<String>): Boolean {
            args.forEach {
                val strings = it.split("=")
                if (strings.size == 2) {
                    return strings[0] == "-a" && strings[1] == "xml"
                }
            }
            return false
        }
    }

    override fun toString(): String {
        return "CompilerXmlArgs(applicationPackage='$applicationPackage', useAndroidX=$useAndroidX, isViewBindingEnabled=$isViewBindingEnabled, isDataBindingEnabled=$isDataBindingEnabled, source=$source, outDir='$outDir')"
    }
}
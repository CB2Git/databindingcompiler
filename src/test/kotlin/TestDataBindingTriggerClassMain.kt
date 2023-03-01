import org.apache.commons.io.FileUtils
import java.io.File

const val DATA_BINDING_TRIGGER_CLASS = "DataBindingTriggerClass"

/**
 * 生成DataBindingTriggerClass.java
 */
fun main() {
    val useAndroidX = true
    val applicationPackage = "com.zzkko.si_falcon_android"
    val annotation: Class<*> = androidx.databinding.BindingBuildInfo::class.java
      /*  if (useAndroidX) {
            androidx.databinding.BindingBuildInfo::class.java
        } else {
            android.databinding.BindingBuildInfo::class.java
        }*/
    val fileContents =
        """
            package ${applicationPackage};

            @${annotation.canonicalName}
            public class $DATA_BINDING_TRIGGER_CLASS {}
            """.trimIndent()

    val outputFile = File(out_data_binding_trigger,"${applicationPackage.replace('.', '/')}/$DATA_BINDING_TRIGGER_CLASS.java")

    FileUtils.forceMkdir(outputFile.parentFile)
    outputFile.writeText(fileContents)
}
import java.io.File

const val baseOutDir = "C:\\Users\\10030648\\Desktop\\out"

//布局文件生成XXX-layout.xml的中间文件位置
var out_tmp = "${baseOutDir}${File.separator}tmp"
//布局文件转换为XXX-layout.xml位置
var out_data_binding_layout_info_type_merge = "${baseOutDir}${File.separator}data_binding_layout_info_type_merge"

var out_data_binding_layout_info_type_remove = "${baseOutDir}${File.separator}out_data_binding_layout_info_type_remove"

//从Library的data_binding_base_class_log_artifact文件夹里面复制过来的Library生成的XXXX-binding_classes.json文件,会合并到自己的XXXX-binding_classes.json文件中
var in_data_binding_base_class_logs_dependency_artifacts =
    "${baseOutDir}${File.separator}data_binding_base_class_logs_dependency_artifacts"

//自身生成的XXXX-binding_classes.json文件与Library生成的XXXX-binding_classes.json文件合并的XXXX-binding_classes.json文件防止的位置
var out_data_binding_base_class_log_artifact = "${baseOutDir}${File.separator}data_binding_base_class_log_artifact"

var out_generated_path = "${baseOutDir}${File.separator}generated"

var out_data_binding_trigger = "$out_generated_path${File.separator}data_binding_trigger"

//xxxxBinding.java文件生成地址
var out_data_binding_base_class_source_out = "$out_generated_path${File.separator}data_binding_base_class_source_out"


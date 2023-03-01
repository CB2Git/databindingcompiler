package com.compiler.utils

import android.databinding.tool.util.L
import android.databinding.tool.writer.JavaFileWriter
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class LocalJavaFileWriter(private val outputBase: String) : JavaFileWriter() {
    override fun writeToFile(canonicalName: String, contents: String) {
        val f = toFile(canonicalName)
        f.parentFile.mkdirs()
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(f)
            IOUtils.write(contents, fos)
        } catch (e: IOException) {
            L.e(e, "cannot write file " + f.absolutePath)
        } finally {
            IOUtils.closeQuietly(fos)
        }
    }

    override fun deleteFile(canonicalName: String) {
        FileUtils.deleteQuietly(toFile(canonicalName));
    }

    private fun toFile(canonicalName: String): File {
        val asPath = canonicalName.replace('.', File.separatorChar)
        return File((outputBase + File.separatorChar) + asPath + ".java")
    }
}
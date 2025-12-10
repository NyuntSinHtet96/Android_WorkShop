package com.example.appspecificfiles

import android.content.Context
import android.os.Environment
import android.widget.Toast
import java.io.File
import java.io.FileOutputStream

class ExternalFileRepository(val context: Context): FileRepository {
    override fun saveNote(note: String) {
        if (isExternalStorageWritable()) {
            val file = getExternalFile(Constants.FILE_TO_WRITE)
            FileOutputStream(file).use { outputStream ->
                outputStream.write(note.toByteArray())
            }
        }
    }

    override fun retrieveNote(): String {
        if (isExternalStorageReadable()) {
            val file = getExternalFile(Constants.FILE_TO_WRITE)
            val note = file.bufferedReader().use { bufferedReader ->
                bufferedReader.readText()
            }
            return note
        }
        return " "
    }
    private fun isExternalStorageWritable(): Boolean =
        Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    private fun isExternalStorageReadable(): Boolean =
        Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED,
                    Environment.MEDIA_MOUNTED_READ_ONLY)
    private fun getExternalFile(fileName: String) : File {
        val path = context.getExternalFilesDir(null)
        return File(path, fileName)
    }
}
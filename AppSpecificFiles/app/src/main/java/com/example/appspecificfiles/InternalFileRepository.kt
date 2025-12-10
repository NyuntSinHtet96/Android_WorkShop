package com.example.appspecificfiles

import android.content.Context
import java.io.File
import java.io.FileOutputStream

class InternalFileRepository(val context: Context): FileRepository {
    override fun saveNote(note:String){
        val file = getInternalFile(Constants.FILE_TO_WRITE)
        FileOutputStream(file).use { outputStream -> outputStream.write(note.toByteArray()) }

    }
    override fun retrieveNote():String{
        val file = getInternalFile(Constants.FILE_TO_WRITE)
        val note = file.bufferedReader().use { bufferedReader -> bufferedReader.readText() }
        return note
    }
    private fun getInternalFile(fileName:String): File {
        val path = context.filesDir
        return File(path,fileName)
    }
}
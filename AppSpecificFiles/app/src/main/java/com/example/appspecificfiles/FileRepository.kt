package com.example.appspecificfiles

interface FileRepository {
    fun saveNote(note:String)
    fun retrieveNote():String
}
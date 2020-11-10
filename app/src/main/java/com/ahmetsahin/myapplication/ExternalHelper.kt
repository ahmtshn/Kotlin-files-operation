package com.ahmetsahin.myapplication

import android.content.Context
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class ExternalHelper {
    private var file: File? = null
    private var list = mutableListOf<Model>()

    // yeni bir dosya oluşturma
    fun createNewFolder(context: Context): Model {
        val name = "File-${createFolderName(file!!.lastModified())}"
        file = File(context.getExternalFilesDir(null), "$name")
        file?.mkdirs()
        return Model(file!!.name, name)
    }

    //silme fonksiyonu
    fun deleteFolder(context: Context, name: String) {
        file = File(context.getExternalFilesDir(name), "/")
    }

    //dosyaları listeler
    // "/" yerine bir dosya ismi verirseniz externalStorage içinde verdiğiniz dosyanın içeriğini listeler
    fun getFolders(context: Context): MutableList<Model> {
        return try {
            file = File(context.getExternalFilesDir(null), "/")
            file?.listFiles()?.forEach {
                list?.add(
                    Model(
                        fileName = it.name,
                        modifyTime = createFolderName(file!!.lastModified())
                    )
                )
            }
            list!!
        } catch (e: Exception) {
            println("Hata $e")
            list!!
        }
    }

    //Dosyanın adını değiştirme fonksiyonu, dosya yolunu ve yeni ismi gönderin
    fun renameFileOrFolder(path: String, context: Context, name: String) {
        val file = File(context.getExternalFilesDir(null), "$path")
        val newFile = File(context.getExternalFilesDir(null), "$name")

        file.renameTo(newFile)
    }

    //Dosya taşıma fonksiyonu. Mevcut dosya yolunu ve taşınmasını istediğiniz dosya yolunu gönderin
    fun moveFile(currentPath: String, context: Context, newPath: String) {
        val file = File(currentPath)
        val newFile = File(newPath)
        file.let {
            it.copyTo(newFile)
            it.delete()
        }
    }

    private fun createFolderName(date: Long): String {
        val name = SimpleDateFormat("ddmmyyyyss", Locale.getDefault())
        return name.format(date)
    }

}
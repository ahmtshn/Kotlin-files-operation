package com.ahmetsahin.myapplication

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private var list: MutableList<Model>? = null
private var recyclerView: RecyclerView? = null
private var adapter: Adapter? = null
private var addButton: TextView? = null
private var externalHelper: ExternalHelper = ExternalHelper()
private val permission = Permissions()
private var position: Int? = null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permission.requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        permission.requestPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)

        //İnterface ile seçilen item dinleniyor ve siliniyor
        val onclickInterface = object : Listener {
            override fun setClick(pos: Int) {
                externalHelper.deleteFolder(context = this@MainActivity, list!![pos].fileName)
                list?.removeAt(pos)
                adapter?.notifyItemRemoved(pos)
            }
        }

        list = externalHelper.getFolders(this)

        recyclerView = findViewById(R.id.recyclerview)
        addButton = findViewById(R.id.addButton)

        recyclerView?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter = Adapter(list!!, onclickInterface)
        recyclerView?.adapter = adapter


        //yeni dosya ekleniyor
        addButton!!.setOnClickListener {
            val newFolder = externalHelper.createNewFolder(this)
            list?.add(newFolder)
            adapter?.notifyItemInserted(list!!.size - 1)
        }
    }

}
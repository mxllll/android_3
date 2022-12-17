package com.example.android3

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android3.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CustomRecyclerAdapter

    private lateinit var dataList: ArrayList<Card>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var descList: Array<String>
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<Card>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CustomRecyclerAdapter(this)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerViewer.layoutManager = layoutManager

        binding.recyclerViewer.adapter = adapter
        adapter.setList(
            arrayOf(
                Card(0, "Great person", "Some clever stuff"),
                Card(1, "Other great person", "Some clever stuff"),
                Card(2, "3Some other person", "Some clever stuff"),
                Card(3, "4Some other person", "Some clever stuff"),
                Card(4, "5Some other person", "Some clever stuff"),
                Card(5, "6Some other person", "Some clever stuff"),
                Card(6, "78Some other person", "Some clever stuff"),
                Card(7, "8Some other person", "Some clever stuff"),
                Card(8, "999Some other person", "Some clever stuff"),
                Card(9, "0Some other person", "Some clever stuff"),
            )
        )

        searchView = binding.btnSearch
        dataList = arrayListOf<Card>()
        searchList = arrayListOf<Card>()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()) {
                    if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)) {
                        searchList.add(it)
                    }
                    binding.recyclerViewer.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    binding.recyclerViewer.adapter!!.notifyDataSetChanged()
                }

                return false
            }
        }
        )
    }

    private fun getData() {
        for (i in imageList.indices) {
            val dataCLass = Card(imageList[i], titleList[i], descList[i])
            dataList.add(dataCLass)
        }
        searchList.addAll(dataList)
        binding.recyclerViewer.adapter = CustomRecyclerAdapter(searchList)
    }

//        val button = binding.btnSearch
//        button.isActivated = true
//        button.queryHint = "Type your keyword here"
//        button.onActionViewExpanded()
//        button.isIconified = false
//        button.clearFocus()
//
//        button.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                adapter.getFilter()?.filter(newText)
//                return false
//            }
//        })
}
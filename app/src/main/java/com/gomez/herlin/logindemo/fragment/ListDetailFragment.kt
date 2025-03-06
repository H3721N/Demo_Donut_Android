package com.gomez.herlin.logindemo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gomez.herlin.logindemo.R
import com.gomez.herlin.logindemo.adapter.ListDetailAdapter

class ListDetailFragment(private val list: List<String>) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewListDetail)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ListDetailAdapter(list)
        return view
    }
}

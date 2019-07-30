package com.ganzory.imaginto_test

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/****   Made by Ali Elganzory 29/7/2019    ****/

class Market : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var returnView = inflater.inflate(R.layout.fragment_market, container, false)

        recyclerView = returnView.findViewById(R.id.products_recycler_view)
        viewManager = LinearLayoutManager(activity as Context)
        viewAdapter = ProductsAdapter(mutableListOf<ProductDataModel>(
            ProductDataModel(R.drawable.ride, "Lisa Simpson", "Netflix", 200f),
            ProductDataModel(R.drawable.table, "Mr. Strand", "AMC", 999f),
            ProductDataModel(R.drawable.bed, "Dooh!!!!!", "The Simpsons", 250f),
            ProductDataModel(R.drawable.clock, "Vector", "Fear TWD", 789f)
        ))

        recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        /*val newAdapter = ProductsAdapter(mutableListOf<ProductDataModel>(
            ProductDataModel(R.drawable.lis_simpson_fail, "Lisa Simpson", "Netflix", 200f),
            ProductDataModel(R.drawable.leaving_evil, "Lisa Simpson", "Netflix", 999f),
            ProductDataModel(R.drawable.lis_simpson_fail, "Lisa Simpson", "Netflix", 250f),
            ProductDataModel(R.drawable.leaving_evil, "Lisa Simpson", "Netflix", 789f)
        ))*/

        //productsRecyclerView.layoutManager = LinearLayoutManager(activity)

        //productsRecyclerView.adapter = newAdapter

        return returnView
    }

}

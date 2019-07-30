package com.ganzory.imaginto_test

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/****   Made by Ali Elganzory 29/7/2019    ****/

class Dashboard : Fragment() {

    private var userDataReference = FirebaseDatabase.getInstance().reference
    private var promos: MutableList<MutableList<String>> = arrayListOf(arrayListOf("string", "string"))

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var returnView = inflater.inflate(R.layout.fragment_market, container, false)

        val dataRef = userDataReference.child("Users").child(activeUser!!.uid).child("Promos")

        dataRef.addValueEventListener(
            object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    for (promo in p0.children){
                        promos.add(arrayListOf(promo.value.toString(), p0.key.toString()))
                    }
                }

            }
        )

        recyclerView = returnView.findViewById(R.id.products_recycler_view)
        viewManager = LinearLayoutManager(activity as Context)
        viewAdapter = PromosAdapter(promos)

        recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        return returnView
    }

}
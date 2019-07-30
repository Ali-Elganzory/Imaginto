package com.ganzory.imaginto_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class PromosAdapter (private val promosArray: MutableList<MutableList<String>>)
    : RecyclerView.Adapter<PromosAdapter.PromoHolder>() {

    var userDataReference = FirebaseDatabase.getInstance().reference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromosAdapter.PromoHolder {

        val mView = LayoutInflater.from(parent.context)
            .inflate(R.layout.promo_view, parent, false)

        return PromoHolder(mView)
    }

    override fun onBindViewHolder(holder: PromosAdapter.PromoHolder, position: Int) {

        holder.product.text = promosArray[position][0]
        holder.promo.text = promosArray[position][1]

    }

    override fun getItemCount(): Int = promosArray.size

    class PromoHolder(view: View) : RecyclerView.ViewHolder(view) {

        var product: TextView = view.findViewById(R.id.product_name)
        var promo: TextView = view.findViewById(R.id.promo_text)

    }

}
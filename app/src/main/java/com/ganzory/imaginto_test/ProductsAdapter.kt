package com.ganzory.imaginto_test


import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.product_element.view.*
import kotlinx.android.synthetic.main.promo_generated_dialog.view.*

/****   Made by Ali Elganzory 29/7/2019    ****/


class ProductsAdapter (private val products: MutableList<ProductDataModel>)
    : RecyclerView.Adapter<ProductsAdapter.ItemViewHolder>() {

    var userDataReference = FirebaseDatabase.getInstance().reference

    private lateinit var dialogView: View
    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var mParent: Context

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view){

    var pName: TextView = view.findViewById(R.id.pName)
    var tName: TextView = view.findViewById(R.id.tName)
    var priceText: TextView = view.findViewById(R.id.priceText)
    var productView: ImageView = view.findViewById(R.id.productView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ItemViewHolder {

        mParent = parent.context
        val mView = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_element, parent, false)

        return ItemViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ItemViewHolder, position: Int) {

        val product = products[position]

        holder.pName.text = product.productName
        holder.tName.text = product.traderName
        holder.priceText.text = product.price.toString()
        holder.productView.setImageResource(product.image)

        holder.itemView.setOnLongClickListener{

            userDataReference.child("Users").child(activeUser!!.uid).child("Promos").push().setValue(it.pName.text.toString())

            dialogView = LayoutInflater.from(mParent)
                .inflate(R.layout.promo_generated_dialog, null)
            dialogBuilder = AlertDialog.Builder(mParent)
            dialogBuilder.setView(dialogView).show()

            true
        }

    }

    override fun getItemCount() = products.size

}
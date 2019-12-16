package com.alan.woopsicredi.ui.mainview.adapter

import android.content.Intent
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.alan.woopsicredi.R
import com.alan.woopsicredi.models.ObjectEvent
import com.alan.woopsicredi.ui.detailsview.DetailsActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_event.view.*
import java.lang.Exception
import java.util.*


class AdapterEvents(private val list : List<ObjectEvent>) : RecyclerView.Adapter<EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val itemEvent = list[position]
        holder.bindView(itemEvent)

        holder.itemView.layoutEvent.setOnClickListener {
            val intent = Intent(it.context, DetailsActivity::class.java)
            intent.putExtra("id_event", list[position].id)
            intent.putExtra("latitude", list[position].latitude)
            intent.putExtra("longitude", list[position].longitude)
            it.context.startActivity(intent)
        }

        holder.itemView.btnShare.setOnClickListener {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Evento " + list[position].title)
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Evento: " + list[position].title + "\n\nDescrição: \n " + list[position].description)
            shareIntent.type = "text/plain"
            it.context.startActivity(shareIntent)
        }
    }
}

class EventViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    fun bindView(event : ObjectEvent){
        val title = itemView.titleEvent
        title.text = event.title

        val imgEvent = itemView.imgEvent

        Picasso.get().load(event.image).into(imgEvent, object : Callback {
            override fun onSuccess() {
                Log.i("Log load", "Sucess")
            }
            override fun onError(e: Exception?) {

                Picasso.get()
                    .load(R.drawable.default_image)
                    .into(imgEvent)
            }
        })


        val txtDate = itemView.txtDate
//        txtDate.text = convertDate(event.date)

        val txtPrice = itemView.txtPrice
//        txtPrice.text = event.price.toString()
    }


}



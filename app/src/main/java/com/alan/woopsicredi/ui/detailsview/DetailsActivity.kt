package com.alan.woopsicredi.ui.detailsview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.alan.woopsicredi.R
import com.alan.woopsicredi.models.ObjectEvent
import com.alan.woopsicredi.ui.detailsview.contract.DetailsContract
import com.alan.woopsicredi.ui.detailsview.presenter.DetailsPresenter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*


class DetailsActivity : AppCompatActivity(), OnMapReadyCallback ,DetailsContract.View {

    lateinit var presenter : DetailsPresenter
    lateinit var mMap: GoogleMap
    private var latitude = 0.0
    private var longitude = 0.0
    private lateinit var setPosition : LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val id  = intent.getStringExtra("id_event")
        latitude = intent.getDoubleExtra("latitude", 0.0)
        longitude = intent.getDoubleExtra("longitude", 0.0)

        setPosition = LatLng(latitude, longitude)

        presenter = DetailsPresenter(this)
        presenter.onLoadData(id!!)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun showLoading(enable: Boolean) {}

    override fun openDialog(events: ObjectEvent) {
        val builder = AlertDialog.Builder(this).create()
        val inflater = layoutInflater
        val dialogLayout: View = inflater.inflate(R.layout.custom_checkin_layout, null)

        val inputName = dialogLayout.findViewById<EditText>(R.id.inputName)
        val inputEmail = dialogLayout.findViewById<EditText>(R.id.inputEmail)


        val name = inputName.text
        val email = inputEmail.text
        val btn = dialogLayout.findViewById<Button>(R.id.buttonCheckin)
        btn.setOnClickListener {
            if(name.isEmpty() && email.isEmpty()){
                DynamicToast.makeWarning(this, "Preencha todos os campos!").show()
            }else{
                presenter.setCheckin(events.id, name.toString(), email.toString())
                builder.dismiss()
            }
        }

        builder.setView(dialogLayout)
        builder.show()
    }

    override fun showToastAfterCheckin(code: Int) {
        if(code == 200){
            DynamicToast.makeSuccess(this, "Check-in Efetuado").show()
        }else{
            DynamicToast.makeError(this, "Ocorreu um erro no Check-in Tente Novamente!").show()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(setPosition, 15F))
    }

    override fun renderActivity(events: ObjectEvent) {

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.title = events.title

        Picasso.get().load(events.image).into(imgDetails, object : Callback {
            override fun onSuccess() {}

            override fun onError(e: Exception?) {

                Picasso.get()
                    .load(R.drawable.default_image)
                    .into(imgDetails)
            }
        })

        mMap.addMarker(MarkerOptions().position(setPosition).title(events.title))

        txtDescribeDetails.text = events.description

        txtPriceDetails.text = events.price.toString()

        val discount = events.cupons[0].discount.toDouble().div(100)
        val apply = events.price.times(discount)

        val decimal = BigDecimal(events.price - apply).setScale(2, RoundingMode.HALF_EVEN)

        txtPriceWithDiscount.text = "R$ $decimal"

        txtDataDetails.text = "Data: " + convertDate(events.date)

        btn_checkin.setOnClickListener {
            openDialog(events)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun convertDate(time : Long) : String{
        val format = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return format.format(time)
    }
}
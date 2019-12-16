package com.alan.woopsicredi.ui.mainview

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alan.woopsicredi.R
import com.alan.woopsicredi.models.ObjectEvent
import com.alan.woopsicredi.ui.mainview.adapter.AdapterEvents
import com.alan.woopsicredi.ui.mainview.contract.MainContract
import com.alan.woopsicredi.ui.mainview.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View{

    lateinit var presenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(false)
        supportActionBar!!.title = "Eventos"

        swipeHome.setOnRefreshListener(presenter::onLoadData)
        showLoading(true)
        presenter.onLoadData()
    }

    override fun setupList(response: List<ObjectEvent>) {
        listEvent.layoutManager = LinearLayoutManager(this)
        listEvent.adapter = AdapterEvents(response)
    }

    override fun showMessageError(error: String) {

    }

    override fun showLoading(enable: Boolean) {
        if(enable){
            loadingLayout.visibility = View.VISIBLE
        }else{
            loadingLayout.visibility = View.GONE
            swipeHome.isRefreshing = false
        }
    }
}

package com.alan.woopsicredi.ui.detailsview.call

import com.alan.woopsicredi.models.ObjectEvent
import com.alan.woopsicredi.network.RetrofitInstance
import com.alan.woopsicredi.ui.detailsview.contract.DetailsContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsEventImpl : DetailsContract.Model {

    override fun getDetailsEvent(listener: DetailsContract.Listener, id : String) {
        val call = RetrofitInstance().getService().getDetailsEvent(id)
        call.enqueue(object : Callback<ObjectEvent> {
            override fun onResponse(call: Call<ObjectEvent>, response: Response<ObjectEvent>) {
                if(response.code() == 200){
                    listener.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ObjectEvent>, t: Throwable) {
                listener.onError(t.message)
            }
        })
    }

    override fun sendCheckin(id : String, name : String, email : String, listener: DetailsContract.Listener) {
        val call = RetrofitInstance().getService().setCheckin(id, name, email)
        call.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                listener.onSuccessCheckin(response.code())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {

            }
        })
    }
}
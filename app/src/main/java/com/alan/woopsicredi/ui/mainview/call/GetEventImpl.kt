package com.alan.woopsicredi.ui.mainview.call

import com.alan.woopsicredi.models.ObjectEvent
import com.alan.woopsicredi.network.RetrofitInstance
import com.alan.woopsicredi.ui.mainview.contract.MainContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetEventImpl : MainContract.Model {

    override fun getListEventServer(listener : MainContract.Listener) {
        val call = RetrofitInstance().getService().getListEvent()
        call.enqueue(object : Callback<List<ObjectEvent>> {
            override fun onResponse(call: Call<List<ObjectEvent>>, response: Response<List<ObjectEvent>>) {
                if(response.code() == 200){
                    listener.onSuccess(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<ObjectEvent>>, t: Throwable) {
                listener.onError(t.message)
            }
        })
    }
}
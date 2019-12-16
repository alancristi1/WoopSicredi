package com.alan.woopsicredi.ui.detailsview.contract

import com.alan.woopsicredi.models.ObjectEvent

interface DetailsContract{

    interface Model{
        fun getDetailsEvent(listener : Listener, id : String)
        fun sendCheckin(id : String, name : String, email : String, listener: Listener)
    }

    interface Presenter{
        fun onLoadData(id : String)
        fun setCheckin(id : String, name : String, email : String)
    }

    interface View{
        fun renderActivity(events : ObjectEvent)
        fun showLoading(enable : Boolean)
        fun openDialog(events: ObjectEvent)
        fun showToastAfterCheckin(code: Int)
    }

    interface Listener{
        fun onSuccess(response : ObjectEvent)
        fun onSuccessCheckin(code : Int)
        fun onError(errorBody: String?)
    }
}
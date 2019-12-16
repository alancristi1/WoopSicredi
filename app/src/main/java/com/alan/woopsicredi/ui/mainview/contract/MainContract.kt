package com.alan.woopsicredi.ui.mainview.contract

import com.alan.woopsicredi.models.ObjectEvent

interface MainContract {

    interface Model{
        fun getListEventServer(listener : Listener)
    }

    interface Presenter{
        fun onLoadData()
    }

    interface View{
        fun setupList(response: List<ObjectEvent>)
        fun showMessageError(error : String)
        fun showLoading(enable : Boolean)
    }

    interface Listener{
        fun onSuccess(response : List<ObjectEvent>)
        fun onError(errorBody: String?)
    }
}
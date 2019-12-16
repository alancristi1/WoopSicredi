package com.alan.woopsicredi.ui.mainview.presenter

import com.alan.woopsicredi.models.ObjectEvent
import com.alan.woopsicredi.ui.mainview.call.GetEventImpl
import com.alan.woopsicredi.ui.mainview.contract.MainContract

class MainPresenter constructor(private val view : MainContract.View) : MainContract.Presenter, MainContract.Listener{

    private var repositoryRemote : GetEventImpl = GetEventImpl()

    override fun onLoadData() {
        repositoryRemote.getListEventServer(this)
    }

    override fun onSuccess(response: List<ObjectEvent>) {
        view.setupList(response)
        view.showLoading(false)
    }

    override fun onError(errorBody: String?) {
        view.showMessageError(errorBody.toString())
        view.showLoading(false)
    }
}
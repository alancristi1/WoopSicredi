package com.alan.woopsicredi.ui.detailsview.presenter

import com.alan.woopsicredi.models.ObjectEvent
import com.alan.woopsicredi.ui.detailsview.call.DetailsEventImpl
import com.alan.woopsicredi.ui.detailsview.contract.DetailsContract

class DetailsPresenter constructor(private val view : DetailsContract.View) : DetailsContract.Presenter, DetailsContract.Listener{

    private var repositoryRemote : DetailsEventImpl = DetailsEventImpl()

    override fun onLoadData(id : String) {
        repositoryRemote.getDetailsEvent(this, id)
    }

    override fun setCheckin(id: String, name: String, email: String) {
        repositoryRemote.sendCheckin(id, name, email, this)
    }

    override fun onSuccess(response: ObjectEvent) {
        view.renderActivity(response)
    }

    override fun onSuccessCheckin(code : Int) {
        view.showToastAfterCheckin(code)
    }

    override fun onError(errorBody: String?) {

    }
}
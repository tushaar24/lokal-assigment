package com.example.simpleapifetch.ui.fragments.showDataListFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleapifetch.Communicator.Communicator
import com.example.simpleapifetch.model.SimpleData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowDataListViewModel : ViewModel() {

    val dataList: MutableLiveData<SimpleData> = MutableLiveData()
    val isSuccessful : MutableLiveData<Boolean> = MutableLiveData()

    fun getDataList() {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                isSuccessful.postValue(true)
                val response = Communicator.getRemoteApiServices().getData()
                if(response.isSuccessful){
                    dataList.postValue(response.body())
                }else{
                    isSuccessful.postValue(false)
                }
            }
        }catch (e : Exception){
            isSuccessful.postValue(false)
        }
    }
}
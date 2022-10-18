package com.task.excitel

import android.app.Application
import androidx.lifecycle.*
import com.task.excitel.data.ExcitelApi
import com.task.excitel.data.ItemInfo
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the fragment
 */
class InfoViewModel(application: Application): AndroidViewModel(application) {

    private val _items = MutableLiveData<List<ItemInfo>>()
    val items: LiveData<List<ItemInfo>> = _items
    private val _details = MutableLiveData<ItemInfo>()
    val details: LiveData<ItemInfo> = _details

    init {
        getInfo()
    }

    fun setDetailsInfo(item: ItemInfo) {
        _details.value = item
    }

    private fun getInfo() {
        viewModelScope.launch {
            try {
                _items.value = ExcitelApi.retrofitService.getItems()
            } catch (e: Exception) {
                _items.value = listOf()
            }
        }
    }
}
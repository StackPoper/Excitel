package com.task.excitel.ui

import android.view.View
import com.task.excitel.data.ItemInfo

interface IOnItemInfo {
    fun click(item: View, info: ItemInfo)
}
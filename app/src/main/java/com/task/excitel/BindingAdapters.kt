package com.task.excitel

import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.task.excitel.data.ItemInfo
import com.task.excitel.ui.LoadSvgImageView

/**
 * Updates the data shown in the [RecyclerView].
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ItemInfo>?) {
    val adapter = recyclerView.adapter as CustomListAdapter
    if (data != null) {
        adapter.addData(data)
    }
}
@BindingAdapter("itemText")
fun bindItem(t: AppCompatTextView, text: String?) {
    if (text?.isNotEmpty() == true)
        t.text = text
}
@BindingAdapter("imageUrl")
fun bindItem(imgView: LoadSvgImageView, imgUrl: String?) {
    imgView.loadSvgOrOther(imgUrl)
}
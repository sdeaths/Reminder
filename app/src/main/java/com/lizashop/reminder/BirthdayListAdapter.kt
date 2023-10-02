package com.lizashop.reminder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lizashop.reminder.databinding.ItemRemindListBinding

class BirthdayListAdapter :
    ListAdapter<BirthdayItem, BirthdayListAdapter.BirthdayViewHolder>(BirthdayDiffUtil()) {

    class BirthdayViewHolder(val binding: ItemRemindListBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirthdayViewHolder {
        val binding = ItemRemindListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BirthdayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BirthdayViewHolder, position: Int) {
        val birthday = getItem(position)
        with(holder.binding) {
            nameFriendText.text = birthday.friendName
            dateText.text = birthday.date
        }
    }
}
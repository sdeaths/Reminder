package com.lizashop.reminder

import androidx.recyclerview.widget.DiffUtil

class BirthdayDiffUtil : DiffUtil.ItemCallback<BirthdayItem>() {
    override fun areItemsTheSame(oldItem: BirthdayItem, newItem: BirthdayItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BirthdayItem, newItem: BirthdayItem): Boolean {
        return oldItem == newItem
    }
}
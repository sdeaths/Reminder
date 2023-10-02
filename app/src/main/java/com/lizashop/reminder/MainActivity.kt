package com.lizashop.reminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.lizashop.reminder.databinding.ActivityMainBinding
import com.lizashop.reminder.databinding.DialogAddingGiftBinding
import java.text.MessageFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val birthdayList = mutableListOf<BirthdayItem>()
    private var id = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = BirthdayListAdapter()
        adapter.submitList(birthdayList)
        binding.birthdayList.layoutManager = LinearLayoutManager(this)
        binding.birthdayList.adapter = adapter

        binding.addBirthday.setOnClickListener {
            val dialogBinding = DialogAddingGiftBinding.inflate(layoutInflater)

            dialogBinding.dateTextButton.setOnClickListener {
                val picker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
                picker.addOnPositiveButtonClickListener {
                    val date = SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(Date(it))
                    dialogBinding.dateTextButton.text = MessageFormat.format("Selected Date: $date")
                }
                picker.show(supportFragmentManager, "tag")
            }

            MaterialAlertDialogBuilder(this@MainActivity)
                .setTitle("Adding Birthday")
                .setView(dialogBinding.root)
                .setPositiveButton("Add Birthday") { dialog, _ ->
                    if (dialogBinding.dateTextButton.text != "Choose Date") {
                        birthdayList.add(
                            BirthdayItem(
                                id = id++,
                                friendName = dialogBinding.friendNameEditText.text.toString(),
                                date = dialogBinding.dateTextButton.text.toString()
                            )
                        )
                        Snackbar.make(
                            it,
                            "Birthday successfully added",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } else {
                        Snackbar.make(
                            it,
                            "Please choose the date",
                            Snackbar.LENGTH_LONG
                        ).show()
                        dialog.dismiss()
                    }
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}
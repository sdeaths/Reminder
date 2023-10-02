package com.lizashop.reminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.lizashop.reminder.databinding.ActivityMainBinding
import com.lizashop.reminder.databinding.DialogAddingGiftBinding

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

            MaterialAlertDialogBuilder(this@MainActivity)
                .setTitle("Adding Birthday")
                .setView(dialogBinding.root)
                .setPositiveButton("Add Birthday") { _, _ ->
                    birthdayList.add(
                        BirthdayItem(
                            id = id++,
                            friendName = dialogBinding.friendNameEditText.text.toString(),
                            date = dialogBinding.dateEditText.text.toString()
                        )
                    )

                    Snackbar.make(
                        it,
                        "Birthday successfully added",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}
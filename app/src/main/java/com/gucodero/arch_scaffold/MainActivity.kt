package com.gucodero.arch_scaffold

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.gucodero.arch_scaffold.databinding.ActivityMainBinding
import com.gucodero.ui.core.activity.ActivityScaffold
import com.gucodero.ui.core.activity.LoadingActivity
import com.gucodero.ui.core.entity.BottomNavMode
import com.gucodero.ui.core.entity.ToolbarMode
import com.gucodero.ui.core.util.onUIEvent
import com.gucodero.ui.core.util.onUIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ActivityScaffold, LoadingActivity {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setBottomNavMode(bottomNavMode: BottomNavMode) {

    }

    override fun setToolbarMode(toolbarMode: ToolbarMode) {

    }

    override var isLoading: Boolean
        get() = true
        set(value) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        val btnMinus = findViewById<Button>(R.id.btnMinus)
//        val btnPlus = findViewById<Button>(R.id.btnPlus)
//        val btnNewPeople = findViewById<Button>(R.id.btnGetNewPeople)
//        val tvCounter = findViewById<TextView>(R.id.tvCounter)
//        btnPlus.setOnClickListener {
//            viewModel.onIncrement()
//        }
//        btnMinus.setOnClickListener {
//            viewModel.onDecrement()
//        }
//        btnNewPeople.setOnClickListener {
//            viewModel.getNewPeople()
//        }
//        onUIState(
//            viewModel = viewModel,
//            listenWhen = { previous, current ->
//                previous?.counter != current.counter
//            }
//        ) {
//            tvCounter.text = it.counter.toString()
//        }
//        onUIEvent(
//            viewModel = viewModel
//        ) {
//            when(it) {
//                is MainUIEvent.DecrementNotValid -> {
//                    AlertDialog.Builder(this)
//                        .setTitle("Error")
//                        .setMessage("No se puede decrementar mas")
//                        .setPositiveButton("Ok") { dialog, _ ->
//                            dialog.dismiss()
//                        }
//                        .create()
//                        .show()
//                }
//                is MainUIEvent.People -> {
//                    AlertDialog.Builder(this)
//                        .setTitle("People")
//                        .setMessage(it.data)
//                        .setPositiveButton("Close") { dialog, _ ->
//                            dialog.dismiss()
//                        }
//                        .create()
//                        .show()
//                }
//            }
//        }
    }
}
package com.example.ozonmobile
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import com.example.ozonmobile.DAOinterfaces.UserDao
import com.example.ozonmobile.Entyties.User
import com.example.ozonmobile.databinding.FragmentFinanceBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FinanceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_finance, container, false)

        val balanceTw: TextView = view.findViewById(R.id.balanceTextView)
        val database = AppDatabase.getDb(requireContext())

        CoroutineScope(Dispatchers.IO).launch {
            val lastUser = database.userDao().getLastUser()
            val balance = lastUser?.balance ?: 0.0

            // Обновляем UI на основе полученных данных
            withContext(Dispatchers.Main) {
                balanceTw.text = getString(R.string.balance_template, balance)
            }

            val operationsButton: Button = view.findViewById(R.id.operationsButton)
            operationsButton.setOnClickListener {
                val intent = Intent(activity, TransactionActivity::class.java)

                startActivity(intent)
            }

            val bonusButton: Button = view.findViewById(R.id.bonusButton)
            bonusButton.setOnClickListener {
                val intent = Intent(requireContext(), BonusActivity::class.java)
                startActivity(intent)
            }

            val bottomNavigationView: BottomNavigationView =
                view.findViewById(R.id.bottomNavigationView)

            bottomNavigationView.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.navigation_chat -> {
                        val intent = Intent(requireContext(), ChatActivity::class.java)
                        startActivity(intent)
                        true // Возвращаем true, чтобы указать, что событие обработано
                    }

                    R.id.navigation_payments -> {
                        val intent = Intent(requireContext(), paymentActivity::class.java)
                        startActivity(intent)
                        true
                    }

                    else -> false
                }
            }
        }
        return view
    }
}

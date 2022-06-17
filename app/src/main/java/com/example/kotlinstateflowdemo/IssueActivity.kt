package com.example.kotlinstateflowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.beust.klaxon.Klaxon
import com.example.kotlinstateflowdemo.databinding.ActivityIssueBinding
import com.example.kotlinstateflowdemo.databinding.ActivityMagazineBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class IssueActivity : AppCompatActivity(), ICallbackListener {
    private var _binding: ActivityIssueBinding? = null
    private val binding get() = _binding!!

    private val viewModel: IssueViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityIssueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data: String = intent.getStringExtra("data").toString()
        Log.w("jagaatest", data)

        var jsonData: JsonData? = null
        try {
            jsonData = Klaxon().parse<JsonData>(data)
            Log.w("jagaatest", jsonData.toString())
        } catch (e: Exception) {
            Log.w("jagaatest", e.toString())
        }

        if(jsonData != null) {
            viewModel.setCookie(jsonData)

            val viewPager: ViewPager2 = findViewById(R.id.view_pager2)
            val fragments: ArrayList<Fragment> = arrayListOf(
                Page1Fragment(),
                Page2Fragment()
            )

            val adapter = ViewPagerAdapter(fragments, this)
            viewPager.adapter = adapter
            viewPager.isUserInputEnabled = false

            lifecycleScope.launchWhenStarted {
                viewModel.issueUiState.collect {
                    when (it) {
                        is IssueViewModel.IssueUiState.Success -> {
                            Snackbar.make(
                                binding.root,
                                "Successfully logged in",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        is IssueViewModel.IssueUiState.Error -> {
                            Snackbar.make(
                                binding.root,
                                it.message,
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        is IssueViewModel.IssueUiState.Loading -> {
//                        binding.progressBar.isVisible = true
                        }
                        is IssueViewModel.IssueUiState.SetUserInputEnabled -> {
                            viewPager.isUserInputEnabled = it.isUserInputEnabled
                        }
                        else -> Unit
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun setUserInputEnabled(isUserInputEnabled: Boolean) {
        viewModel.setUserInputEnabled(isUserInputEnabled)
    }
}
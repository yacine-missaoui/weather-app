package com.example.weatherapp.ui.townList

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.core.common.Utils.TAG
import com.example.weatherapp.core.domain.model.LocatedTown
import com.example.weatherapp.databinding.FragmentTownListBinding
import com.example.weatherapp.ui.townDetails.TownDetailsActivity
import com.example.weatherapp.ui.townList.adapter.LocatedTownAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class TownListFragment : Fragment() {
    private lateinit var binding: FragmentTownListBinding
    private val viewModel: TownListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTownListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                observeTownList()
            }
        }


    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchTowns()
    }
    private suspend fun observeTownList()
    {
        viewModel.townsState.collectLatest { towns ->
            with(binding)
            {
                if (towns.isEmpty()) {
                    textViewEmptyMessage.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else {
                    textViewEmptyMessage.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    displayData(towns)
                }
            }

        }
    }

    private fun handleLocatedTownClick(clickedTown: LocatedTown)
    {
        Log.d(TAG, "handleLocatedTownClick: ${clickedTown.id}")
        val intent = Intent(context, TownDetailsActivity::class.java)
        intent.putExtra("townId",clickedTown.id)
        startActivity(intent)
    }

    private fun displayData(towns: List<LocatedTown>)
    {
        val adapter = LocatedTownAdapter(towns){
            handleLocatedTownClick(it)
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}

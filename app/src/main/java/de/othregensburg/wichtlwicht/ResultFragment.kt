package de.othregensburg.wichtlwicht

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.othregensburg.wichtlwicht.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentResultBinding.inflate(inflater, container, false)

        val adapter = ListAdapter(viewModel.originalParticipantList)
        val recyclerView = binding.recyclerViewOrg

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        if(viewModel.wichtlpartnerList.isEmpty())
            binding.resultWichtln.visibility = View.INVISIBLE
        else
            binding.resultWichtln.visibility = View.VISIBLE

        if(viewModel.tasksListshuffle.isEmpty())
            binding.resultAufgaben.visibility = View.INVISIBLE
        else
            binding.resultAufgaben.visibility = View.VISIBLE

        binding.resultWichtln.setOnClickListener {
            val adapter2 = ListAdapter(viewModel.wichtlpartnerList)
            val recyclerView2 = binding.recyclerViewResult

            recyclerView2.adapter = adapter2
            recyclerView2.layoutManager = LinearLayoutManager(requireContext())
        }
        binding.resultAufgaben.setOnClickListener {
            val adapter2 = ListAdapter(viewModel.tasksListshuffle)
            val recyclerView2 = binding.recyclerViewResult

            recyclerView2.adapter = adapter2
            recyclerView2.layoutManager = LinearLayoutManager(requireContext())
        }
        binding.deleteAlldeleteButton.setOnClickListener {
            viewModel.originalParticipantList.clear()
            viewModel.wichtlpartnerList.clear()
            viewModel.tasksList.clear()
            viewModel.tasksListshuffle.clear()

            findNavController().navigate(R.id.action_ResultFragment_to_ListFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
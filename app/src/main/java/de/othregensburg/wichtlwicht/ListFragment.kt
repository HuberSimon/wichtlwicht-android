package de.othregensburg.wichtlwicht

import android.os.Bundle
import android.view.*
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.othregensburg.wichtlwicht.databinding.FragmentListBinding


class ListFragment : Fragment() {


    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        val adapter = ListAdapter(viewModel.originalParticipantList)
        val recyclerView = binding.recyclerView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        binding.addButton.setOnClickListener {
            val inp = binding.inputParticipant
            val input: String = inp.text.toString()
            if(checkInput(input)) {

                viewModel.originalParticipantList.add(input)

                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())

                inp.text.clear()
            }
        }
        binding.startButton.setOnClickListener {
            viewModel.indexParticipant = 0
            viewModel.wichtlpartnerList.clear()
            if(viewModel.originalParticipantList.size > 3) {
                for(item in viewModel.originalParticipantList) {
                    viewModel.wichtlpartnerList.add(item)
                }

                viewModel.wichtlpartnerList.shuffle()
                var idx: Int = 0
                while(idx < viewModel.originalParticipantList.size) {
                    if(viewModel.originalParticipantList.elementAt(idx) == viewModel.wichtlpartnerList.elementAt(idx)){
                        viewModel.wichtlpartnerList.shuffle()
                        idx = 0
                    }
                    else {
                        idx++
                    }
                }
                findNavController().navigate(R.id.action_ListFragment_to_ParticipantFragment)
            }
            else{
                Toast.makeText(context, "Es müssen mindestens 4 Teilnehmer sein!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.deleteLastButton.setOnClickListener {
            if (viewModel.originalParticipantList.isNotEmpty()){
                viewModel.originalParticipantList.removeAt(viewModel.originalParticipantList.lastIndex)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        binding.deleteAllButton.setOnClickListener {
            if (viewModel.originalParticipantList.isNotEmpty()) {
                viewModel.originalParticipantList.clear()
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
        }

        return view
    }

    private fun checkInput(input: String): Boolean{
        if (input.isNotEmpty()) {
            for (item: String in viewModel.originalParticipantList){
                if (input == item) {
                    Toast.makeText(context,"Namen müssen unterschiedlich sein", Toast.LENGTH_SHORT).show()
                    return false
                }
            }
            if(input.elementAt(input.lastIndex) == ' '){
                Toast.makeText(context,"Kein Leerzeichen am Ende", Toast.LENGTH_SHORT).show()
                return false
            }
            if(input.elementAt(0) == ' '){
                Toast.makeText(context,"Kein Leerzeichen am Anfang", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }else{
            return false
        }
    }


}
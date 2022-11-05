package de.othregensburg.wichtlwicht

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.othregensburg.wichtlwicht.databinding.FragmentTaskBinding


class TasksFragment : Fragment() {


    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        val adapter = ListAdapter(viewModel.originalParticipantList)
        val recyclerView = binding.recyclerView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter2 = ListAdapter(viewModel.tasksList)
        val recyclerView2 = binding.recyclerViewTasks

        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager = LinearLayoutManager(requireContext())

        binding.addButton.setOnClickListener {
            val inp = binding.inputParticipant
            val input: String = inp.text.toString()
            if(checkInputPart(input)) {

                viewModel.originalParticipantList.add(input)

                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())

                inp.text.clear()
            }
        }
        binding.addTasksButton.setOnClickListener {
            val inp = binding.inputTask
            val input: String = inp.text.toString()
            if(checkInputTask(input)) {

                viewModel.tasksList.add(input)

                recyclerView2.adapter = adapter2
                recyclerView2.layoutManager = LinearLayoutManager(requireContext())

                inp.text.clear()
            }
        }
        binding.startButton.setOnClickListener {
            viewModel.indexParticipant = 0
            viewModel.tasksListshuffle.clear()
            if(viewModel.originalParticipantList.size > 3) {
                if(viewModel.originalParticipantList.size == viewModel.tasksList.size){
                    for(item in viewModel.tasksList) {
                        viewModel.tasksListshuffle.add(item)
                    }

                    viewModel.tasksListshuffle.shuffle()
                    var idx: Int = 0
                    while(idx < viewModel.tasksList.size) {
                        if(viewModel.tasksList.elementAt(idx) == viewModel.tasksListshuffle.elementAt(idx)){
                            viewModel.tasksListshuffle.shuffle()
                            idx = 0
                        }
                        else {
                            idx++
                        }
                    }
                findNavController().navigate(R.id.action_TasksFragment_to_ParticipantTaskFragment)
                }
                else{
                    Toast.makeText(context, "Es muss genau so viele Aufgaben wie Teilnehmer geben!", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(context, "Es müssen mindestens 4 Teilnehmer sein!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.deleteLastPartButton.setOnClickListener {
            if (viewModel.originalParticipantList.isNotEmpty()){
                viewModel.originalParticipantList.removeAt(viewModel.originalParticipantList.lastIndex)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        binding.deleteLastTaskButton.setOnClickListener {
            if (viewModel.tasksList.isNotEmpty()){
                viewModel.tasksList.removeAt(viewModel.tasksList.lastIndex)
                recyclerView2.adapter = adapter2
                recyclerView2.layoutManager = LinearLayoutManager(requireContext())
            }
        }
        binding.deleteAllButton.setOnClickListener {
            if (viewModel.originalParticipantList.isNotEmpty()) {
                viewModel.originalParticipantList.clear()
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(requireContext())
            }
            if (viewModel.tasksList.isNotEmpty()) {
                viewModel.tasksList.clear()
                recyclerView2.adapter = adapter2
                recyclerView2.layoutManager = LinearLayoutManager(requireContext())
            }
        }

        return view
    }

    private fun checkInputPart(input: String): Boolean{
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
    private fun checkInputTask(input: String): Boolean{
        if (input.isNotEmpty()) {
            for (item: String in viewModel.tasksList){
                if (input == item) {
                    Toast.makeText(context,"Aufgaben müssen unterschiedlich sein", Toast.LENGTH_SHORT).show()
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
package de.othregensburg.wichtlwicht

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import de.othregensburg.wichtlwicht.databinding.FragmentParticipantBinding

class ParticipantFragment : Fragment() {

        private var _binding: FragmentParticipantBinding? = null

        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        private val viewModel: ListViewModel by activityViewModels()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            _binding = FragmentParticipantBinding.inflate(inflater, container, false)

            if (viewModel.originalParticipantList.isNotEmpty()) {
                binding.textviewNextWichtl.text = viewModel.originalParticipantList.elementAt(viewModel.indexParticipant)
            }

            binding.buttonNextWichtl.setOnClickListener {
                findNavController().navigate(R.id.action_ParticipantFragment_to_WichtlFragment)
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
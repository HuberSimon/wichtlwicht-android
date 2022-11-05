package de.othregensburg.wichtlwicht

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import de.othregensburg.wichtlwicht.databinding.FragmentWichtlBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WichtlFragment : Fragment() {

    private var _binding: FragmentWichtlBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: ListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWichtlBinding.inflate(inflater, container, false)

        binding.textviewWichtl.text = viewModel.originalParticipantList.elementAt(viewModel.indexParticipant)

        binding.buttonWichtl.setOnClickListener{
            if (viewModel.originalParticipantList.size > viewModel.indexParticipant) {
                binding.textviewWichtlPartner.text = viewModel.wichtlpartnerList.elementAt(viewModel.indexParticipant)
            }
        }
        binding.buttonNext.setOnClickListener {
            viewModel.indexParticipant += 1
            if (viewModel.originalParticipantList.size == viewModel.indexParticipant) {
                Toast.makeText(context, "Jeder hat einen Wichtl", Toast.LENGTH_LONG).show()
                viewModel.indexParticipant = 0
                findNavController().navigate(R.id.action_WichtlFragment_to_ListFragment)
            }
            else {
                findNavController().navigate(R.id.action_WichtlFragment_to_ParticipantFragment)
            }
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
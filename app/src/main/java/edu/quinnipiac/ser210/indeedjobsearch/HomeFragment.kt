/*
  * Sam Woodburn & Gabby Pierce
  * SER210- assignment 3
  * home fragment- welcome screen, displays toolbar, indeed image, search bar, search button, and bottom text view
 */

package edu.quinnipiac.ser210.jobsearch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import edu.quinnipiac.ser210.jobsearch.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    //job view model
    private lateinit var viewModel: JobViewModel
    private lateinit var viewModelFactory: JobViewModelFactory
    //variables for binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    var chosenLocation: String = " "

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment, with binding
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model
        val repository = JobsRepository()
        viewModelFactory = JobViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(JobViewModel::class.java)


        //search button actions
        val searchButton: Button = binding.searchLocations
        searchButton.setOnClickListener{
            chosenLocation = binding.editSearch.text.toString()
            //log make sure correct info from the EditText is being passed to the all job fragment
            Log.d("LOCATION", "entered: ${chosenLocation}")
            val action = HomeFragmentDirections.actionHomeFragmentToAllJobFragment2(chosenLocation)
            findNavController().navigate(action)
        }

    }

}



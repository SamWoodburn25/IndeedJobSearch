/*
  * Sam Woodburn & Gabby Pierce
  * SER210- assignment 3
  * all jobs fragment- shows all software engineering jobs from the specified location
 */

package edu.quinnipiac.ser210.jobsearch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import edu.quinnipiac.ser210.jobsearch.databinding.FragmentAllJobBinding

class AllJobFragment : Fragment() {

    //variables for binding
    private var _binding: FragmentAllJobBinding? = null
    private val binding get() = _binding!!
    //variables for recycler view
    lateinit var recyclerAdapter: RecyclerAdapter
    //job view model
    private lateinit var viewModel: JobViewModel
    private lateinit var viewModelFactory: JobViewModelFactory
    private lateinit var chosenLocation: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAllJobBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the chosen location from arguments
        val args = AllJobFragmentArgs.fromBundle(requireArguments())
        chosenLocation = args.searchQuery

        // Setup ViewModel
        val repository = JobsRepository()
        viewModelFactory = JobViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(JobViewModel::class.java)

        // Setup RecyclerView with Adapter and click listener to navigate
        setupRecyclerView()

        // Observe ViewModel LiveData for jobs
        observeViewModel()
        viewModel.searchJobs(chosenLocation, "Software Engineer", null, null)
        //log to monitor data
        Log.d("LOCATION", "search: ${chosenLocation}")
    }

    //setting up recycler view with recycler adapter
    private fun setupRecyclerView() {
        recyclerAdapter = RecyclerAdapter(listOf())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recyclerAdapter
    }

    //observe live data changes
    private fun observeViewModel() {
        //observing the live data list of hits from the view model
        viewModel.jobs.observe(viewLifecycleOwner) { jobs ->
            if (jobs != null) {
                //log for info
                Log.d("ALL_JOB_FRAGMENT", "LiveData observer received ${jobs.size} jobs")
                recyclerAdapter.submitList(jobs)
            } else {
                Log.d("ALL_JOB_FRAGMENT", "LiveData observer received null")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Avoid memory leak
    }

}
/*
  * Sam Woodburn & Gabby Pierce
  * SER210- assignment 3
  * help fragment- displays directions how to use the app
 */

package edu.quinnipiac.ser210.jobsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.quinnipiac.ser210.jobsearch.databinding.FragmentAllJobBinding
import edu.quinnipiac.ser210.jobsearch.databinding.FragmentHelpBinding

//binding variables
    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    class HelpFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            _binding = FragmentHelpBinding.inflate(inflater, container, false)
            val view = binding.root
            return view
        }

    }
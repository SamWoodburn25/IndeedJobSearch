/*
  * Sam Woodburn & Gabby Pierce
  * SER210- assignment 3
  * job view model factory- takes repository parameter to make a job view model
 */

package edu.quinnipiac.ser210.jobsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class JobViewModelFactory(private val repository: JobsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JobViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return JobViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
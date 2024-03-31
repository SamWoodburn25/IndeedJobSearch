/*
  * Sam Woodburn & Gabby Pierce
  * SER210- assignment 3
  * job view model- observes the live data from the job repository
  * jobs variable to store list of Hits
 */

package edu.quinnipiac.ser210.jobsearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.quinnipiac.ser210.indeedjobsearch.apiData.Hit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class JobViewModel (private val repository: JobsRepository) : ViewModel() {

    //live data variables
    private val _jobs = MutableLiveData<List<Hit>>()
    val jobs: LiveData<List<Hit>> get() = _jobs

    //calls the repository to fetch job listings
    fun searchJobs(location: String, query: String, title: String?, companyName: String?){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.searchJobs(location, query, title, companyName)
                Log.d("VIEW_MODEL: ", "api call success")
                //successfully getting the data, post the list of hjts is posted to be observed by UI
                withContext(Dispatchers.Main){
                    Log.d("VIEW_MODEL:", "Posting ${response?.hits?.size} hits to LiveData")
                    _jobs.value = response?.hits
                }
            } catch (e: Exception) {
                Log.d("VIEW_MODEL", "API call failure: ${e.message}")
            }
        }
    }
}


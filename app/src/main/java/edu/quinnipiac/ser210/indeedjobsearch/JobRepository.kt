/*
  * Sam Woodburn & Gabby Pierce
  * SER210- assignment 3
  * job repository - makes an instance of the api client and checks
  * if the api response was successful when calling the search jobs method
 */

package edu.quinnipiac.ser210.jobsearch

import android.util.Log
import edu.quinnipiac.ser210.indeedjobsearch.apiData.ApiInterface
import edu.quinnipiac.ser210.indeedjobsearch.apiData.JobJSON

class JobsRepository {
    //api instance
    private val apiClient = ApiInterface.ApiClient.instance

    suspend fun searchJobs(location: String,  query: String, title: String?, companyName: String?): JobJSON {
       //check if api call is success
        val response = apiClient.searchJobs(location, query, title, companyName)
        if (response.isSuccessful && response.body() != null) {
            //log result
            Log.d("REPOSITORY", "API Response success: ${response.body()}")
            return response.body()!!
        } else {
            //log result
            Log.d("REPOSITORY", "API Response failure: ${response.code()} ${response.message()}")
            throw Exception("Failed to fetch jobs: ${response.code()} ${response.message()}")
        }
    }
}


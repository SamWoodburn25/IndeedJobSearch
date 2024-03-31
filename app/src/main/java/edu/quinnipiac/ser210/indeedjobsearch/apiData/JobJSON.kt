/*
  * Sam Woodburn & Gabby Pierce
  * SER210- assignment 3
  * JobJSON data class- contains all info indeed api has
 */

package edu.quinnipiac.ser210.indeedjobsearch.apiData

import edu.quinnipiac.ser210.indeedjobsearch.apiData.Hit

data class JobJSON(
    val count: Int,
    val hits: List<Hit>,    //only using this list of the job matches
    val indeed_final_url: String,
    val next_page_id: Int,
    val suggest_locality: Any
)



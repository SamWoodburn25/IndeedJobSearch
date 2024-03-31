/*
  * Sam Woodburn & Gabby Pierce
  * SER210- assignment 3
  * hit data class- contains all info api has about specified searched job
 */

package edu.quinnipiac.ser210.indeedjobsearch.apiData

//data class with actual job info
data class Hit(
    val company_name: String,   //used
    val formatted_relative_time: String,
    val id: String,
    val link: String,
    val locality: String,
    val location: String,   //used
    val pub_date_ts_milli: Long,
    val salary: SalaryX,
    val title: String   //used
)
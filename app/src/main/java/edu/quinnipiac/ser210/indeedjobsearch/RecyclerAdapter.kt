/*
  * Sam Woodburn & Gabby Pierce
  * SER210- assignment 3
  * recycler adapter- loads and binds the data to the recycler view
  * inner class view holder- binds actual data to the text view
 */

package edu.quinnipiac.ser210.jobsearch


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import edu.quinnipiac.ser210.indeedjobsearch.apiData.Hit

//list of jobs variable, to be accessed by other classes
lateinit var hitList: List<Hit>
class RecyclerAdapter(var dataSet:  List<Hit>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    //view holder class
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //text view variables
        val textViewTitle: TextView = view.findViewById(R.id.jobTitle)
        val textViewCompany: TextView = view.findViewById(R.id.companyName)
        val textViewLocation: TextView = view.findViewById(R.id.jobLocation)
        fun bind(hit: Hit) {
            textViewTitle.text = "Job Title: "+hit.title
            textViewCompany.text = "Company: "+hit.company_name
            textViewLocation.text = "Location: "+hit.location
        }
    }

    //make view holder, list_item layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        //dataset parameter is equal to hitList
        hitList = dataSet

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //bind hit to holder
        val hit = dataSet[position]
        holder.bind(hit)
    }

    override fun getItemCount() = dataSet.size


    //load a list of Hit to the dataset variable
    fun submitList(newHits: List<Hit>) {
        //log to track the info
        Log.d("RECYCLER_ADAPTER", "Submitting list with size: ${newHits.size}")
        val diffCallback = object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = dataSet.size
            override fun getNewListSize(): Int = newHits.size
            //check if the items or contents are the same
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataSet[oldItemPosition].id == newHits[newItemPosition].id
            }
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return dataSet[oldItemPosition] == newHits[newItemPosition]
            }
        }
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        //update dataset variable
        dataSet = newHits
        diffResult.dispatchUpdatesTo(this)
    }

}
package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.viewmodel.MainViewState
import com.example.myapplication.viewmodel.SchoolData

class MainBindingAdapter(
    private val viewState: MainViewState,
    private val schoolList : List<SchoolData> = viewState.schools
) : RecyclerView.Adapter<MainBindingAdapter.MainViewHolder>(){

    private var onClickListener: OnClickListener? = null
    companion object {
        const val TAG = "HomeBindingAdapter"
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.school_layout, parent, false)

        return MainViewHolder(view)
    }

    override fun getItemCount(): Int {
        return schoolList.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val currentSchool = schoolList[position]

        holder.schoolButton.text = currentSchool.school_name
        holder.schoolButton.setOnClickListener {
            if(onClickListener != null) {
                onClickListener!!.onClick(position)
            }
        }
  }

    class MainViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val schoolButton: Button = view.findViewById(R.id.school_name)
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }

}
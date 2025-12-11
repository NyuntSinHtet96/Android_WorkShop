package com.example.studentrepository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val studentList: List<Student>,
                     private val onItemClick: (Student) -> Unit) // Define onClick listener as lambda) //
                    : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
// Display the student details
        val student = studentList[position]
        holder . matriculationNumberTextView.text = student . matriculationNumber
        holder.nameTextView.text = student.name
// Set up the click listener to pass the student data
        holder.itemView.setOnClickListener { onItemClick(student) }
    }

    override fun getItemCount(): Int = studentList.size
    inner class StudentViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val matriculationNumberTextView: TextView = itemView.findViewById(R.id.matriculation_number_text_view)
        val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
    }
}
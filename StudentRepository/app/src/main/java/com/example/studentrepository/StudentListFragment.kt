package com.example.studentrepository

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.studentrepository.databinding.FragmentStudentListBinding

class StudentListFragment : Fragment() {
    private var _binding: FragmentStudentListBinding?=null
    private val binding get()=_binding!!
    private lateinit var studentAdapter: StudentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStudentListBinding.inflate(inflater, container, false)
//        binding.apply {
//            student1Button.setOnClickListener {
//                //Toast.makeText(context,"Show student 1's details",Toast.LENGTH_LONG).show()
//                launchStudentDetailsFragment(1);
//            }
//            student2Button.setOnClickListener {
//                //Toast.makeText(context,"Show student 2's details",Toast.LENGTH_LONG).show()
//                launchStudentDetailsFragment(2);
//
//            }
//            student3Button.setOnClickListener {
//                //Toast.makeText(context,"Show student 3's details",Toast.LENGTH_LONG).show()
//                launchStudentDetailsFragment(3);
//
//            }
//        }
            binding.recyclerViewStudents.layoutManager = LinearLayoutManager(context)
            val studentList = FakeStudentData.getStudents()
            studentAdapter = StudentAdapter(studentList) {
            // When a student item is clicked, launch the detail fragment
                student -> launchStudentDetailsFragment(student.id)
        }
        binding.recyclerViewStudents.adapter = studentAdapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun launchStudentDetailsFragment(studentId:Int){
        val action = StudentListFragmentDirections.actionStudentListFragmentToStudentDetailsFragment(studentId)
        binding.root.findNavController().navigate(action)
    }
}
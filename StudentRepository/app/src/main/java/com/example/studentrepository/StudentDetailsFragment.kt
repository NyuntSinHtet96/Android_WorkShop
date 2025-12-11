package com.example.studentrepository

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.studentrepository.databinding.FragmentStudentDetailsBinding

class StudentDetailsFragment : Fragment() {
    private var _binding: FragmentStudentDetailsBinding?=null
    private val binding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentStudentDetailsBinding.inflate(inflater, container, false)
        val studentId = StudentDetailsFragmentArgs.fromBundle(requireArguments()).studentId
        val studentObj = FakeStudentData.getStudent(studentId)
        binding.apply {
            if(studentObj!=null){
                nameTextView.text = studentObj.name
                matriculationNumberTextView.text =  "Matric No.:${studentObj.matriculationNumber}"
                emailTextView.text = "Email: ${studentObj.email}"
                majorTextView.text = "Major: ${studentObj.major}"
            }
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
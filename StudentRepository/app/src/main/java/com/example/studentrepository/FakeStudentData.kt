package com.example.studentrepository

object FakeStudentData {
    private val studentList = listOf(
        Student(1, "Bruce Wayne", "A011111A", "E011111@u.nus.edu", "Computer Science"),
        Student(2, "Clark Kent", "A022222B", "E022222@u.nus.edu", "Business"),
        Student(3, "Tony Shark", "A033333C", "E033333@u.nus.edu", "Mechanical Engineering")
    )

    fun getStudent(studentId: Int): Student? {
        for (student in studentList) {
            if (student.id === studentId)
                return student
        }
        return null
    }

    fun getStudents(): List<Student> {
        return studentList
    }
}
package com.example.rxjavasamples.operators

object StudentUtils {

    fun getStudents(): ArrayList<Student> {

        val students = ArrayList<Student>()

        val student1 = Student()
        student1.name = " student 1"
        student1.email = " student1@gmail.com "
        student1.age = 27
        students.add(student1)

        val student2 = Student()
        student2.name = " student 2"
        student2.email = " student2@gmail.com "
        student2.age = 20
        students.add(student2)

        val student3 = Student()
        student3.name = " student 3"
        student3.email = " student3@gmail.com "
        student3.age = 20
        students.add(student3)

        val student4 = Student()
        student4.name = " student 4"
        student4.email = " student4@gmail.com "
        student4.age = 20
        students.add(student4)

        val student5 = Student()
        student5.name = " student 5"
        student5.email = " student5@gmail.com "
        student5.age = 20
        students.add(student5)

        return students


    }
}
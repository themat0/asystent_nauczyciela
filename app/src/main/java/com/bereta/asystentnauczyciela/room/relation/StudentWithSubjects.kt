package com.bereta.asystentnauczyciela.room.relation

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.bereta.asystentnauczyciela.room.entities.Student
import com.bereta.asystentnauczyciela.room.entities.StudentSubjects
import com.bereta.asystentnauczyciela.room.entities.Subject


data class StudentWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentID",
        entityColumn = "subjectID",
        associateBy = Junction(StudentSubjects::class)
    )
    val subjects: List<Subject>
)
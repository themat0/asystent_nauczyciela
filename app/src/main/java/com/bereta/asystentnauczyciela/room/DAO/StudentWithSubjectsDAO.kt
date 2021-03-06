package com.bereta.asystentnauczyciela.room.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bereta.asystentnauczyciela.room.entities.Subject
import com.bereta.asystentnauczyciela.room.relation.SubjectWithStudents

@Dao
interface StudentWithSubjectsDAO {
    @Transaction
    @Query("SELECT * FROM subjects_table "+
            "INNER JOIN students_subjects_table ON students_subjects_table.subjectID = subjects_table.subjectID " +
            "WHERE students_subjects_table.studentID LIKE :id")
    fun getStudentWithSubjects(id: Int): LiveData<List<Subject>>
    @Transaction
    @Query("SELECT * FROM subjects_table where subjectID not in("+
            "SELECT subjects_table.subjectID FROM subjects_table "+
            "INNER JOIN students_subjects_table ON students_subjects_table.subjectID = subjects_table.subjectID " +
            "WHERE students_subjects_table.studentID LIKE :id)")
    fun getStudentWithNotSubjects(id: Int): LiveData<List<Subject>>
    @Transaction
    @Query("INSERT INTO students_subjects_table (subjectID, studentID) VALUES (:subject,:student)")
    fun insertStudentSubject(student: Int, subject: Long)
    @Transaction
    @Query("DELETE FROM students_subjects_table where subjectID like :subject and studentID like :student")
    fun deleteStudentSubject(student: Int, subject: Long)
    @Transaction
    @Query("SELECT S.*,SUB.* FROM students_table S left join students_subjects_table P on S.studentID = P.studentID inner join subjects_table SUB on SUB.subjectID=P.subjectID")
    fun getAllStudents(): LiveData<List<SubjectWithStudents>>
    //@Insert
    //fun insertStudentSubject2(student: Student, subject: Subject)

}
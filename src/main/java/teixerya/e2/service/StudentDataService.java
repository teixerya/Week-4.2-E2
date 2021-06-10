package teixerya.e2.service;

import teixerya.e2.model.StudentForm;

import java.util.List;

public interface StudentDataService {

    void insertStudentForm(StudentForm form);

    List<StudentForm> getAllStudentForms();

    void deleteAllStudentForms();

    void deleteStudentForm(int id);

    StudentForm getStudentForm(int id);

    void updateStudentForm(StudentForm form);
}

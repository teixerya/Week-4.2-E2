package teixerya.e2.service;

import teixerya.e2.model.StudentForm;

import java.util.List;

public interface CustomerDataService {

    void insertStudentForm(StudentForm form);

    List<StudentForm> getAllStudentForms();

    void deleteAllStudentForms();

    void deleteStudentForm(int customer_id);

    StudentForm getStudentForm(int customer_id);

    void updateStudentForm(StudentForm form);
}

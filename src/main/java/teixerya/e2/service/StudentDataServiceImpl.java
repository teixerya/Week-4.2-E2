package teixerya.e2.service;


import org.springframework.stereotype.Service;
import teixerya.e2.model.StudentForm;
import teixerya.e2.repository.StudentDataRepository;
import teixerya.e2.repository.StudentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentDataServiceImpl implements StudentDataService {

    private final StudentDataRepository studentDataRepository;

    StudentDataServiceImpl(StudentDataRepository studentDataRepository){
        this.studentDataRepository = studentDataRepository;
    }

    private static void copyFormToEntity(StudentForm form, StudentEntity customer){
        //customer.setId(form.getId());
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setProgramName(form.getProgramName());
        customer.setProgramYear(form.getProgramYear());
        customer.setProgramCoop(form.isProgramCoop());
        customer.setProgramInternship(form.isProgramInternship());
    }

    private static void copyEntityToForm(StudentEntity student, StudentForm form){
        form.setId(student.getId());
        form.setFirstName(student.getFirstName());
        form.setLastName(student.getLastName());
        form.setProgramName(student.getProgramName());
        form.setProgramYear(student.getProgramYear());
        form.setProgramCoop(student.isProgramCoop());
        form.setProgramInternship(student.isProgramInternship());
    }

    public void insertStudentForm(StudentForm form) {
        StudentEntity student = new StudentEntity();
        copyFormToEntity(form, student);
        student = studentDataRepository.save(student);
        form.setId(student.getId());
    }

    public List<StudentForm> getAllStudentForms() {
        List<StudentForm> formList = new ArrayList<>();
        List<StudentEntity> studentList = studentDataRepository.findAll();
        for(StudentEntity student: studentList){
            StudentForm form = new StudentForm();
            copyEntityToForm(student, form);
            formList.add(form);
        }
        return formList;
    }

    public void deleteAllStudentForms() {
        studentDataRepository.deleteAll();
    }

    public void deleteStudentForm(int id) {
        studentDataRepository.deleteById(id);
    }

    public StudentForm getStudentForm(int id) {
        Optional<StudentEntity> result = studentDataRepository.findById(id);
        if(result.isPresent()){
            StudentForm form = new StudentForm();
            StudentEntity student = result.get();
            copyEntityToForm(student, form);
            return form;
        }
        return null;
    }

    public void updateStudentForm(StudentForm form) {
        Optional<StudentEntity> result = studentDataRepository.findById(form.getId());
        if(result.isPresent()){
            StudentEntity student = result.get();
            copyFormToEntity(form, student);
            studentDataRepository.save(student);
            //studentRepository.flush();
        }
    }
}


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
        //customer.setCustomer_id(form.getCustomer_id());
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setEmail(form.getEmail());
        customer.setStreet(form.getStreet());
        customer.setCity(form.getCity());
        customer.setProgramInternship(form.isProgramInternship());
    }

    private static void copyEntityToForm(StudentEntity customer, StudentForm form){
        form.setCustomer_id(customer.getId());
        form.setFirstName(customer.getFirstName());
        form.setLastName(customer.getLastName());
        form.setEmail(customer.getEmail());
        form.setStreet(customer.getStreet());
        form.setProgramCoop(customer.isProgramCoop());
        form.setProgramInternship(customer.isProgramInternship());
    }

    public void insertStudentForm(StudentForm form) {
        StudentEntity student = new StudentEntity();
        copyFormToEntity(form, student);
        student = studentDataRepository.save(student);
        form.setCustomer_id(student.getId());
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

    public StudentForm getStudentForm(int customer_id) {
        Optional<StudentEntity> result = studentDataRepository.findById(customer_id);
        if(result.isPresent()){
            StudentForm form = new StudentForm();
            StudentEntity customer = result.get();
            copyEntityToForm(customer, form);
            return form;
        }
        return null;
    }

    public void updateStudentForm(StudentForm form) {
        Optional<StudentEntity> result = studentDataRepository.findById(form.getCustomer_id());
        if(result.isPresent()){
            StudentEntity customer = result.get();
            copyFormToEntity(form, customer);
            studentDataRepository.save(customer);
            //studentRepository.flush();
        }
    }
}


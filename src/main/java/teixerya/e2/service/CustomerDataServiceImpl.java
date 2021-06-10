package teixerya.e2.service;


import org.springframework.stereotype.Service;
import teixerya.e2.model.StudentForm;
import teixerya.e2.repository.CustomerDataRepository;
import teixerya.e2.repository.CustomerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerDataServiceImpl implements CustomerDataService {

    private final CustomerDataRepository customerDataRepository;

    CustomerDataServiceImpl(CustomerDataRepository customerDataRepository){
        this.customerDataRepository = customerDataRepository;
    }

    private static void copyFormToEntity(StudentForm form, CustomerEntity customer){
        //customer.setCustomer_id(form.getCustomer_id());
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setEmail(form.getEmail());
        customer.setStreet(form.getStreet());
        customer.setCity(form.getCity());
        customer.setState(form.getState());
    }

    private static void copyEntityToForm(CustomerEntity customer, StudentForm form){
        form.setCustomer_id(customer.getId());
        form.setFirstName(customer.getFirstName());
        form.setLastName(customer.getLastName());
        form.setEmail(customer.getEmail());
        form.setStreet(customer.getStreet());
        form.setCity(customer.getCity());
        form.setState(customer.getState());
    }

    public void insertStudentForm(StudentForm form) {
        CustomerEntity student = new CustomerEntity();
        copyFormToEntity(form, student);
        student = customerDataRepository.save(student);
        form.setCustomer_id(student.getId());
    }

    public List<StudentForm> getAllStudentForms() {
        List<StudentForm> formList = new ArrayList<>();
        List<CustomerEntity> studentList = customerDataRepository.findAll();
        for(CustomerEntity student: studentList){
            StudentForm form = new StudentForm();
            copyEntityToForm(student, form);
            formList.add(form);
        }
        return formList;
    }

    public void deleteAllStudentForms() {
        customerDataRepository.deleteAll();
    }

    public void deleteStudentForm(int id) {
        customerDataRepository.deleteById(id);
    }

    public StudentForm getStudentForm(int customer_id) {
        Optional<CustomerEntity> result = customerDataRepository.findById(customer_id);
        if(result.isPresent()){
            StudentForm form = new StudentForm();
            CustomerEntity customer = result.get();
            copyEntityToForm(customer, form);
            return form;
        }
        return null;
    }

    public void updateStudentForm(StudentForm form) {
        Optional<CustomerEntity> result = customerDataRepository.findById(form.getCustomer_id());
        if(result.isPresent()){
            CustomerEntity customer = result.get();
            copyFormToEntity(form, customer);
            customerDataRepository.save(customer);
            //studentRepository.flush();
        }
    }
}


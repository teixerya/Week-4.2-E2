package teixerya.e2.service;


import org.springframework.stereotype.Service;
import teixerya.e2.model.CustomerForm;
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

    private static void copyFormToEntity(CustomerForm form, CustomerEntity customer){
        //customer.setCustomer_id(form.getCustomer_id());
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setEmail(form.getEmail());
        customer.setStreet(form.getStreet());
        customer.setCity(form.getCity());
        customer.setState(form.getState());
        customer.setZipCode(form.getZipCode());
    }

    private static void copyEntityToForm(CustomerEntity customer, CustomerForm form){
        form.setCustomer_id(customer.getId());
        form.setFirstName(customer.getFirstName());
        form.setLastName(customer.getLastName());
        form.setEmail(customer.getEmail());
        form.setStreet(customer.getStreet());
        form.setCity(customer.getCity());
        form.setState(customer.getState());
        form.setZipCode(customer.getZipCode());
    }



    public List<CustomerForm> getAllCustomerForms() {
        List<CustomerForm> formList = new ArrayList<>();
        List<CustomerEntity> studentList = customerDataRepository.findAll();
        for(CustomerEntity student: studentList){
            CustomerForm form = new CustomerForm();
            copyEntityToForm(student, form);
            formList.add(form);
        }
        return formList;
    }



    public CustomerForm getCustomerForm(int customer_id) {
        Optional<CustomerEntity> result = customerDataRepository.findById(customer_id);
        if(result.isPresent()){
            CustomerForm form = new CustomerForm();
            CustomerEntity customer = result.get();
            copyEntityToForm(customer, form);
            return form;
        }
        return null;
    }



}


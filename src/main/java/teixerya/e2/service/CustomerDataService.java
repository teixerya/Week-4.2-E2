package teixerya.e2.service;

import teixerya.e2.model.CustomerForm;

import java.util.List;

public interface CustomerDataService {


    List<CustomerForm> getAllCustomerForms();

    CustomerForm getCustomerForm(int customer_id);


}

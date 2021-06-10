package teixerya.e2.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teixerya.e2.model.CustomerForm;
import teixerya.e2.service.CustomerDataService;

import java.util.List;


@Controller
    public class StudentDataController {

        private final Logger logger = LoggerFactory.getLogger(StudentDataController.class);



        private final CustomerDataService customerDataService;

        public StudentDataController(CustomerDataService customerDataService){
            this.customerDataService = customerDataService;
        }

        @GetMapping(value={"/", "/ListStudents"})
        public ModelAndView listStudents() {
            logger.trace("listStudents() is called");
            List<CustomerForm> list = customerDataService.getAllCustomerForms();
            return new ModelAndView("CustomerList",
                    "customers", list);
        }


        @GetMapping("CustomerDetails/{customer_id}")
        public String customerDetails(@PathVariable String customer_id, Model model){
            logger.trace("customerDetails() is called");
            try {
                CustomerForm form = customerDataService.getCustomerForm(Integer.parseInt(customer_id));
                if (form != null) {
                    model.addAttribute("customer", form);
                    return "CustomerDetails"; // show the customer data in the form to edit
                } else {
                    logger.trace("no data for this id=" + customer_id);
                    return "DataNotFound";
                }
            } catch (NumberFormatException e) {
                logger.trace("the id is missing or not an integer");
                return "DataNotFound";
            }
        }


    }


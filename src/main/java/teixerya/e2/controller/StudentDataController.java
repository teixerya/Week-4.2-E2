package teixerya.e2.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teixerya.e2.model.StudentForm;
import teixerya.e2.service.StudentDataService;

import java.util.List;


@Controller
    public class StudentDataController {

        private final Logger logger = LoggerFactory.getLogger(StudentDataController.class);

        private static final String[] programs = {
                "--- Select Program ---",
                "Computer Programmer", "Systems Technology",
                "Engineering Technician", "Systems Technician"};

        private final StudentDataService studentDataService;

        public StudentDataController(StudentDataService studentDataService){
            this.studentDataService = studentDataService;
        }




        @GetMapping(value={"/", "/ListStudents"})
        public ModelAndView listStudents() {
            logger.trace("listStudents() is called");
            List<StudentForm> list = studentDataService.getAllStudentForms();
            return new ModelAndView("CustomerList",
                    "customers", list);
        }


        @GetMapping("StudentDetails/{customer_id}")
        public String studentDetails(@PathVariable String customer_id, Model model){
            logger.trace("studentDetails() is called");
            try {
                StudentForm form = studentDataService.getStudentForm(Integer.parseInt(customer_id));
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


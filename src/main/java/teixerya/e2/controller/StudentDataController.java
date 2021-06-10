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

        @GetMapping(value={"/", "/Index"})
        public String index(){
            logger.trace("index() is called");
            return "Index";
        }

        @GetMapping("/AddStudent")
        public ModelAndView addStudent(){
            logger.trace("addStudent() is called");
            ModelAndView modelAndView =
                    new ModelAndView("AddStudent",
                            "form", new StudentForm());
            modelAndView.addObject("programs", programs);
            return modelAndView;
        }

        @PostMapping("/InsertStudent")
        public String insertStudent(
                @Validated @ModelAttribute("form") StudentForm form,
                BindingResult bindingResult,
                Model model){
            logger.trace("insertStudent() is called");
            // checking for the input validation errors
            if (bindingResult.hasErrors()) {
                logger.trace("input validation errors");
                //model.addAttribute("form", form);
                model.addAttribute("programs", programs);
                return "AddStudent";
            } else {
                logger.trace("the user inputs are correct");
                studentDataService.insertStudentForm(form);
                return "redirect:ConfirmInsert/" + form.getCustomer_id();
            }
        }

        @GetMapping("/ConfirmInsert/{customer_id}")
        public String confirmInsert(@PathVariable(name = "customer_id") String strId, Model model){
            logger.trace("confirmInsert() is called");
            try {
                int customer_id = Integer.parseInt(strId);
                logger.trace("looking for the data in the database");
                StudentForm form = studentDataService.getStudentForm(customer_id);
                if (form == null) {
                    logger.trace("no data for this id=" + customer_id);
                    return "DataNotFound";
                } else {
                    logger.trace("showing the data");
                    model.addAttribute("form", form);
                    return "ConfirmInsert";
                }
            } catch (NumberFormatException e) {
                logger.trace("the id in not an integer");
                return "DataNotFound";
            }
        }

        @GetMapping("/ListStudents")
        public ModelAndView listStudents() {
            logger.trace("listStudents() is called");
            List<StudentForm> list = studentDataService.getAllStudentForms();
            return new ModelAndView("ListStudents",
                    "customers", list);
        }

        @GetMapping("/DeleteAll")
        public String deleteAll(){
            logger.trace("deleteAll() is called");
            studentDataService.deleteAllStudentForms();
            return "redirect:ListStudents";
        }

        @GetMapping("StudentDetails/{customer_id}")
        public String studentDetails(@PathVariable String customer_id, Model model){
            logger.trace("studentDetails() is called");
            try {
                StudentForm form = studentDataService.getStudentForm(Integer.parseInt(customer_id));
                if (form != null) {
                    model.addAttribute("customer", form);
                    return "StudentDetails"; // show the customer data in the form to edit
                } else {
                    logger.trace("no data for this id=" + customer_id);
                    return "DataNotFound";
                }
            } catch (NumberFormatException e) {
                logger.trace("the id is missing or not an integer");
                return "DataNotFound";
            }
        }

        // a user clicks "Delete" link (in the table) to "DeleteStudent"
        @GetMapping("/DeleteStudent")
        public String deleteStudent(@RequestParam String customer_id, Model model) {
            logger.trace("deleteStudent() is called");
            try {
                StudentForm form = studentDataService.getStudentForm(Integer.parseInt(customer_id));
                if (form != null) {
                    model.addAttribute("customer", form);
                    return "DeleteStudent"; // ask "Do you really want to remove?"
                } else {
                    return "redirect:ListStudents";
                }
            } catch (NumberFormatException e) {
                return "redirect:ListStudents";
            }
        }

        // a user clicks "Remove Record" button in "DeleteStudent" page,
        // the form submits the data to "RemoveStudent"
        @PostMapping("/RemoveStudent")
        public String removeStudent(@RequestParam String customer_id) {
            logger.trace("removeStudent() is called");
            try {
                studentDataService.deleteStudentForm(Integer.parseInt(customer_id));
            } catch (NumberFormatException e) {
                logger.trace("the id is missing or not an integer");
            }
            return "redirect:ListStudents";
        }

        // a user clicks "Edit" link (in the table) to "EditStudent"
        @GetMapping("/EditStudent")
        public String editStudent(@RequestParam String customer_id, Model model) {
            logger.trace("editStudent() is called");
            try {
                StudentForm form = studentDataService.getStudentForm(Integer.parseInt(customer_id));
                if (form != null) {
                    model.addAttribute("form", form);
                    model.addAttribute("programs", programs);
                    return "EditStudent";
                } else {
                    logger.trace("no data for this id=" + customer_id);
                    return "redirect:ListStudents";
                }
            } catch (NumberFormatException e) {
                logger.trace("the id is missing or not an integer");
                return "redirect:ListStudents";
            }
        }

        // the form submits the data to "UpdateStudent"
        @PostMapping("/UpdateStudent")
        public String updateStudent(
                @Validated @ModelAttribute("form") StudentForm form,
                BindingResult bindingResult,
                Model model) {
            logger.trace("updateStudent() is called");
            // checking for the input validation errors
            if (bindingResult.hasErrors()) {
                logger.trace("input validation errors");
                //model.addAttribute("form", form);
                model.addAttribute("programs", programs);
                return "EditStudent";
            } else {
                logger.trace("the user inputs are correct");
                studentDataService.updateStudentForm(form);
                logger.debug("id = " + form.getCustomer_id());
                return "redirect:StudentDetails/" + form.getCustomer_id();
            }
        }
    }


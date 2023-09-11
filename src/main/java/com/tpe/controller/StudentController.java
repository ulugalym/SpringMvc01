package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
//class level: clastaki tum method'lar icin gecerli
//method level: sadece o method icin gecerli
public class StudentController {
    @Autowired
    private StudentService service;

    //controller'den mav(veri+view dosyasinin ismi) veya String olarak view name i donebiliriz.

    @GetMapping("/hi")//http://localhost:8080/SpringMvc/students/hi + GET
    public ModelAndView sayHi(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message","Hi;");
        mav.addObject("messagebody","I am a Student Management System");
        mav.setViewName("hi");
        return mav;
    }

    //view resolver: hi.jsp dosyasini views klasorinde bulur
    // ve mav icindeki datayi bind eder.

    @GetMapping("/new")//http://localhost:8080/SpringMvc/students/new + GET
    public String sendStudentForm(@ModelAttribute("student")Student student){
        return "studentForm";
    }
    //@ModelAttribute studentForm'daki "student" modelinin controller'e aktarilmasini saglar.




    //save/create student:response olarak tum studentleri gosterelim
    @PostMapping("/saveStudent")//http://localhost:8080/SpringMvc/students/saveStudent + POST
    public String createStudent(@Valid @ModelAttribute Student student, BindingResult bindingResult){

        //validasyon hatasi varsa tekrar formu goster
        if(bindingResult.hasErrors()){
            return "studentForm";
        }

        //validasyon hatasi yoksa
        service.saveStudent(student);


        return "redirect:/students"; //http://localhost:8080/SpringMvc/students/  tekrar bu linke yonlendirir
    }




    //tum studentleri listeleme
    @GetMapping//http://localhost:8080/SpringMvc/students/ + GET
    public ModelAndView getStudents(){
        List<Student>allStudent=service.getAllStudent();

        ModelAndView mav=new ModelAndView();
        mav.addObject("studentList",allStudent);
        mav.setViewName("students");

        return mav;
    }

    //@RequestParam: Bu annotation, bir method'un cagrilmasi
    // sirasinda istekle ile gonderilen bir query parameter'yi almarini saglar.

    //update
    @GetMapping("/update")//http://localhost:8080/SpringMvc/students/update?id=1 + GET
    public ModelAndView ShowStudentForm(@RequestParam("id")Long id){
        Student foundStudent=service.findStudentById(id);
        ModelAndView mav=new ModelAndView();
        mav.addObject("student",foundStudent);
        mav.setViewName("studentForm");

        return mav;
    }


    //@PathVariable: request icindeki path parameter'sinin degerini method'un parameter'si olarak
    //almamizi saglar.
    //delete;tum studentleri gosterelim.
    @GetMapping("/delete/{id}")//http://localhost:8080/SpringMvc/students/delete/1 + GET
    public String deleteStudent(@PathVariable("id")Long id){
        service.deleteStudent(id);

        return "redirect:/students";
    }


    //try-catch'in catch blogu gibi calisir
    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleNotFoundException(Exception ex){
        ModelAndView mav=new ModelAndView();
        mav.addObject("message",ex.getMessage());
        mav.setViewName("notFound");
        return mav;
    }


}

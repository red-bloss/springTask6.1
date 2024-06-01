package com.program.spring2.controller;

import com.program.spring2.entities.ApplicationRequest;
import com.program.spring2.repository.ApplicationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    ApplicationsRepository applicationsRepository;

    @GetMapping(value = "/")
    public String getRequests(Model model){
        List<ApplicationRequest> apps = applicationsRepository.findAll();
        model.addAttribute("apps", apps);
        return "index";
    }

    @GetMapping(value = "/newRequests")
    public String getNewRequests(Model model){
        List<ApplicationRequest> apps = applicationsRepository.findByHandledIsFalse();
        model.addAttribute("apps", apps);
        return "index";
    }

    @GetMapping(value = "/handledRequests")
    public String getHandledRequests(Model model){
        List<ApplicationRequest> apps = applicationsRepository.findByHandledIsTrue();
        model.addAttribute("apps", apps);
        return "index";
    }


    @GetMapping(value = "/addRequest")
    public String addStudent(){
        return "addRequest";
    }

    @PostMapping(value = "/addRequest")
    public String addStudent(@RequestParam(name = "user_name") String userName,
                             @RequestParam(name = "course_name") String courseName,
                             @RequestParam(name = "phone") String phone,
                             @RequestParam(name = "commentary") String commentary){
        ApplicationRequest request = new ApplicationRequest();
        request.setUserName(userName);
        request.setCourseName(courseName);
        request.setCommentary(commentary);
        request.setPhone(phone);
        request.setHandled(false);
        applicationsRepository.save(request);
        return "redirect:/";
    }
    @GetMapping(value = "/details/{id}")
    public String details(@PathVariable(name = "id") Long id,
                          Model model){
        ApplicationRequest request = applicationsRepository.findById(id).orElseThrow();
        model.addAttribute("request", request);
        return "details";
    }

    @PostMapping(value = "/saveRequest")
    public String saveRequest(@RequestParam(name = "id") Long id){
        ApplicationRequest request = applicationsRepository.findById(id).orElseThrow();
        request.setHandled(true);
        applicationsRepository.save(request);
        return "redirect:/";
    }

    @PostMapping(value = "/deleteRequest")
    public String deleteRequest(@RequestParam(name = "id") Long id){
        ApplicationRequest request = applicationsRepository.findById(id).orElseThrow();
        applicationsRepository.delete(request);
        return "redirect:/";
    }
}

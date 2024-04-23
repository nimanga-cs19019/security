package com.example.security.Controller;

import com.example.security.Model.Admin;
import com.example.security.Service.Impl.AdminServiceImpl;
import com.example.security.dto.adminDto;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class LoginController {
    private AdminServiceImpl adminService;
    private PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String loginForm(org.springframework.ui.Model model){
        model.addAttribute("title","Login Admin");

        return "login";
    }
    @RequestMapping("/index")
    public String home(org.springframework.ui.Model model)
    {   model.addAttribute("title","Home Page");
        return"index";
    }
    @GetMapping("/register")
    public adminDto addAttribute() {
        return new adminDto();
    }
    public String register(org.springframework.ui.Model model) {
        model.addAttribute("title","Register");
        model.addAttribute("adminDto", new adminDto());
        return "register";
    }
    @GetMapping("/forgot-password")
    public String forgotPassword(Model model)
    {    model.addAttribute("title","Forgot Password");
        return "forgot-password";
    }
    @PostMapping("/register-new")
    public String addNewAdmin(@Valid  @ModelAttribute("adminDto")adminDto adminDto,
                              BindingResult result,
                              org.springframework.ui.Model model
                               ){
        try {

            if (result.hasErrors())
            {
                model.addAttribute("adminDto", new adminDto());
                result.getModel();
                return "register";
            }
            String username=adminDto.getUsername();
            Admin admin=adminService.findByUsername(username);
            if(admin != null)
            {
                model.addAttribute("adminDto", adminDto);
                 System.out.println("admin not null");
                 model.addAttribute("emailError","Your email has been registered!");
               return"/register";
            }
            if(adminDto.getPassword().equals(adminDto.getRepeatPassword()))
            {   adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                System.out.println("success");
                model.addAttribute("success","Register successfully");
                model.addAttribute("adminDto",adminDto);
              }else {
                model.addAttribute("adminDto",adminDto);
                System.out.println("password not same");
                model.addAttribute("passwordError","Wrong password!Check again.");
                return "redirect:/register";
            }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                model.addAttribute("errors","The server has been wrong!");
            }
             return "register";
    }
}

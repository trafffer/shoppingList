package com.ex.prep.web;

import com.ex.prep.model.binding.UserLoginBindingModel;
import com.ex.prep.model.binding.UserRegisterBindingModel;
import com.ex.prep.model.constant.Constant;
import com.ex.prep.model.service.UserServiceModel;
import com.ex.prep.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {
     private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/login")
    public String login(Model model){
        if (!model.containsAttribute("userModel")){
            model.addAttribute("userModel",new UserLoginBindingModel());
            model.addAttribute("notFound",false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirmed(@Valid UserLoginBindingModel userModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userModel",userModel);
            redirectAttributes.addFlashAttribute(Constant.BINDING+"userModel",bindingResult);
            return "redirect:login";
        }
        UserServiceModel userServiceModel = userService.findUserByUsernameAndPassword(userModel.getUsername(),
                userModel.getPassword());
        if (userServiceModel==null){
            redirectAttributes.addFlashAttribute("userModel",userModel);
            redirectAttributes.addFlashAttribute("notFound",true);
            return "redirect:login";
        }
    userService.login(userServiceModel);
    return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model){
        if (!model.containsAttribute("userModel")){
            model.addAttribute("userModel",new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirmed(@Valid UserRegisterBindingModel userModel,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes ){
        if (bindingResult.hasErrors()|| !userModel.setEqualPassword()){
            redirectAttributes.addFlashAttribute("userModel",userModel);
            redirectAttributes.addFlashAttribute(Constant.BINDING+"userModel",bindingResult);
            return "redirect:register";
        }
            userService.registerUser(userModel);
            return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout(){
        userService.logout();
        return "redirect:/";
    }
}

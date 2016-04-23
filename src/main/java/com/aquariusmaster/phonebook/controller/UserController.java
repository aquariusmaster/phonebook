package com.aquariusmaster.phonebook.controller;

import com.aquariusmaster.phonebook.entity.User;
import com.aquariusmaster.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by harkonnen on 20.04.16.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/denied")
    public String noaccess() {
        return "denied";
    }

    @RequestMapping("/login")
    public String signIn(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error!=null) model.addAttribute("loginError", true);

        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String signUp(Model model, @Valid User user, BindingResult result) {

        if(result.hasErrors()) {
            System.out.println(result);
            return "register";
        }

        user.setAuthority("ROLE_USER");
        user.setEnabled(true);

        if(userService.exists(user.getUsername())) {
            result.rejectValue("username", "DuplicateKey.user.username");
            return "register";
        }
        try {
            userService.save(user);
        } catch (DuplicateKeyException e) {
            result.rejectValue("username", "DuplicateKey.user.username");
            return "register";
        }

        return "reg-success";
    }


}

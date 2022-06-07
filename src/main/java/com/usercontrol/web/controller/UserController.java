package com.usercontrol.web.controller;

import com.usercontrol.web.entity.User;
import com.usercontrol.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/get/{id}")
    public String getUserById(@PathVariable long id, Model model) {
        User user = userService.getById(id);

        if (user == null) {
            return "redirect:/users/get";
        }

        model.addAttribute("users", new User[]{user});
        model.addAttribute("new", new User());
        model.addAttribute("back", true);

        return "users";
    }

    @GetMapping(value = "/get")
    public String getAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("new", new User());
        model.addAttribute("back", false);

        return "users";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteUserById(@PathVariable long id) {
        userService.removeById(id);

        return "redirect:/users/get";
    }

    @PostMapping(value = "/save")
    public String saveUser(@ModelAttribute User user) {
        userService.save(user);

        return "redirect:/users/get";
    }
}

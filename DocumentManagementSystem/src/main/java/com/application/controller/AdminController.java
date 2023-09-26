package com.application.controller;

import com.application.entity.User;
import com.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user/users";
    }

    @GetMapping("/admin/users/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/userById";
    }

    @GetMapping("/admin/users/{id}/delete")
    public String deleteUserForm(@PathVariable int id, Model model) {
        model.addAttribute("userId", id);
        return "user/deleteUser";
    }

    @PostMapping("/admin/users/delete")
    public String deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
        return "redirect:/main";
    }

    @GetMapping("/admin/users/{id}/update")
    public String updateUserForm(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/updateUser";
    }

    @PutMapping("/admin/users/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/main";
    }

    @GetMapping("/admin/users/new")
    public String saveUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/saveUser";
    }

    @PostMapping("/admin/users")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/main";
    }
}

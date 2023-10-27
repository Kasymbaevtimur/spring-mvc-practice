package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.User;
import peaksoft.service.impl.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "user/save";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:find-all";
    }

    @GetMapping("/find-all")
    public String findAll(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "user/get-all";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user/update";
    }

    @PostMapping("{id}")
    public String saveUpdate(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        userService.update(id, user);
        return "redirect:find-all";
    }



}

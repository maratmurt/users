package ru.skillbox.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.skillbox.users.model.User;
import ru.skillbox.users.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.showAll());
        return "index";
    }

    @GetMapping("/user/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "edit";
    }

    @PostMapping("/user/create")
    public String createUser(@ModelAttribute User user) {
        userService.create(user);
        return "redirect:/";
    }

    @GetMapping("/user/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PostMapping("/user/edit")
    public String updateUser(@ModelAttribute User user) {
        userService.update(user);
        return "redirect:/";
    }

    @GetMapping("user/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.delete(id);
        return "redirect:/";
    }

}

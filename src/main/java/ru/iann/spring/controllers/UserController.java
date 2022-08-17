package ru.iann.spring.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.iann.spring.storage.DAO;
import ru.iann.spring.model.User;

@Controller
@RequestMapping("/users")
public class UserController {

    private final DAO userDAO;

    @Autowired
    public UserController(DAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String start(){
        return "users/start";
    }


    @GetMapping("/show")
    public String show(Model model){
        model.addAttribute("users", userDAO.show());
        return "users/show";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable long id){
        model.addAttribute("user", userDAO.getById(id));
        return "users/user-by-id";
    }

    @GetMapping("/new")
    public String addUserForm(Model model){
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult result){
        if (result.hasErrors()){
            return "users/new";
        }
        userDAO.create(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/change")
    public String updateForm(Model model, @PathVariable long id){
        model.addAttribute("user", userDAO.getById(id));
        return "users/change";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, @PathVariable("id") long id, BindingResult result){
        if (result.hasErrors()){
            return "users/change";
        }

        userDAO.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id){
        userDAO.delete(id);
        return "redirect:/users";
    }


}

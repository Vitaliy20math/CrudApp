package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDao;
import web.models.User;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersControllers {

    private UserDao userDao;
    @Autowired
    public UsersControllers(UserDao userDao) {
        this.userDao = userDao;
    }
    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm CRUD application");
        messages.add("0.0.1 version by feb'23 ");
        model.addAttribute("messages", messages);
        return "index";
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDao.index());
        return "pages/listUsers";
    }
    @GetMapping("/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
        model.addAttribute("userId", userDao.getUser(id));
        return "pages/userById";
    }
    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("user", new User());
        return "pages/form_for_new_user";
    }
    @PostMapping()
    public String create(@ModelAttribute("user") User user){
        userDao.save(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.getUser(id));
        return "pages/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDao.update(id, user);
        return "redirect:/users/{id}";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.delete(id);
        return "redirect:/users";
    }




}

package me.momarija.bioui.controllers.admin;

import me.momarija.bioui.domains.User;
import me.momarija.bioui.service.AdminUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/users/")
public class AdminUserController {

    @Autowired
    private AdminUsersService adminUsersService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showUsers(Model model) {
        model.addAttribute("title", "Liste des utilisateurs");
        model.addAttribute("users", adminUsersService.getUsers());
        return "admin/UserList";
    }

    @RequestMapping(value = "{id}/delete/", method = RequestMethod.POST)
    public String deleteUser(@PathVariable int id) {
        adminUsersService.deleteUser(id);
        return "redirect:/admin/users/";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("title", "Ajouter un utilisateur");
        model.addAttribute("user", new User());
        model.addAttribute("roles", adminUsersService.getRoles());
        return "admin/UserForm";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(Model model, @Valid User user, BindingResult bindingResult,
                          @RequestParam(required = false) int[] roles) {
        if (bindingResult.hasErrors() || roles == null) {
            model.addAttribute("title", "Erreur");
            if (roles == null)
                model.addAttribute("erreurRole", true);
            model.addAttribute("roles", adminUsersService.getRoles());
            return "admin/UserForm";
        }

        adminUsersService.addUser(user, roles);
        return "redirect:/admin/users/";
    }

}

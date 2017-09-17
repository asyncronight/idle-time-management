package me.momarija.bioui.service.impl;

import me.momarija.bioui.domains.Role;
import me.momarija.bioui.domains.User;
import me.momarija.bioui.repos.RoleRepo;
import me.momarija.bioui.repos.UserRepo;
import me.momarija.bioui.service.AdminUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AdminUsersServiceImpl implements AdminUsersService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Override
    public User addUser(User user, int[] roles) {
        List<Role> list = new ArrayList<>();
        for (int i : roles) {
            list.add(roleRepo.findOne(i));
        }
        user.setRoles(list);
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(int idUser) {
        User user = userRepo.findOne(idUser);
        if (user == null) {
            throw new RuntimeException("Suppression echouée, utilisateur introuvable");
        }
        userRepo.delete(idUser);
    }

    @Override
    public List<User> getUsers() {
        List<User> list = userRepo.findAll();
        if (list == null) list = new ArrayList<User>();
        return list;
    }

    @Override
    public List<Role> getRoles() {
        List<Role> list = roleRepo.findAll();
        if (list == null) list = new ArrayList<Role>();
        return list;
    }

}

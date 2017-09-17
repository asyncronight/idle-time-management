package me.momarija.bioui.service;


import me.momarija.bioui.domains.Role;
import me.momarija.bioui.domains.User;

import java.util.List;

public interface AdminUsersService {

    User addUser(User user, int[] roles);

    void deleteUser(int idUser);

    List<User> getUsers();

    List<Role> getRoles();
}

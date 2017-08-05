package me.momarija.bioui.domains;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty(message = "Le nom d'utilisateur ne peut pas être vide")
    @Length(min = 3, max = 20, message = "Le nom d'utilisateur entre 3 et 20 caractères")
    private String username;

    @NotEmpty(message = "Le mot de passe ne peut pas être vide")
    @Length(min = 6, max = 30, message = "Le mot de passe entre 3 et 20 caractères")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

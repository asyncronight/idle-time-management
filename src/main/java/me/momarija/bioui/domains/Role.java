package me.momarija.bioui.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue
	private Integer id;

    private String role;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

	public Integer getId() {
		return id;
    }

	public void setId(Integer id) {
		this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

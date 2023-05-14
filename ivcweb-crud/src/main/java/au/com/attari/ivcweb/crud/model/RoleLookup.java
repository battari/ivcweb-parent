package au.com.attari.ivcweb.crud.model;

import javax.persistence.*;

@Entity
@Table(name = "role_lookup")
public class RoleLookup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String role;

    public RoleLookup() {
    }

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
}
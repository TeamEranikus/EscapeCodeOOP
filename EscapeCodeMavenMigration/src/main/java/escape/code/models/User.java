package escape.code.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable{
    private String name;
    private String password;
    private Long id;
    private Long score;

    public User(){
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue()
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}

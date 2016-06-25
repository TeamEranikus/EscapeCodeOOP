package escape.code.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "items")
public class Item implements Serializable{
    private Long id;
    private String type;

    public Item(){

    }

    @Id
    @GeneratedValue()
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

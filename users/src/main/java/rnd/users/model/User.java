package rnd.users.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String First_name;
    private String Last_name;
/*
    private Blob Photo;
*/
    private String Position;

    protected User() {

    }

    public User(long id, String First_name, String Last_name, Blob Photo, String Position) {
        this.id = id;
        this.First_name = First_name;
        this.Last_name = Last_name;
/*
        this.Photo = Photo;
*/
        this.Position = Position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

/*
    public Blob getPhoto() {
        return Photo;
    }

    public void setPhoto(Blob photo) {
        Photo = photo;
    }
*/

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", FirstName='" + First_name + '\'' +
                ", LastName='" + Last_name + '\'' +
                ", Position='" + Position +
                '}';
    }
}


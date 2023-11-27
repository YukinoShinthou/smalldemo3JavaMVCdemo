package kendrick.Xml.Person;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message="Invalid Name")
    @Size(min=2,max=30,message="From 2 to 30 characters")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Invalid Email")
    @Email(message = "Invalid Email")
    @Column(name = "email")
    private String email;
    @Min(value = 5,message = "Age should be above 5")
    @Column(name = "age")
    private int age;

    public Person() {
    }



    public Person( String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

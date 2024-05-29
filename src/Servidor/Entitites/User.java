package Servidor.Entitites;

public class User extends BaseEntity {
    public String Name;
    public String Email;
    public String Password;

    public User(String name, String email, String password) {
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }

    public User(String email, String password) {
        this.Email = email;
        this.Password = password;
    }

    public User() {

    }
}

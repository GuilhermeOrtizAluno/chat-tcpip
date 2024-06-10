package Servidor.Entitites;

public class User extends BaseEntity {
    public String Email;
    public String Password;

    public User(String email, String password) {
        this.Email = email;
        this.Password = password;
    }

    public User() {

    }
}

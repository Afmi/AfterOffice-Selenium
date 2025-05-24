package pojo;

public class RegisterRequest {
    public String email;
    public String password;
    public String full_name;
    public String department;
    public String title;

    public RegisterRequest(String email, String password, String fullName, String department, String title) {
        this.email = email;
        this.password = password;
        this.full_name = fullName;
        this.department = department;
        this.title = title;
    }
}

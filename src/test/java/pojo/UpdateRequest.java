package pojo;

public class UpdateRequest {
    public String email;
    public String password;
    public String full_name;
    public String department;
    public String title;

    public UpdateRequest(String email, String password, String full_name, String department, String title) {
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        this.department = department;
        this.title = title;
    }

    public UpdateRequest() {
    }
}

package tr.com.huseyinaydin.dto;

import lombok.Data;

@Data
public class AppUserRegisterDto {
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
}

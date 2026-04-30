package tr.com.huseyinaydin.dto;

import lombok.Data;

@Data
public class AppUserUpdateDto {
    private String name;
    private String surname;
    private String email;
    private String district;
    private String city;
    private String imageUrl;
    private String password;
}

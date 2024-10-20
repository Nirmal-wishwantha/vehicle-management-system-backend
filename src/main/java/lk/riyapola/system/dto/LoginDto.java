package lk.riyapola.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    private Integer id;
    private String email;
    private String massage;
    private String token;

    public LoginDto(String email, String massage, String token) {
        this.email = email;
        this.massage = massage;
        this.token = token;
    }
}

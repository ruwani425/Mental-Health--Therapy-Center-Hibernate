package lk.ijse.gdse.SerenityMentalHealthTherapyCenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private String role;
    private String username;
    private String password;

    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserDTO(int userId,String finalUserName, String newPassword) {
        this.userId = userId;
        this.username = finalUserName;
        this.password = newPassword;
    }
}

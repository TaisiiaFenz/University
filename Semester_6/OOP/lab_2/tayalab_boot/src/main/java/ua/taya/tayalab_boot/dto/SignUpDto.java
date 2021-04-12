package ua.taya.tayalab_boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SignUpDto {
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String passport;
    private LocalDate birthday;
}

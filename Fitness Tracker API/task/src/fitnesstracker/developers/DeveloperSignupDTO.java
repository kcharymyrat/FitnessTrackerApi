package fitnesstracker.developers;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DeveloperSignupDTO(
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email cannot be empty")
        String email,

        @NotBlank(message = "Password cannot be blank")
        String password
) {

}

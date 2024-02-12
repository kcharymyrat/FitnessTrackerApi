package fitnesstracker.applications;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApplicationRegisterDTO(
        @NotBlank(message = "Application name cannot be empty")
        String name,

        @NotNull
        String description
) { }

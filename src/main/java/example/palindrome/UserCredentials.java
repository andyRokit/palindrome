package example.palindrome;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserCredentials {
    private String username;

    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
    private String value;
}

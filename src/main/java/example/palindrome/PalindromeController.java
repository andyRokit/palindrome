package example.palindrome;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PalindromeController {
    private final PalindromeService palindromeService;

    @PostMapping("/")
    public boolean isPalindrome(@Valid @RequestBody UserCredentials userCredentials) {
        return palindromeService.isPalindrome(userCredentials.getValue());
    }
}

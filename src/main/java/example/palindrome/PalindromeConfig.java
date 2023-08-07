package example.palindrome;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class PalindromeConfig {
    @Value("${palindrome.store.path}")
    private String palindromeStorePath;

    @Bean
    public CachingHashSet palindromeStore() {
        File storeFile = new File(palindromeStorePath);
        return new CachingHashSet(storeFile);
    }
}

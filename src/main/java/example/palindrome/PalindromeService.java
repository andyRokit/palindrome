package example.palindrome;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PalindromeService {
    private final CachingHashSet palindromeStore;

    public boolean isPalindrome(final String value) {
        if(matchesCachedPalindrome(value)) {
            log.debug("Cache hit for " + value);
            return true;
        }

        if(calculateIsPalindrome(value)) {
            log.debug("Match for " + value);
            palindromeStore.add(value);
            return true;
        }

        log.debug("No match for " + value);
        return false;
    }

    private boolean matchesCachedPalindrome(final String value) {
        return palindromeStore.contains(value);
    }

    private boolean calculateIsPalindrome(final String value) {
        int left = 0;
        int right = value.length() - 1;

        while (left < right) {
            if (value.charAt(left) != value.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}

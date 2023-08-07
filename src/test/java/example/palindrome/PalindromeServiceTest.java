package example.palindrome;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PalindromeServiceTest {
    @InjectMocks
    private PalindromeService underTest;

    @Mock
    private CachingHashSet palindromeStore;


    @Test
    public void trueIfCacheHit() {
        when(palindromeStore.contains("bob")).thenReturn(true);
        assertThat(underTest.isPalindrome("bob"), is(true));
    }

    @Test
    public void trueIfCacheMissAndMatchOdd() {
        when(palindromeStore.contains("bob")).thenReturn(false);
        assertThat(underTest.isPalindrome("bob"), is(true));
    }

    @Test
    public void trueIfCacheMissAndMatchEven() {
        when(palindromeStore.contains("abba")).thenReturn(false);
        assertThat(underTest.isPalindrome("abba"), is(true));
    }

    @Test
    public void falseIfCacheMissAndNoMatch() {
        when(palindromeStore.contains("dan")).thenReturn(false);
        assertThat(underTest.isPalindrome("dan"), is(false));
    }
}

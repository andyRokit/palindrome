package example.palindrome;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CachingStringSetStoreTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void cacheLoadsWhenFileExists() {
        File preexistingCache = new File("src/test/resources/sample1.txt");
        CachingHashSet underTest = new CachingHashSet(preexistingCache);
        assertThat(underTest, hasItems("apples", "pears", "bananas"));
    }

    @Test
    public void emptySetWhenNoFileExists() {
        File preexistingCache = new File("doesntexist");
        CachingHashSet underTest = new CachingHashSet(preexistingCache);
        assertThat(underTest, is(empty()));
    }

    @Test
    public void flushAndLoad() throws Exception {
        File newCache = temp.newFile();

        CachingHashSet writeStore = new CachingHashSet(newCache);
        assertThat(writeStore, is(empty()));
        writeStore.add("oranges");
        writeStore.add("strawberries");
        writeStore.flush();

        CachingHashSet readStore = new CachingHashSet(newCache);
        assertThat(readStore, hasItems("oranges", "strawberries"));
    }
}
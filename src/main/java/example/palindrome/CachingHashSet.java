package example.palindrome;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.*;
import java.util.HashSet;

@Slf4j
public class CachingHashSet extends HashSet<String> {
    private final File store;

    public CachingHashSet(
            File store
    ) {
        this.store = store;
        populateSetFromStore();
    }

    private void populateSetFromStore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(store))) {
            String line;
            while ((line = reader.readLine()) != null) {
                this.add(line);
            }
        } catch (FileNotFoundException e) {
            // Ignore.  Proceed without populating cache.
            log.info("No cache found.");
        } catch (IOException e) {
            // Ignore.  Proceed without populating cache.
            log.error("Error reading from cache", e);
        }
    }

    @Scheduled(fixedDelay = 5000) // Run this method every 5 seconds
    public void flush() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(store))) {
            for(String element : this) {
                writer.write(element);
                writer.newLine();
            }
        } catch (IOException e) {
            // Ignore.  Proceed without caching.
            log.error("Error writing to cache", e);
        }
    }
}

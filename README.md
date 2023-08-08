# Palindrome Inspector

Develop a REST API to accept a request containing a username and text value. The
program will process the request and return a response indicating whether the value
is a palindrome.
A palindrome is a word, phrase, or sequence that reads the
same backwards as forwards, such as madam or civic.

### Requirements:
1. Validate the input. Reject values with numbers, spaces and punctuation.
2. Improve performance by caching previously processed values.
3. Persist the processed value using the file system or database.
4. Populate the cache on startup from the persisted values.
5. Include appropriate functional and non-functional tests. (ie unit and integration testing/performance profiling).

## Assumptions
- The palindrome is supposed to represent a much more processing intensive process.  Otherwise the cache would be unlikely to pay for itself in a production scenario.
- A large variety is palindromes may result in high numbers of cache writes per second.
- The number of non-palindrome values will be significantly larger than palindromes

## Design

### Caching
The cache of known palindromes is implemented by a simple hashset which is loaded from a specified file then persisted to disk periodically.
The mechanism makes no effort to either address race conditions or constantly keep the persisted version up to date.
This is due to the fact that it is intended as a cache, where a few cache misses is an acceptable outcome.

The flush interval (of 5 seconds) is intended to demonstrate a better alternative that performing I/O with every write.

### Improved
The current file-based implementation could be swapped out for a database version, but it may still make sense to maintain the delayed flush strategy to avoid lots of tiny I/O writes.
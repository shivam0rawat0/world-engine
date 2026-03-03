import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class BytecodeCache {
    // Inner class to represent an entry in the cache
    private static class CacheEntry {
        byte[] bytecode;
        long expirationTime;

        CacheEntry(byte[] bytecode, long ttl) {
            this.bytecode = bytecode;
            this.expirationTime = System.currentTimeMillis() + ttl;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expirationTime;
        }
    }

    private final Map<String, CacheEntry> cache;
    private final long defaultTTL;

    // Constructor to initialize the cache with a default TTL
    public BytecodeCache(long defaultTTL, TimeUnit timeUnit) {
        this.cache = new HashMap<>();
        this.defaultTTL = timeUnit.toMillis(defaultTTL);
    }

    // Method to add or update an entry in the cache
    public void put(String className, byte[] bytecode) {
        cache.put(className, new CacheEntry(bytecode, defaultTTL));
    }

    // Method to retrieve an entry from the cache
    public byte[] get(String className) {
        CacheEntry entry = cache.get(className);
        if (entry == null || entry.isExpired()) {
            cache.remove(className);
            return null; // Entry not found or expired
        }
        return entry.bytecode;
    }

    // Method to invalidate expired entries
    public void invalidateExpiredEntries() {
        Iterator<Map.Entry<String, CacheEntry>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, CacheEntry> entry = iterator.next();
            if (entry.getValue().isExpired()) {
                iterator.remove(); // Remove expired entry
            }
        }
    }

    // Method to forcefully invalidate a specific entry
    public void invalidate(String className) {
        cache.remove(className);
    }
}
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Task 2: String Character Frequency
 * Counts character occurrences in order of first appearance.
 *
 * Assumptions:
 * - Case sensitive (h != H)
 * - Spaces are counted
 * - All characters including special chars are counted
 *
 * Example: "hello world" -> h:1, e:1, l:3, o:2, :1, w:1, r:1, d:1
 */
public class StringCharFrequency {

    public static String countCharacters(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // LinkedHashMap preserves insertion order (first appearance)
        Map<Character, Integer> frequency = new LinkedHashMap<>();

        for (char c : input.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
            if (result.length() > 0) result.append(", ");
            result.append(entry.getKey()).append(":").append(entry.getValue());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(countCharacters("hello world"));
        System.out.println(countCharacters(""));
        System.out.println(countCharacters(null));
        System.out.println(countCharacters("aAbBcC"));
        System.out.println(countCharacters("!!!"));
    }
}
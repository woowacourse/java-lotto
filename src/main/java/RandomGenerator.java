import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomGenerator {
    private RandomGenerator() {
    }

    public static List<Integer> generateUniqueRandomNumbers(int count, int start, int end) {
        SecureRandom random = new SecureRandom();

        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < count) {
            int number = random.nextInt(end - start + 1) + start;
            numbers.add(number);
        }
        return new ArrayList<>(numbers);
    }
}

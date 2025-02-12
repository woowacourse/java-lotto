import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomGenerator {
    private RandomGenerator() {
    }

    public static List<Integer> generateUniqueRandomNumbers(int count, int start, int end) {
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < count) {
            int number = random.nextInt(end - start + 1) + start;
            numbers.add(number);
        }
        return numbers.stream().collect(Collectors.toList());
    }
}

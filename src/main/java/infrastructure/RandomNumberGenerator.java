package infrastructure;

import domain.lotto.NumberGenerator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public List<Integer> generate(int minNumber, int maxNumber, int maxSize) {
        Random random = new Random();

        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < maxSize) {
            int number = random.nextInt(minNumber, maxNumber + 1);
            addNumberIfUnique(numbers, number);
        }

        numbers.sort(Comparator.naturalOrder());

        return numbers;
    }

    private void addNumberIfUnique(List<Integer> numbers, int number) {
        if (!numbers.contains(number)) {
            numbers.add(number);
        }
    }
}

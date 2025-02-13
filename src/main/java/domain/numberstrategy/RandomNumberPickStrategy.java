package domain.numberstrategy;

import java.util.ArrayList;
import java.util.List;

public class RandomNumberPickStrategy implements NumberPickStrategy {

    @Override
    public List<Integer> pickNumbers(int maxNumber, int size) {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() != size) {
            numbers.add(pickNumber(maxNumber));
        }
        return numbers;
    }

    private int pickNumber(int maxNumber) {
        return (int) (Math.random() * maxNumber) + 1;
    }
}

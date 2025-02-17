package domain.lottogeneratestrategy;

import java.util.ArrayList;
import java.util.List;

public class RandomLottoPickStrategy implements LottoPickStrategy {

    private final int maxNumber;

    public RandomLottoPickStrategy(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    @Override
    public List<Integer> pickNumbers(int size) {
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() != size) {
            numbers.add(pickNumber());
        }
        return numbers;
    }

    private int pickNumber() {
        return (int) (Math.random() * maxNumber) + 1;
    }
}

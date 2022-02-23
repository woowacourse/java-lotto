package lotterymachine.model;

import java.util.Collections;
import java.util.List;

public class LotteryTicket {

    private final List<Integer> numbers;

    public LotteryTicket(final List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int countMatchingNumbers(List<Integer> numbers) {
        return (int) numbers.stream()
                .filter(this.numbers::contains)
                .count();
    }

    public boolean containsNumber(int number) {
        return this.numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}

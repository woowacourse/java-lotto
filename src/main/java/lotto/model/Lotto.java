package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.model.number.WinningNumbers;

public class Lotto {
    private static final List<Integer> LOTTO_BALLS = new ArrayList<>();
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int NUMBER_COUNT = 6;
    private static final int PRICE = 1000;

    static {
        for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
            LOTTO_BALLS.add(number);
        }
    }

    private final List<Integer> numbers;

    public Lotto() {
        Collections.shuffle(LOTTO_BALLS);
        List<Integer> numbers = LOTTO_BALLS.subList(0, NUMBER_COUNT);
        Collections.sort(numbers);

        this.numbers = List.copyOf(numbers);
    }

    public static int countAvailableTickets(Money money) {
        return money.countAvailable(PRICE);
    }

    public int match(WinningNumbers winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers::match)
                .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}

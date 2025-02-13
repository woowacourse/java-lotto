package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Lotto {
    public static final int MAX_LOTTO_NUMBER = 45;
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int LOTTO_SIZE = 6;
    private final List<Integer> numbers;

    public Lotto(final Set<Integer> numbers) {
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        this.numbers = sortedNumbers;
    }

    public int getNumbersSize() {
        return numbers.size();
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}

package lotto.domain;

import java.util.*;

public class Lotto {
    private static final int MAX_LOTTO_SIZE = 6;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int MIN_LOTTO_NUMBER = 1;

    private final List<Integer> numbers;

    public Lotto() {
        this.numbers = createAutoNumbers();
    }

    public static List<Integer> createAutoNumbers() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < MAX_LOTTO_SIZE) {
            numbers.add(createRandomNumber());
        }
        return convertLottoNumbers(numbers);
    }

    private static List<Integer> convertLottoNumbers(Set<Integer> numbers) {
        List<Integer> convertNumbers = new ArrayList<>(numbers);
        Collections.sort(convertNumbers);
        return convertNumbers;
    }

    private static int createRandomNumber() {
        return (int) (Math.random() * MAX_LOTTO_NUMBER) + MIN_LOTTO_NUMBER;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        for (Integer number : numbers) {
            stringJoiner.add(Integer.toString(number));
        }
        return stringJoiner.toString();
    }
}

package lotto.domain;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto() {
        this.numbers = createAutoNumber();
    }

    public static List<Integer> createAutoNumber() {
        Set<Integer> numbers = new HashSet<>();
        while (numbers.size() < 6) {
            numbers.add(createRandomNumber());
        }
        List<Integer> resultNumbers = new ArrayList<>(numbers);
        Collections.sort(resultNumbers);
        return resultNumbers;
    }

    private static int createRandomNumber() {
        return (int) (Math.random() * 45) + 1;
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

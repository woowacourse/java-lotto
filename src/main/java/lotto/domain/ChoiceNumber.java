package lotto.domain;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class ChoiceNumber {
    private static final List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(toList());
    private List<Integer> choiceNumbers;

    public ChoiceNumber() {
        Collections.shuffle(numbers);
        generateRandom6AscendingNumber();
    }

    public ChoiceNumber(String input) {
        choiceNumbers = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toList());
        validateRange();
        validateDuplicate();
    }

    private void validateDuplicate() {
        boolean unique = choiceNumbers.stream()
                .allMatch(new HashSet<>()::add);
        if (!unique) {
            throw new IllegalArgumentException("중복값이 있습니다");
        }
    }

    private void validateRange() {
        if (choiceNumbers.stream().anyMatch(i -> i < 1 || i > 45)) {
            throw new IllegalArgumentException("범위내에 없습니다");
        }
    }

    private void generateRandom6AscendingNumber() {
        choiceNumbers = numbers.stream().limit(6).collect(toList());
        Collections.sort(choiceNumbers);
    }

    public List<Integer> getChoiceNumbers() {
        return choiceNumbers;
    }

    public int findMatchCount(ChoiceNumber numbers) {
        return choiceNumbers.stream()
                .filter(numbers.getChoiceNumbers()::contains)
                .collect(toList())
                .size();
    }

    public boolean isContainNumber(int targetNumber) {
        return choiceNumbers.contains(targetNumber);
    }

    @Override
    public String toString() {
        return choiceNumbers.toString();
    }
}

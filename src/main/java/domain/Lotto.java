package domain;

import domain.numbergenerator.NumberGenerator;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream().sorted().toList();
    }

    public static Lotto from(NumberGenerator numberGenerator) {
        List<Integer> numbers = numberGenerator.generateNumber();
        return new Lotto(numbers);
    }

    public boolean hasDuplicateNumber(final int bonusNumber) {
        return numbers.stream().anyMatch(number -> number == bonusNumber);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("");
        }
        if (hasDistinctNumber(numbers)) {
            throw new IllegalArgumentException("");
        }
        if (numbers.stream().anyMatch(number -> !isValidNumber(number))) {
            throw new IllegalArgumentException("");
        }
    }

    private boolean isValidNumber(int number) {
        return number > 0 && number <= 45;
    }

    private boolean hasDistinctNumber(List<Integer> numbers) {
        int distinctedSize = numbers.stream()
                .distinct()
                .toList()
                .size();

        return numbers.size() != distinctedSize;
    }
}

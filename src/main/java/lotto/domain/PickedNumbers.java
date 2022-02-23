package lotto.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PickedNumbers {
    private static final List<Integer> numbers = IntStream.rangeClosed(1, 45).boxed().collect(Collectors.toList());
    private List<Integer> pickedNumbers;

    public PickedNumbers() {
        Collections.shuffle(numbers);
        generateRandom6AscendingNumber();
    }

    public PickedNumbers(String input) {
        pickedNumbers = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        validateRange();
        validateDuplicate();
    }

    private void validateDuplicate() {
        boolean unique = pickedNumbers.stream()
                .allMatch(new HashSet<>()::add);
        if (!unique) {
            throw new IllegalArgumentException("중복값이 있습니다");
        }
    }

    private void validateRange() {
        if (pickedNumbers.stream().anyMatch(i -> i < 1 || i > 45)) {
            throw new IllegalArgumentException("범위내에 없습니다");
        }
    }

    private void generateRandom6AscendingNumber() {
        pickedNumbers = numbers.stream().limit(6).collect(Collectors.toList());
        Collections.sort(pickedNumbers);
    }

    public List<Integer> getPickedNumbers() {
        return pickedNumbers;
    }
}

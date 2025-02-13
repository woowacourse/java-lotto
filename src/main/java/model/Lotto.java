package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {

    private List<Integer> numbers;

    public static Lotto of(final String input) { // "1, 2, 3, 4, 5, 6"
        String[] split = input.split(", ");
        List<String> splitList = List.of(split);

        List<Integer> parsedInput = new ArrayList<>();
        for (int i = 0; i < splitList.size(); i++) {
            validateInteger(splitList.get(i));
            parsedInput.add(Integer.parseInt(splitList.get(i)));
        }

        if (parsedInput.size() != 6) {
            throw new IllegalArgumentException("로또 번호는 6개여야 한다.");
        }
        return new Lotto(parsedInput);
    }

    public Lotto(final List<Integer> numbers) {
        validateRange(numbers);
        validateDuplicate(numbers);
        this.numbers = numbers;
    }

    private void validateDuplicate(List<Integer> inputs) {
        HashSet<Integer> set = new HashSet<>(inputs);
        if (inputs.size() != set.size()) {
            throw new IllegalArgumentException("로또 번호는 중복되지 않아야 한다.");
        }
    }

    private void validateRange(List<Integer> inputs) {
        for (int i = 0; i < inputs.size(); i++) {
            if (1 > inputs.get(i) || inputs.get(i) > 45) {
                throw new IllegalArgumentException("로또 번호는 1부터 45사이여야 한다.");
            }
        }
    }

    private static void validateInteger(final String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("로또 번호는 숫자여야 합니다");
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}

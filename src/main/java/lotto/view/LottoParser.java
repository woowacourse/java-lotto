package lotto.view;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.List;
import lotto.model.Lotto;

public class LottoParser {

    public Lotto convert(String text) {
        List<String> splitNumbers = splitNumbers(text);
        List<String> trimNumbers = trimNumbers(splitNumbers);
        return Lotto.create(toInts(trimNumbers));
    }

    private List<String> splitNumbers(String numbers) {
        return List.of(numbers.split(","));
    }

    private List<String> trimNumbers(List<String> numbers) {
        return numbers.stream()
            .map(String::trim)
            .collect(toUnmodifiableList());
    }

    private List<Integer> toInts(List<String> numbers) {
        return numbers.stream()
            .map(Integer::valueOf)
            .collect(toUnmodifiableList());
    }
}

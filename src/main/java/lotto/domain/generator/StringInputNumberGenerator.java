package lotto.domain.generator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lotto.utils.IntegerUtils;

public class StringInputNumberGenerator implements NumberGenerator {

    private static final String DELIMITER = ",";

    private List<Integer> numbers;

    public StringInputNumberGenerator(String inputNumbers) {
        this.numbers = toIntNumbers(inputNumbers);
    }

    private List<Integer> toIntNumbers(String input) {
        return Arrays.stream(input.split(DELIMITER))
            .map(str -> IntegerUtils.parse(str.trim()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Integer> generate(int size) {
        return numbers.subList(0, size);
    }
}

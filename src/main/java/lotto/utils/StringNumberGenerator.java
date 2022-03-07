package lotto.utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class StringNumberGenerator implements NumberGenerator {

    private static final String NUMBER_DELIMITER = ",";

    private final Iterator<String> iterator;

    public StringNumberGenerator(String input) {
        this(List.of(input));
    }

    public StringNumberGenerator(List<String> inputs) {
        this.iterator = inputs.iterator();
    }

    @Override
    public List<Integer> generate(int size) {
        return readLine(iterator.next(), size);
    }

    private List<Integer> readLine(String next, int size) {
        List<Integer> numbers = toNumbers(next);
        return numbers.subList(0, size);
    }

    private List<Integer> toNumbers(String inputs) {
        return Arrays.stream(inputs.split(NUMBER_DELIMITER))
            .map(number -> IntegerUtils.parse(number.trim()))
            .collect(Collectors.toList());
    }
}

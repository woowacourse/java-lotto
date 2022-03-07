package lotto.utils;

import java.util.Arrays;
import java.util.Collections;
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
        if (!iterator.hasNext()) {
            throw new IllegalArgumentException("읽어들일 숫자가 존재하지 않습니다.");
        }
        return readLine(iterator.next(), size);
    }

    private List<Integer> readLine(String input, int size) {
        List<Integer> numbers = toNumbers(input);
        validateNumbersSize(size, numbers);
        List<Integer> integers = numbers.subList(0, size);
        Collections.sort(integers);
        return integers;
    }

    private void validateNumbersSize(int size, List<Integer> numbers) {
        if (numbers.size() < size) {
            throw new IllegalArgumentException("입력된 숫자들의 길이가 요구하는 길이보다 작습니다.");
        }
    }

    private List<Integer> toNumbers(String input) {
        return Arrays.stream(input.split(NUMBER_DELIMITER))
            .map(number -> IntegerUtils.parse(number.trim()))
            .collect(Collectors.toList());
    }
}

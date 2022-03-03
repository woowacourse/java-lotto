package lotto.domain.generator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import lotto.utils.IntegerUtils;

public class StringInputNumberGenerator implements NumberGenerator {

    private static final String LINE_DELIMITER = System.lineSeparator();
    private static final String NUMBER_DELIMITER = ",";

    private Iterator<String> inputIterator;

    public StringInputNumberGenerator(String input) {
        this(List.of(input));
    }

    public StringInputNumberGenerator(List<String> inputs) {
        this.inputIterator = inputs.iterator();
    }

    @Override
    public List<Integer> generate(int size) {
        if (!inputIterator.hasNext()) {
            throw new IllegalArgumentException("더이상 읽어들일 숫자가 없습니다.");
        }
        return readLine(inputIterator.next(), size);
    }

    private List<Integer> readLine(String line, int size) {
        List<Integer> integers = toIntegers(line);
        validateLength(size, integers);
        return integers.subList(0, size);
    }

    private void validateLength(int size, List<Integer> integers) {
        if (integers.size() < size) {
            throw new IllegalArgumentException(String.format(
                "입력된 리스트의 길이(%d)가 요구된 길이(%d)보다 작습니다.", integers.size(), size
            ));
        }
    }

    private List<Integer> toIntegers(String line) {
        return Arrays.stream(line.split(NUMBER_DELIMITER))
            .map(number -> IntegerUtils.parse(number.trim()))
            .collect(Collectors.toList());
    }
}

package converter;

import domain.Number;
import java.util.Arrays;
import java.util.List;

public class StringToNumbersConverter {

    public List<Number> convert(String rawInput, String delimiter) {
        return Arrays.stream(rawInput.split(delimiter))
                .map(String::trim)
                .map(Integer::valueOf)
                .map(Number::new)
                .toList();
    }
}

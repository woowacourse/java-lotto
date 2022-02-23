package study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class StreamTest {

    @Test
    void join() {
        List<String> list = Arrays.asList("a", "bcd", "efgh");

        String result = String.join(", ", list);

        assertThat(result).isEqualTo("a, bcd, efgh");
    }

    @Test
    void joining_intList() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        String result = list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        assertThat(result).isEqualTo("1, 2, 3");
    }

    @Test
    void toMap_initializesMapWithValuesInStream() {
        Map<String, String> map = Stream
                .of(new String[][]{{"Hello", "World"}, {"John", "Doe"},})
                .collect(Collectors.toMap(data -> data[0], data -> data[1]));

        Map<OrderEnum, Integer> map2 = Arrays
                .stream(OrderEnum.values())
                .collect(Collectors.toMap(lottoResult -> lottoResult, i -> 0));
    }

    private enum OrderEnum {
        FIRST, SECOND, THIRD, FOURTH, FIFTH
    }
}
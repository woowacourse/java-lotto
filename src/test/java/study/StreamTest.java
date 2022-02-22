package study;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class StreamTest {

    @Test
    void joining() {
        List<String> list = Arrays.asList("a", "bcd", "efgh");

        String result = list.stream().collect(Collectors.joining(", "));

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
}

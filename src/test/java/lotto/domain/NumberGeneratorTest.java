package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class NumberGeneratorTest {

    @Test
    void generate() {
        //given
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        //when
        List<Integer> expected = NumberGenerator.generate();
        //then
        assertThat(numbers.size()).isEqualTo(expected.size());
    }
}

package model;

import static org.assertj.core.api.Assertions.assertThat;

import common.NumberGenerator;
import java.util.List;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NumberGeneratorTest {

    private final int SIZE = 3;
    private final int MIN_NUMBER = 1;
    private final int MAX_NUMBER = 10;

    @RepeatedTest(100)
    void 중복되지_않는_숫자들이_뽑힌다() {
        List<Integer> numbers = NumberGenerator.generate(SIZE, MIN_NUMBER, MAX_NUMBER);
        assertThat(numbers).doesNotHaveDuplicates();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void 설정한_개수만큼의_숫자들이_뽑힌다(int size) {
        List<Integer> numbers = NumberGenerator.generate(size, MIN_NUMBER, MAX_NUMBER);
        assertThat(numbers).hasSize(size);
    }

    @RepeatedTest(100)
    void 설정한_범위_내의_숫자들이_뽑힌다() {
        List<Integer> numbers = NumberGenerator.generate(SIZE, MIN_NUMBER, MAX_NUMBER);
        assertThat(numbers).allMatch(num -> num >= MIN_NUMBER && num <= MAX_NUMBER);
    }
}

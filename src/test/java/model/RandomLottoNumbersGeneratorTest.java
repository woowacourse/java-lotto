package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RandomLottoNumbersGeneratorTest {

    @ParameterizedTest
    @MethodSource("provideRangeAndSizeAndExpectedSet")
    @DisplayName("랜덤 값 생성하기")
    void createRandomNumbers(int start, int end, LottoNumbers expect) {
        LottoNumbersGenerator generator = new RandomLottoNumbersGenerator(start, end);
        LottoNumbers lottoNumbers = generator.createLottoNumbers();
        assertThat(lottoNumbers).isEqualTo(expect);
    }

    private static Stream<Arguments> provideRangeAndSizeAndExpectedSet() {
        return Stream.of(
            Arguments.of(1, 6, new LottoNumbers(List.of(1, 2, 3, 4, 5, 6))),
            Arguments.of(10, 15, new LottoNumbers(List.of(10, 11, 12, 13, 14, 15))),
            Arguments.of(6, 11, new LottoNumbers(List.of(6, 7, 8, 9, 10, 11)))
        );
    }
}

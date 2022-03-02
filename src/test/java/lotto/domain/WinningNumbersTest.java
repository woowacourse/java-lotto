package lotto.domain;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

public class WinningNumbersTest {
    private final List<Integer> numbers = getNumbers(1, 2, 3, 4, 5, 6);
    private final LottoNumbers lottoNumbers = new LottoNumbers(numbers);

    @Test
    @DisplayName("중복 문자")
    void test() {
        // given

        // when
        LottoNumber bonusNumber = LottoNumber.of(1);

        // then
        assertThatThrownBy(() -> new WinningNumbers(lottoNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("parameterProvider")
    @DisplayName("[n]등 당첨 결과 반환")
    void prize2(List<Integer> list, Ranking expect) {
        // given
        LottoNumber bonusNumber = LottoNumber.of(7);
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, bonusNumber);

        // when
        LottoNumbers myLotto = new LottoNumbers(list);

        // then
        assertThat(winningNumbers.calculateRanking(myLotto)).isEqualTo(expect);
    }

    private static Stream<Arguments> parameterProvider() {
        return Stream.of(
                Arguments.arguments(getNumbers(1,2,3,4,5,6), Ranking.FIRST),
                Arguments.arguments(getNumbers(1,2,3,4,5,7), Ranking.SECOND),
                Arguments.arguments(getNumbers(1,2,3,4,5,8), Ranking.THIRD),
                Arguments.arguments(getNumbers(1,2,3,4,8,9), Ranking.FOURTH),
                Arguments.arguments(getNumbers(1,2,3,8,9,10), Ranking.FIFTH)
        );
    }

    private static List<Integer> getNumbers(int... numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }
}
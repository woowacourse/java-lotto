package lotto.domain;

import static lotto.common.constant.BusinessRule.*;
import static lotto.common.constant.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    @Test
    @DisplayName("로또 번호는 로또가 생성할 때, 정렬된다.")
    void test_LottoNumberSorted_WhenLottoGenerate() {
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));

        assertThat(lotto.toString()).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    @DisplayName("로또 번호의 갯수가 기준(6)과 다를 경우, 예외를 발생시킨다.")
    void error_WhenLottoNumberUncorrectedSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_INCORRECT_LOTTO_SIZE.getMessage());
    }

    @Test
    @DisplayName("로또 번호의 범위가 기준에서 벗어났을 경우, 예외를 발생시킨다.")
    void error_WhenLottoNumberOverRange() {
        var overMAX = LOTTO_MAXIMUM + 1;
        var lessMIN = LOTTO_MINIMUM - 1;

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, overMAX)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());

        assertThatThrownBy(() -> new Lotto(List.of(lessMIN, 2, 3, 4, 5, 6)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());
    }

    private static Stream<Arguments> matchWinningLottoTestParameters() {
        return Stream.of(
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 10)), 5),
            Arguments.of(new Lotto(List.of(1, 2, 3, 4, 11, 10)), 4),
            Arguments.of(new Lotto(List.of(1, 2, 3, 12, 11, 10)), 3)
        );
    }
}

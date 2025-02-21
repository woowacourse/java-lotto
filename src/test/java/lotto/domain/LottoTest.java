package lotto.domain;

import static lotto.common.constant.BusinessRule.*;
import static lotto.common.constant.ErrorMessage.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
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
        List<Integer> list = new ArrayList<>(List.of(6, 5, 4, 3, 2, 1));
        Lotto lotto = new Lotto(list);
        Collections.sort(list);

        assertThat(lotto.getLottoNumbers()).isEqualTo(list);
    }

    @Test
    @DisplayName("로또 번호의 범위가 기준보다 클 경우, 예외를 발생시킨다.")
    void error_WhenLottoNumberMoreThenRange() {
        var overMAX = LOTTO_MAXIMUM + 1;

        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, overMAX)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());
    }

    @Test
    @DisplayName("로또 번호의 범위가 기준보다 작을 경우, 예외를 발생시킨다.")
    void error_WhenLottoNumberLessThenRange() {
        var lessMIN = LOTTO_MINIMUM - 1;

        assertThatThrownBy(() -> new Lotto(List.of(lessMIN, 2, 3, 4, 5, 6)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_LOTTO_NUMBER_RANGE.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideLottoNumbers")
    @DisplayName("로또 번호의 갯수가 기준(6)과 다를 경우, 예외를 발생시킨다.")
    void error_WhenLottoNumberUncorrectedSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ERROR_INCORRECT_LOTTO_SIZE.getMessage());
    }

    private static Stream<Arguments> provideLottoNumbers() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4, 5)),
            Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7)),
            Arguments.of(List.of(1, 2, 3))
        );
    }
}

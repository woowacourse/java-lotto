package domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.ExceptionMessage;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {

    public static Stream<Arguments> getMatchedCountTest() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), 5),
                Arguments.of(List.of(1, 2, 3, 4, 7, 8), 4),
                Arguments.of(List.of(1, 2, 3, 7, 8, 9), 3),
                Arguments.of(List.of(1, 2, 7, 8, 9, 10), 2),
                Arguments.of(List.of(1, 11, 7, 8, 9, 10), 1),
                Arguments.of(List.of(12, 11, 7, 8, 9, 10), 0)
        );
    }

    @DisplayName("로또_번호_범위_테스트(1 미만의 값)")
    @Test
    void rangeTest1() {
        assertThatThrownBy(() -> new Lotto(List.of(0, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.LOTTO_RANGE_ERROR.getMessage());
    }

    @DisplayName("로또_번호_범위_테스트(46 이상의 값)")
    @Test
    void rangeTest2() {
        assertThatThrownBy(() -> new Lotto(List.of(46, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.LOTTO_RANGE_ERROR.getMessage());
    }

    @DisplayName("로또 번호 중복 테스트")
    @Test
    void duplicateTest() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 1, 2, 3, 4, 5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.LOTTO_NUMBER_DUPLICATED_ERROR.getMessage());
    }

    @DisplayName("로또 번호 갯수 테스트")
    @Test
    void sizeTest() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.LOTTO_SIZE_ERROR.getMessage());
    }

    @DisplayName("로또 번호 정렬 테스트")
    @Test
    void sortTest() {
        // given
        Lotto lotto = new Lotto(List.of(6, 5, 4, 3, 2, 1));
        // when & then
        List<Integer> numbers = lotto.getSortedLottoNumbers();
        assertThat(numbers).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("hasNumber - 해당 번호가 있으면 TRUE 없으면 FALSE를 반환다.")
    @ParameterizedTest
    @CsvSource({"1,true", "2,true", "10,false", "45,false"})
    void hasNumberTest(int number, boolean expected) {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = new LottoNumber(number);
        // when
        boolean hasNumber = lotto.hasNumber(lottoNumber);
        // then
        assertThat(hasNumber).isEqualTo(expected);
    }

    @DisplayName("hasNumber - 해당 번호가 있으면 TRUE 없으면 FALSE를 반환다.")
    @ParameterizedTest
    @MethodSource
    void getMatchedCountTest(List<Integer> numbers, int expectedCount) {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        Lotto compareLotto = new Lotto(numbers);
        // when
        int matchedCount = lotto.getMatchedCount(compareLotto);
        // then
        assertThat(matchedCount).isEqualTo(expectedCount);
    }
}
package lotto;

import static lotto.domain.Lotto.MAX_LOTTO_NUMBER;
import static lotto.domain.Lotto.MIN_LOTTO_NUMBER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTest {
    @DisplayName("특정 번호를 포함하는 지 여부를 반환할 수 있다")
    @CsvSource(value = {"1:true", "6:true", "8:false"}, delimiterString = ":")
    @ParameterizedTest
    void 특정_번호를_포함하는_지_여부를_반환할_수_있다(int number, boolean expected) {
        //given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        //when
        boolean result = lotto.contains(number);

        //then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("번호가 1과 45 사이의 숫자가 아니면 예외를 던진다")
    @Test
    void 번호가_1과_45_사이의_번호가_아니면_예외를_던진다() {
        //given
        List<Integer> lottoNumbers = List.of(1, 2, 3, 4, 5, 46);

        //when
        //then
        assertThatThrownBy(() -> new Lotto(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 %d ~ %d 사이여야 합니다.".formatted(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
    }

    @DisplayName("6개의 고유한 번호가 아니라면 예외를 던진다")
    @MethodSource("returnWrongSizeNumbers")
    @ParameterizedTest
    void _6개의_고유한_번호가_아니라면_예외를_던진다(List<Integer> numbers) {
        //given
        //when
        //then
        assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("6개의 고유한 번호를 입력해야 합니다.");
    }

    static Stream<Arguments> returnWrongSizeNumbers() {
        return Stream.of(
                Arguments.arguments(List.of(1, 2, 3, 4, 5)),
                Arguments.arguments(List.of(1, 2, 3, 4, 5, 6, 7)),
                Arguments.arguments(List.of(1, 2, 3, 4, 6, 6))
        );
    }
}

package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import java.util.stream.Stream;
import lotto.exception_message.ExceptionMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoTest {

    @DisplayName("당첨 로또를 올바르게 생성할 수 있다.")
    @Test
    void 당첨_로또를_올바르게_생성할_수_있다() {
        assertThatCode(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7))
                .doesNotThrowAnyException();
    }

    @DisplayName("입력된 보너스 번호의 범위를 검증할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 46})
    void 입력된_보너스_번호의_범위를_검증할_수_있다(int givenInvalidBonusNumber) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), givenInvalidBonusNumber))
                .withMessage(ExceptionMessage.OUT_OF_RANGE.getContent());
    }

    @DisplayName("입력된 당첨 번호와 보너스 번호가 겹치는지 검증할 수 있다.")
    @Test
    void 입력된_당첨_번호와_보너스_번호가_겹치는지_검증할_수_있다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 6))
                .withMessage(ExceptionMessage.DUPLICATED_NUMBERS.getContent());
    }

    @DisplayName("로또의 당첨 결과를 구할 수 있다.")
    @ParameterizedTest
    @MethodSource
    void 로또의_당첨_결과를_구할_수_있다(Lotto givenLotto, WinningTier expectedTier) {
        WinningLotto givenWinningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);

        WinningTier actualTier = givenWinningLotto.findWinningTier(givenLotto);

        assertThat(actualTier).isEqualTo(expectedTier);
    }

    static Stream<Arguments> 로또의_당첨_결과를_구할_수_있다() {
        return Stream.of(
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), WinningTier.FIRST),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), WinningTier.SECOND),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 45)), WinningTier.THIRD),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 44, 45)), WinningTier.FOURTH),
                Arguments.of(new Lotto(List.of(1, 2, 3, 43, 44, 45)), WinningTier.FIFTH),
                Arguments.of(new Lotto(List.of(1, 2, 42, 43, 44, 45)), WinningTier.EMPTY)
        );
    }
}
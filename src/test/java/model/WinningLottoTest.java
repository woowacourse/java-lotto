package model;

import static model.WinningLotto.BONUS_INPUT_ERROR_MESSAGE;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningLottoTest {

    @DisplayName("당첨 번호가 1 이상 45 이하의 정수인지 검사한다")
    @Test
    void 당첨_번호가_1_이상_45_이하의_정수인지_검사한다() {
        String rawWinningNumbers = "1, 2, 3, 4, 15, 45";
        org.assertj.core.api.Assertions.assertThatCode(() -> {new WinningLotto(rawWinningNumbers);})
                .doesNotThrowAnyException();
    }

    @DisplayName("당첨 번호가 1 이상 45 이하의 정수가 아니면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"1, 100, 2, 3, 4, 5", "abc, 1, 2, 3, 4, 5", "-1, 1, 2, 3, 4, 5", "1, 2, 3, 4, 5, 46"})
    void 당첨_번호가_1_이상_45_이하의_정수가_아니면_예외가_발생한다(String input) {
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {new WinningLotto(input);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호는 중복되지 않는 1 이상 45 이하의 정수여야합니다.");
    }

    @DisplayName("당첨 번호에 중복이 존재하면 예외가 발생한다")
    @Test
    void 당첨_번호에_중복이_존재하면_예외가_발생한다() {
        String rawWinningNumbers = "1, 1, 3, 4, 15, 45";
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {new WinningLotto(rawWinningNumbers);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("당첨 번호는 중복되지 않는 1 이상 45 이하의 정수여야합니다.");
    }

    @DisplayName("보너스 볼이 1 이상 45 이하의 정수인지 검사한다")
    @Test
    void 보너스_볼이_1_이상_45_이하의_정수인지_검사한다() {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 15, 45");
        org.assertj.core.api.Assertions.assertThatCode(() -> {winningLotto.setBonus("6");})
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 볼이 1 이상 45 이하의 정수가 아니라면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "46", "100"})
    void 보너스_볼이_1_이상_45_이하의_정수가_아니라면_예외가_발생한다(String bonus) {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 15, 45");
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {winningLotto.setBonus(bonus);})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_INPUT_ERROR_MESSAGE);
    }

    @DisplayName("보너스 볼이 당첨 번호와 중복된다면 예외가 발생한다")
    @Test
    void 보너스_볼이_당첨_번호와_중복된다면_예외가_발생한다() {
        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 15, 45");
        org.assertj.core.api.Assertions.assertThatThrownBy(() -> {winningLotto.setBonus("1");})
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BONUS_INPUT_ERROR_MESSAGE);
    }
}
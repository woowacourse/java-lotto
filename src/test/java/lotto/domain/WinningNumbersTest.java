package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @DisplayName("당첨 번호 생성을 확인한다.")
    @Test
    void 당첨_번호_생성을_확인한다() {

        assertThatCode(() -> new WinningNumbers(List.of(1, 10, 19, 28, 37, 45), 6)).doesNotThrowAnyException();
        assertThatCode(() -> new WinningNumbers(List.of(2, 3, 4, 5, 6, 7), 1)).doesNotThrowAnyException();
        assertThatCode(() -> new WinningNumbers(List.of(2, 3, 4, 5, 6, 7), 45)).doesNotThrowAnyException();
    }

    @DisplayName("로또의 당첨 갯수를 확인한다.")
    @Test
    void 로또의_당첨_갯수를_계산한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 7);

        assertThat(winningNumbers.checkMatchCount(lotto)).isEqualTo(6);
    }

    @DisplayName("보너스 번호 일치여부를 확인한다.")
    @Test
    void 보너스번호_일치여부를_확인한다() {
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers = new WinningNumbers(List.of(11, 12, 13, 14, 15, 16), 6);

        assertThat(winningNumbers.checkMatchBonus(lotto)).isEqualTo(true);
    }

    @DisplayName("당첨 번호값 초과시 예외를 발생한다.")
    @Test
    void 당첨_번호값_초과시_예외를_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 46), 7)).isInstanceOf(
                IllegalArgumentException.class).hasMessage("[ERROR] 범위를 벗어나는 숫자입니다.");
    }

    @DisplayName("당첨 번호값 미만일시 예외를 발생한다.")
    @Test
    void 당첨_번호값_미만일시_예외를_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(0, 2, 3, 4, 5, 6), 7)).isInstanceOf(
                IllegalArgumentException.class).hasMessage("[ERROR] 범위를 벗어나는 숫자입니다.");
    }

    @DisplayName("보너스 번호값 초과시 예외를 발생한다.")
    @Test
    void 보너스_번호값_초과시_예외를_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 46)).isInstanceOf(
                IllegalArgumentException.class).hasMessage("[ERROR] 범위를 벗어나는 숫자입니다.");
    }

    @DisplayName("보너스 번호값 미만일시 예외를 발생한다.")
    @Test
    void 보너스_번호값_미만일시_예외를_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 0)).isInstanceOf(
                IllegalArgumentException.class).hasMessage("[ERROR] 범위를 벗어나는 숫자입니다.");
    }

    @DisplayName("당첨 번호는 중복될 시 예외를 발생한다.")
    @Test
    void 당첨_번호는_중복될_시_예외를_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 5), 7)).isInstanceOf(
                IllegalArgumentException.class).hasMessage("[ERROR] 당첨 번호는 중복될 수 없습니다.");
    }

    @DisplayName("보너스 번호는 당첨 번호와 중복될 수 없다.")
    @Test
    void 보너스_번호는_당첨_번호와_중복될_수_없다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 6)).isInstanceOf(
                IllegalArgumentException.class).hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("당첨 번호의 갯수가 부족할 시 예외를 발생한다.")
    @Test
    void 당첨_번호의_갯수가_부족할시_예외를_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5), 6)).isInstanceOf(
                IllegalArgumentException.class).hasMessage("[ERROR] 로또의 갯수가 일치하지 않습니다.");
    }

    @DisplayName("당첨 번호의 갯수가 많을 시 예외를 발생한다.")
    @Test
    void 당첨_번호의_갯수가_많을시_예외를_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6, 7), 6)).isInstanceOf(
                IllegalArgumentException.class).hasMessage("[ERROR] 로또의 갯수가 일치하지 않습니다.");
    }
}

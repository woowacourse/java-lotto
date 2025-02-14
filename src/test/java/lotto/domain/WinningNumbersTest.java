package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @DisplayName("당첨 번호의 범위는 1부터 45여야 한다.")
    @Test
    void 당첨_번호의_범위는_1부터_45여야_한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 46), 7))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("[ERROR] 범위를 벗어나는 숫자입니다.");
    }

    @DisplayName("보너스 번호의 범위는 1부터 45여야 한다.")
    @Test
    void 보너스_번호의_범위는_1부터_45여야_한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 46))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("[ERROR] 범위를 벗어나는 숫자입니다.");
    }

    @DisplayName("당첨 번호는 중복될 시 예외를 발생한다.")
    @Test
    void 당첨_번호는_중복될_시_예외를_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 5), 7))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("[ERROR] 당첨 번호는 중복될 수 없습니다.");
    }

    @DisplayName("보너스 번호는 당첨 번호와 중복될 수 없다.")
    @Test
    void 보너스_번호는_당첨_번호와_중복될_수_없다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5, 6), 6))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }

    @DisplayName("당첨 번호의 갯수가 6개가 아니면 예외를 발생한다.")
    @Test
    void 당첨_번호의_갯수가_6개가_아니면_예외를_발생한다() {
        assertThatThrownBy(() -> new WinningNumbers(List.of(1, 2, 3, 4, 5), 6))
                .isInstanceOf(IllegalArgumentException.class).hasMessage("[ERROR] 로또의 갯수가 일치하지 않습니다.");
    }
}

package validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static validation.LottoValidator.*;

class LottoValidatorTest {
    @DisplayName("당첨 번호 6개 예외 처리 테스트")
    @Test
    void 당첨_번호_6개_예외_처리_테스트() {
        List<Integer> underSixNumbers = List.of(1, 2, 3, 4, 5);
        List<Integer> sixNumbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> overSixNumbers = List.of(1, 2, 3, 4, 5, 6, 7);
        assertThatThrownBy(() -> {
            validateWinningNumbers(underSixNumbers);
            validateWinningNumbers(sixNumbers);
            validateWinningNumbers(overSixNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호는 1에서 45 사이의 수 예외 처리 테스트")
    @Test
    void 당첨_번호는_1에서_45_사이의_수_예외_처리_테스트() {
        assertThatThrownBy(() -> {
            validateNumber(0);
            validateNumber(5);
            validateNumber(40);
            validateNumber(50);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호 중복 예외 처리 테스트")
    @Test
    void 당첨_번호_중복_예외_처리_테스트() {
        assertThatThrownBy(() -> {
            validateWinningNumbers(List.of(1, 1, 2, 3, 4, 5));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호와 보너스볼 중복 예외 처리 테스트")
    @Test
    void 로또_번호와_보너스볼_중복_예외_처리_테스트() {
        assertThatThrownBy(() -> {
            validateBonusBallUnique(List.of(1, 2, 3, 4, 5, 6), 6);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

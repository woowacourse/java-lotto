package domain;

import static error.ErrorMessage.BONUS_NUMBER_ALREADY_EXIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningNumberTest {

    @DisplayName("로또 번호에 보너스 번호가 이미 존재한다면, 예외를 발생시킨다")
    @Test
    void invalid_bonus_number() {
        List<Integer> lottoNumber = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.from(lottoNumber);
        int bonusNumber = 6;

        assertThatThrownBy(() -> {
            WinningNumber winningNumber = WinningNumber.of(lotto, bonusNumber);
        }).isInstanceOf(IllegalArgumentException.class)
            .hasMessage(BONUS_NUMBER_ALREADY_EXIST.getMessage());
    }

    @DisplayName("로또 번호에 보너스 번호가 존재하지 않는다면, winningNumber는 성공적으로 생성되어야 한다")
    @Test
    void valid_bonus_number() {
        List<Integer> lottoNumber = List.of(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.from(lottoNumber);
        int bonusNumber = 7;
        List<Integer> expectedLottoNumber = List.of(1, 2, 3, 4, 5, 6);
        int expectedBonusNumber = 7;
        WinningNumber winningNumber = WinningNumber.of(lotto, bonusNumber);
        Lotto lotto1 = winningNumber.getNumbers();
        List<Integer> numbers = lotto1.getNumbers();
        assertThat(numbers).isEqualTo(expectedLottoNumber);
        assertThat(winningNumber.getBonusNumber()).isEqualTo(expectedBonusNumber);
    }
}

package domain;

import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.LottoGenerator;
import domain.LottoGenerator.WinningLottoGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("보너스볼과 당첨번호의 중복을 검사한 뒤, 중복되면 예외를 발생시킨다.")
    void validateDuplicateBonusBallAndWinningNumber() {
        List<Integer> winningNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            winningNumbers.add(i);
        }
        assertThatThrownBy(() -> new WinningLotto(winningNumbers,1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.LOTTO_AND_BONUS_BALL_DUPLICATION);
    }

    @Test
    @DisplayName("당첨번호간의 중복을 검사한 뒤, 중복되면 예외를 발생시킨다.")
    void validateDuplicateWinningNumber() {
        List<Integer> winningNumbers = new ArrayList<>();
        winningNumbers.add(1);
        for (int i = 1; i <= 5; i++) {
            winningNumbers.add(i);
        }
        assertThatThrownBy(() -> new WinningLotto(winningNumbers,6))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.DUPLICATE_LOTTO_NUMBER);
    }

}
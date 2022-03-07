package domain;

import domain.Lotto.LottoFactory;
import domain.Lotto.LottoNumber;
import domain.Lotto.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ExceptionMessage;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("보너스볼과 당첨번호의 중복을 검사한 뒤, 중복되면 예외를 발생시킨다.")
    void validateDuplicate() {
        List<Integer> winningNumbers = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            winningNumbers.add(i);
        }
        LottoNumber bonusBall = LottoNumber.valueOf(1);
        assertThatThrownBy(() -> new WinningLotto(LottoFactory.generateManualLotto(winningNumbers), bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessage.LOTTO_AND_BONUS_BALL_DUPLICATION);
    }

}
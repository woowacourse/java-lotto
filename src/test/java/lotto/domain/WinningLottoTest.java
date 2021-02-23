package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WinningLottoTest {

    @Test
    @DisplayName("보너스 볼이 지난 주 당첨 번호 안에 있는지 확인")
    void containBonusBallInLotto() {
        Lotto lotto = Lotto.from(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = new LottoNumber(6);
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusBall))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(WinningLotto.BONUS_BALL_IN_LOTTO_ERROR_MESSAGE);
    }
}

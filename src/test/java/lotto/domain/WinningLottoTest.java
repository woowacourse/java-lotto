package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @Test
    @DisplayName("보너스 볼이 지난 주 당첨 번호 안에 있는지 확인")
    void containBonusBallInLotto() {
        Lotto lotto = Lotto.createByInteger(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = new LottoNumber(6);
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된");
    }
}

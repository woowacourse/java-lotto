package lotto.domain;

import lotto.domain.lottomachine.LottoMachine;
import lotto.domain.lottomachine.TestLottoMachine;
import lotto.domain.rating.Rating;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class WinningLottoTest {

    @Test
    @DisplayName("보너스 볼이 지난 주 당첨 번호 안에 있는지 확인")
    void containBonusBallInLotto() {
        Lotto lotto = Lotto.createByInteger(Arrays.asList(1, 2, 3, 4, 5, 6));
        LottoNumber bonusBall = LottoNumber.valueOf(6);
        assertThatThrownBy(() -> new WinningLotto(lotto, bonusBall))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복된");
    }

    @Test
    @DisplayName("스크래치 결과 확인")
    void scratch() {
        LottoMachine lottoMachine = new TestLottoMachine();
        Lotto lotto = new Lotto(lottoMachine.generate());
        WinningLotto winningLotto = new WinningLotto(lotto, LottoNumber.valueOf(7));
        assertThat(winningLotto.scratch(lotto)).isEqualTo(Rating.FIRST);
    }
}

package lotto.domain.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinningLottoTest {

    List<Number> inputWinningLottoNumber = new ArrayList<>();
    List<Number> purchasedLottoNumber = new ArrayList<>();
    Lotto myLotto;
    WinningLotto winningLotto;
    Number bonusNumber;

    @Before
    public void setUp() {
        for (int i = 1; i <= 6; i++) {
            inputWinningLottoNumber.add(NumberSet.of(i));
        }

        for (int i = 3; i <= 7; i++) {
            purchasedLottoNumber.add(NumberSet.of(i));
        }

        myLotto = new Lotto(purchasedLottoNumber);
        bonusNumber = NumberSet.of(7);
        winningLotto = new WinningLotto(new Lotto(inputWinningLottoNumber), bonusNumber);
    }

    @Test
    public void 당첨_번호_갯수_확인() {
        int match = winningLotto.matchCount(myLotto);
        assertThat(match).isEqualTo(4);
    }

    @Test
    public void 보너스볼_당첨_확인() {
        boolean matchBonus = winningLotto.matchBonusNumber(myLotto);
        assertThat(matchBonus).isEqualTo(true);
    }
}

package lotto;

import org.junit.Test;

import javax.management.Query;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class LottoTest {
    @Test
    public void 숫자_세개_일치() {
        Lotto lotto = new Lotto("1,2,3,4,5,6");
        WinningLotto winningLotto = new WinningLotto("3,4,5,7,8,9");

        assertThat(winningLotto.prize(lotto)).isEqualTo(Prize.THIRD);

    }

}

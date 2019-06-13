package lotto;


import lotto.model.*;
import lotto.model.exceptions.IllegalNumberCombinationException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningLottoTest {
    @Test
    public void 숫자_0개_일치() {
        Lotto lotto1 = new Lotto("1,2,3,4,5,6");
        Lotto lotto2 = new Lotto("10,11,12,7,8,9");
        LottoNumber bonusNumber = new LottoNumber(1);
        WinningLotto winningLotto = new WinningLotto(lotto2, bonusNumber);

        assertThat(winningLotto.prizeOf(lotto1)).isEqualTo(Prize.MISS);
    }

    @Test
    public void 숫자_한개_일치() {
        Lotto lotto = new Lotto("1,2,3,4,5,6");
        Lotto lotto2 = new Lotto("6,11,12,7,8,9");
        LottoNumber bonusNumber = new LottoNumber(10);
        WinningLotto winningLotto = new WinningLotto(lotto2, bonusNumber);
        assertThat(winningLotto.prizeOf(lotto)).isEqualTo(Prize.MISS);
    }

    @Test
    public void 숫자_두개_일치() {
        Lotto lotto = new Lotto("1,2,3,4,5,6");
        Lotto lotto2 = new Lotto("3,11,5,7,8,9");
        LottoNumber bonusNumber = new LottoNumber(10);
        WinningLotto winningLotto = new WinningLotto(lotto2, bonusNumber);
        assertThat(winningLotto.prizeOf(lotto)).isEqualTo(Prize.MISS);
    }

    @Test
    public void 숫자_세개_일치() {
        Lotto lotto = new Lotto("1,2,3,4,5,6");
        Lotto lotto2 = new Lotto("3,4,5,7,8,9");
        LottoNumber bonusNumber = new LottoNumber(10);
        WinningLotto winningLotto = new WinningLotto(lotto2, bonusNumber);
        assertThat(winningLotto.prizeOf(lotto)).isEqualTo(Prize.FIFTH);
    }

    @Test
    public void 숫자_네개_일치() {
        Lotto lotto = new Lotto("1,2,3,4,5,6");
        Lotto lotto2 = new Lotto("3,4,5,6,8,9");
        LottoNumber bonusNumber = new LottoNumber(10);
        WinningLotto winningLotto = new WinningLotto(lotto2, bonusNumber);
        assertThat(winningLotto.prizeOf(lotto)).isEqualTo(Prize.FOURTH);
    }

    @Test
    public void 숫자_다섯개_일치_보너스_불일치() {
        Lotto lotto = new Lotto("1,2,3,4,5,6");
        Lotto lotto2 = new Lotto("1,2,3,4,5,7");
        LottoNumber bonusNumber = new LottoNumber(8);
        WinningLotto winningLotto = new WinningLotto(lotto2, bonusNumber);
        assertThat(winningLotto.prizeOf(lotto)).isEqualTo(Prize.THIRD);
    }

    @Test
    public void 숫자_다섯개_일치_보너스_일치() {
        Lotto lotto = new Lotto("1,2,3,4,5,6" +
                "");
        Lotto lotto2 = new Lotto("1,2,3,4,5,7");
        LottoNumber bonusNumber = new LottoNumber(6);
        WinningLotto winningLotto = new WinningLotto(lotto2, bonusNumber);
        assertThat(winningLotto.prizeOf(lotto)).isEqualTo(Prize.SECOND);
    }

    @Test
    public void 숫자_여섯개_일치() {
        Lotto lotto = new Lotto("1,2,3,4,5,6");
        Lotto lotto2 = new Lotto("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber(10);
        WinningLotto winningLotto = new WinningLotto(lotto2, bonusNumber);
        assertThat(winningLotto.prizeOf(lotto)).isEqualTo(Prize.FIRST);
    }

    @Test
    void 보너스_로또_중복_테스트() {
        Lotto lotto = new Lotto("1,2,3,4,5,6");
        LottoNumber bonusNumber = new LottoNumber(6);
        assertThrows(IllegalNumberCombinationException.class, () ->
                new WinningLotto(lotto, bonusNumber));
    }


}

package domain;

import domain.lottoGame.Lotto;
import domain.lottoGame.LottoNumber;
import domain.lottoGame.LottoRank;
import domain.lottoGame.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @DisplayName("당첨 로또 등수를 반환하는 기능")
    @Test
    void isMatch() {
        //given
        Lotto lotto = new Lotto(new int[]{1, 2, 3, 4, 5, 6});
        LottoNumber bonusBall = new LottoNumber(7);
        WinningLotto winningLotto = new WinningLotto(lotto, bonusBall);

        Lotto targetLotto = new Lotto(new int[]{1, 2, 3, 4, 5, 6});

        //when
        LottoRank lottoRank = LottoRank.valueOf(winningLotto, targetLotto);

        //then
        assertThat(lottoRank).isEqualTo(LottoRank.FIRST);
    }
}

package lotto.model.object;

import lotto.model.LottoRank;
import lotto.model.exception.WinningLottoBonusBallDuplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WinningInfoTest {
        Lotto winningLotto;
        BonusBall bonusBall1;
        BonusBall bonusBall2;

        @BeforeEach
        void setUp() {
                winningLotto = new Lotto(new String[]{"1","2","3","4","5","6"});
                bonusBall1 = new BonusBall(5);
                bonusBall2 = new BonusBall(7);
        }

        @Test
        void 당첨_번호_보너스볼_중복_검사() {
                assertThrows(WinningLottoBonusBallDuplicationException.class, ()->{
                        new WinningInfo(winningLotto, bonusBall1);
                });
        }

        @Test
        void 일치_숫자_추출_검사() {
                Lotto purchasedLotto = new Lotto(new String[]{"7","6","5","4","3","2"});
                assertThat(new WinningInfo(winningLotto, bonusBall2).getMatchRank(purchasedLotto)).isEqualTo(LottoRank.SECOND);
        }
}
package lotto.model.object;

import lotto.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;


class WinStatsTest {
        @Test
        void 당첨_통계_해당_등수_개수_추출_검사() {
                List<LottoNumber> lottoNumbers1 = new ArrayList<>();
                lottoNumbers1.add(LottoNumber.getInstance(1));
                lottoNumbers1.add(LottoNumber.getInstance(2));
                lottoNumbers1.add(LottoNumber.getInstance(3));
                lottoNumbers1.add(LottoNumber.getInstance(4));
                lottoNumbers1.add(LottoNumber.getInstance(5));
                lottoNumbers1.add(LottoNumber.getInstance(6));
                Lotto purchasedLotto1 = new Lotto(lottoNumbers1);

                List<LottoNumber> lottoNumbers2 = new ArrayList<>();
                lottoNumbers2.add(LottoNumber.getInstance(10));
                lottoNumbers2.add(LottoNumber.getInstance(2));
                lottoNumbers2.add(LottoNumber.getInstance(3));
                lottoNumbers2.add(LottoNumber.getInstance(4));
                lottoNumbers2.add(LottoNumber.getInstance(5));
                lottoNumbers2.add(LottoNumber.getInstance(6));
                Lotto purchasedLotto2 = new Lotto(lottoNumbers2);

                List<Lotto> purchasedLottos = new ArrayList<>();
                purchasedLottos.add(purchasedLotto1);
                purchasedLottos.add(purchasedLotto2);

                Lotto winningLotto = new Lotto(new String[]{"1", "2", "3", "4", "5", "6"});

                BonusBall bonusBall = new BonusBall(10);

                WinningInfo winningInfo = new WinningInfo(winningLotto, bonusBall);

                WinStats winStats = new WinStats(purchasedLottos, winningInfo);

                assertThat(winStats.getRankCount(LottoRank.SECOND)).isEqualTo(1);
        }
}
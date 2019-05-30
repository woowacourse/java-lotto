package lotto.model.creator;

import lotto.model.LottoRank;
import lotto.model.object.BonusBall;
import lotto.model.object.Lotto;
import lotto.model.object.LottoNumber;
import lotto.model.object.WinningInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WinStatsCreatorTest {
        
        @Test
        void 당첨_통계_생성_검사() {
                List<LottoNumber> lottoNumbers1 = new ArrayList<>();
                lottoNumbers1.add(LottoNumberCreator.create(1));
                lottoNumbers1.add(LottoNumberCreator.create(2));
                lottoNumbers1.add(LottoNumberCreator.create(3));
                lottoNumbers1.add(LottoNumberCreator.create(4));
                lottoNumbers1.add(LottoNumberCreator.create(5));
                lottoNumbers1.add(LottoNumberCreator.create(6));
                Lotto purchasedLotto1 = new Lotto(lottoNumbers1);

                List<LottoNumber> lottoNumbers2 = new ArrayList<>();
                lottoNumbers2.add(LottoNumberCreator.create(10));
                lottoNumbers2.add(LottoNumberCreator.create(2));
                lottoNumbers2.add(LottoNumberCreator.create(3));
                lottoNumbers2.add(LottoNumberCreator.create(4));
                lottoNumbers2.add(LottoNumberCreator.create(5));
                lottoNumbers2.add(LottoNumberCreator.create(6));
                Lotto purchasedLotto2 = new Lotto(lottoNumbers2);

                List<Lotto> purchasedLottos = new ArrayList<>();
                purchasedLottos.add(purchasedLotto1);
                purchasedLottos.add(purchasedLotto2);
                Lotto winningLotto = LottoCreator.create(new String[]{"1", "2", "3", "4", "5", "6"});

                BonusBall bonusBall = BonusBallCreator.create(10);

                WinningInfo winningInfo = WinningInfoCreator.create(winningLotto, bonusBall);

                assertThat(WinStatsCreator.create(purchasedLottos, winningInfo).getMappingStats().get(LottoRank.SECOND)).isEqualTo(1);
        }
}

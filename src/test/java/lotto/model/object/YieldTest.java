package lotto.model.object;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.offset;

class YieldTest {
        @Test
        void 수익률_생성_검사() {
                Payment payment = new Payment(5000);

                List<LottoNumber> lottoNumbers1 = new ArrayList<>();
                lottoNumbers1.add(LottoNumber.getInstance(3));
                lottoNumbers1.add(LottoNumber.getInstance(4));
                lottoNumbers1.add(LottoNumber.getInstance(5));
                lottoNumbers1.add(LottoNumber.getInstance(6));
                lottoNumbers1.add(LottoNumber.getInstance(7));
                lottoNumbers1.add(LottoNumber.getInstance(8));
                Lotto purchasedLotto1 = new Lotto(lottoNumbers1);

                List<LottoNumber> lottoNumbers2 = new ArrayList<>();
                lottoNumbers2.add(LottoNumber.getInstance(4));
                lottoNumbers2.add(LottoNumber.getInstance(5));
                lottoNumbers2.add(LottoNumber.getInstance(6));
                lottoNumbers2.add(LottoNumber.getInstance(7));
                lottoNumbers2.add(LottoNumber.getInstance(8));
                lottoNumbers2.add(LottoNumber.getInstance(9));
                Lotto purchasedLotto2 = new Lotto(lottoNumbers2);

                List<Lotto> purchasedLottos = new ArrayList<>();
                purchasedLottos.add(purchasedLotto1);
                purchasedLottos.add(purchasedLotto2);

                Lotto winningLotto = new Lotto(new String[]{"1", "2", "3", "4", "5", "6"});

                BonusBall bonusBall = new BonusBall(10);

                WinningInfo winningInfo = new WinningInfo(winningLotto, bonusBall);

                WinStats winStats = new WinStats(purchasedLottos, winningInfo);

                Yield yield = new Yield(payment, winStats);

                assertThat(yield.getRate()).isEqualTo(11, offset(0.0001));
        }
}
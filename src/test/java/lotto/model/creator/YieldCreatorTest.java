package lotto.model.creator;

import lotto.model.object.Lotto;
import lotto.model.object.LottoNumber;
import lotto.model.object.Payment;
import lotto.model.object.WinStats;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

public class YieldCreatorTest {
        @Test
        void 수익률_생성_검사() {
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
                lottoNumbers2.add(LottoNumberCreator.create(20));
                lottoNumbers2.add(LottoNumberCreator.create(4));
                lottoNumbers2.add(LottoNumberCreator.create(30));
                lottoNumbers2.add(LottoNumberCreator.create(6));
                Lotto purchasedLotto2 = new Lotto(lottoNumbers2);

                List<Lotto> purchasedLottos = new ArrayList<>();
                purchasedLottos.add(purchasedLotto1);
                purchasedLottos.add(purchasedLotto2);
                Lotto winnerLotto = WinnerLottoCreator.create(new String[]{"1", "2", "3", "4", "5", "6"});

                Payment payment = new Payment(5000);
                assertThat(YieldCreator.create(payment, WinStatsCreator.create(purchasedLottos, winnerLotto)).getNumber()).isEqualTo(400_001, offset(0.0001));
        }
}

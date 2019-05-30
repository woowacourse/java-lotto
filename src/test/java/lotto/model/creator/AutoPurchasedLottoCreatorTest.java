package lotto.model.creator;

import lotto.model.object.Lotto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AutoPurchasedLottoCreatorTest {

        @Test
        void 자동_구매_로또_생성_검사() {
                List<Integer> lottoNumbers = new ArrayList<>();
                lottoNumbers.add(1);
                lottoNumbers.add(2);
                lottoNumbers.add(3);
                lottoNumbers.add(4);
                lottoNumbers.add(5);
                lottoNumbers.add(6);
                
                Lotto purchasedLotto = AutoPurchasedLottoCreator.create(lottoNumbers);

                assertThat(purchasedLotto.getLottoNumbers().get(0)).isEqualTo(LottoNumberCreator.create(1));
                assertThat(purchasedLotto.getLottoNumbers().get(1)).isEqualTo(LottoNumberCreator.create(2));
                assertThat(purchasedLotto.getLottoNumbers().get(2)).isEqualTo(LottoNumberCreator.create(3));
                assertThat(purchasedLotto.getLottoNumbers().get(3)).isEqualTo(LottoNumberCreator.create(4));
                assertThat(purchasedLotto.getLottoNumbers().get(4)).isEqualTo(LottoNumberCreator.create(5));
                assertThat(purchasedLotto.getLottoNumbers().get(5)).isEqualTo(LottoNumberCreator.create(6));

        }

}
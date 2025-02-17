package lotto.domain;

import static lotto.domain.Lotto.LOTTO_SIZE;
import static lotto.domain.LottoNumber.LOTTO_RANGE_MAXIMUM;
import static lotto.domain.LottoNumber.LOTTO_RANGE_MINIMUM;
import static lotto.domain.LottoShop.LOTTO_PRIZE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class LottoShopTest {

    @DisplayName("돈에 맞게 로또가 구매되는지 확인")
    @Test
    void test_byLottos() {
        // given
        LottoShop lottoShop = new LottoShop(
                new RandomNumbersGenerator(LOTTO_RANGE_MINIMUM, LOTTO_RANGE_MAXIMUM, LOTTO_SIZE));
        int money = LOTTO_PRIZE * 5;

        // when
        List<Lotto> lottos = lottoShop.buyLottos(money);

        // then
        assertThat(lottos.size()).isEqualTo(5);
    }

}

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

    @DisplayName("돈에 맞춰 로또 개수를 구매하는지 확인")
    @Test
    void test_byLottos() {
        // given
        Money money = new Money(LOTTO_PRIZE * 5, LOTTO_PRIZE);

        LottoShop lottoShop = new LottoShop(
                new RandomNumbersGenerator(LOTTO_RANGE_MINIMUM, LOTTO_RANGE_MAXIMUM, LOTTO_SIZE));

        // when
        List<Lotto> lottos = lottoShop.buyLottos(money);

        // then
        assertThat(lottos.size()).isEqualTo(5);
    }

}

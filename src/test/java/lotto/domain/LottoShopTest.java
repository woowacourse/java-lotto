package lotto.domain;

import static lotto.common.constant.Constant.LOTTO_PRIZE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class LottoShopTest {

    @DisplayName("돈에 맞게 로또가 구매되는지 확인")
    @Test
    void test_byLottos() {
        // given
        LottoShop lottoShop = new LottoShop(new LottoNumberGenerator());
        Money money = new Money(LOTTO_PRIZE * 5);

        // when
        List<Lotto> lottos = lottoShop.buyLottos(money);

        // then
        assertThat(lottos.size()).isEqualTo(money.getAmount());
    }

}

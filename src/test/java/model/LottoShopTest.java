package model;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoShopTest {

    @Test
    @DisplayName("구매 가격을 기반으로 로또 구매 개수 계산")
    void calculateLottoAmount() {

        LottoShop lottoShop = new LottoShop();
        int price = 14000;
        int expect = 14;

        int count = lottoShop.calculateLottoAmount(price);

        Assertions.assertThat(count).isEqualTo(expect);
    }

    @Test
    @DisplayName("구매 개수를 기반으로 Lottos 객체 생성")
    void buyLottos() {
        LottoShop lottoShop = new LottoShop();
        int price = 14000;
        int count = 14;
        Lottos lottos = lottoShop.buyLottos(price);

        assertAll(
                () -> Assertions.assertThat(lottos).isInstanceOf(Lottos.class),
                () -> Assertions.assertThat(lottos.getLottos()).hasSize(count),
                () -> Assertions.assertThat(lottos.getLottos()).allSatisfy(
                        lotto -> Assertions.assertThat(lotto).isInstanceOf(Lotto.class)
                )
        );
    }
}
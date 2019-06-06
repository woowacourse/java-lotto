package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoFactoryTest {
    @Test
    void 입력받은_금액만큼_로또를_제대로_발행해주는지_테스트() {
        PurchaseAmount purchaseAmount = PurchaseAmount.of(5000);
        List<Lotto> issuedLottos = LottoFactory.issueLottoWorthOf(purchaseAmount);

        assertThat(issuedLottos.size()).isEqualTo(5);
    }
}
package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    
    @Test
    @DisplayName("자동 로또 생성 테스트")
    void init() {
        // given
        PaymentAmount paymentAmount = PaymentAmount.from("1000");
        PurchaseCount purchaseCount = PurchaseCount.of(paymentAmount, "0");
        
        // when
        Lottos lottos = LottosFactory.makeLottos(purchaseCount);
        
        // then
        assertThat(lottos.getLottos().size()).isEqualTo(1);
    }
}

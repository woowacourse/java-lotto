package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    
    @Test
    @DisplayName("구매 로또 생성 테스트")
    void init() {
        // given
        String paymentAmount = "1000";
        int purchaseCount = 1;
        
        // when
        Lottos lottos = LottoFactory.makeLottos(PaymentAmount.from(paymentAmount));
        
        
        // then
        assertThat(lottos.toInts().size()).isEqualTo(purchaseCount);
    }
}

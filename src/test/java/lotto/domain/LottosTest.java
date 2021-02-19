package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {
    
    @Test
    @DisplayName("구매 로또 생성 테스트")
    void init() {
        // given
        int purchaseCount = 10;
        
        // when
        Lottos lottos = Lottos.makeLottos(purchaseCount);
        
        
        // then
        assertThat(lottos.toInts()
                         .size()).isEqualTo(10);
    }
}

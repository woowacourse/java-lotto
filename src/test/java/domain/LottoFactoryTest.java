package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

    @Test
    @DisplayName("로또 발행 테스트")
    public void success_1() {
        int input = 14000;
        Money money = new Money(input);
        LottoFactory lottoFactory = new LottoFactory(money);
        Assertions.assertThat(lottoFactory.getLottoSize()).isEqualTo(14);
    }
}

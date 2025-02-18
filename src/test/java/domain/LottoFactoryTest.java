package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoFactoryTest {

    @Test
    @DisplayName("로또 발행 개수 테스트")
    public void success_1() {
        int input = 14000;
        Money money = new Money(input);
        Assertions.assertThat(LottoFactory.createLottos(money).size()).isEqualTo(14);
    }
}

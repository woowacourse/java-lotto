package domain;

import generator.DefaultLottoNumber;
import generator.RandomGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BuyerTest {

    @Test
    @DisplayName("로또 발행 테스트")
    public void success_1() {
        RandomGenerator randomGenerator = new DefaultLottoNumber();
        int input = 14000;
        Money money = new Money(input);
        Buyer buyer = new Buyer(money, randomGenerator);
        Assertions.assertThat(buyer.getLottoSize()).isEqualTo(14);
    }
}

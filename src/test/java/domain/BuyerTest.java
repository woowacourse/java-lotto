package domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import generator.DefaultLottoNumber;
import generator.FixedLottoNumber;
import generator.RandomGenerator;
import java.util.Arrays;
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

    @Test
    void testBuyerCreatesLottosWithFixedNumbers() {
        FixedLottoNumber fixedRandomGenerator = new FixedLottoNumber(Arrays.asList(1, 2, 3, 4, 5, 6));

        Money money = new Money(1000);
        Buyer buyer = new Buyer(money, fixedRandomGenerator);

        String result = buyer.createResult();

        assertTrue(result.contains("[1, 2, 3, 4, 5, 6]"), "로또 번호는 고정된 값이어야 합니다.");
    }
}

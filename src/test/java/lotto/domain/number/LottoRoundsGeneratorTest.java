package lotto.domain.number;

import lotto.domain.result.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoRoundsGeneratorTest {
    @Test
    void 로또_생성_test() {
        Money money = new Money(5000);
        assertThat(LottoRoundsGenerator.createLottoRounds(money)).hasSize(5);
    }
}

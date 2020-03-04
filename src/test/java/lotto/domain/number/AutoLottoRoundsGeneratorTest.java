package lotto.domain.number;

import lotto.domain.result.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AutoLottoRoundsGeneratorTest {

    @Test
    void generate() {
        Money money = new Money(5000, LottoRoundsGenerator.LOTTO_PRICE);
        AutoLottoRoundsGenerator autoLottoRoundsGenerator = new AutoLottoRoundsGenerator();
        LottoRounds result = autoLottoRoundsGenerator.generate(money);
        assertThat(result.getAllLottoNumbers()).hasSize(5);
    }
}
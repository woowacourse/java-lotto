package lotto.domain.number;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoChanceTest {

    @Test
    @DisplayName("구입 금액에비해 많은 수동 개수의 입력을 할 경우 예외")
    void newLottoChanceWithBiggerPassiveChance() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            Payout.valueOf("5000").newLottoChance(Chance.valueOf("6"))
        );
    }

    @Test
    @DisplayName("정상적인 수동 개수 입력")
    void newLottoChance() {
        LottoChance lottoChance = Payout.valueOf("5000").newLottoChance(Chance.valueOf("5"));

        assertThat(lottoChance.getActiveChance()).isEqualTo(0);
        assertThat(lottoChance.getPassiveChance()).isEqualTo(5);
    }
}

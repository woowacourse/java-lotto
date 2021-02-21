package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoAmountTest {

    @DisplayName("구입 하려는 로또 수량 생성 테스트")
    @Test
    void testCreateLottoAmount() {
        Money money = new Money("14000");
        String manual = "4";
        LottoAmount lottoAmount = new LottoAmount(money, manual);

        assertThat(lottoAmount.toManualAmountNumber()).isEqualTo(4);
        assertThat(lottoAmount.toAutoAmountNumber()).isEqualTo(10);
    }

    @Test
    void testValidateLottoAmount() {
        Money money = new Money("14000");
        String wrongManual1 = "";
        String wrongManual2 = "asd";
        String wrongManual4 = "1500";

        assertThatIllegalArgumentException().isThrownBy(() -> new LottoAmount(money, wrongManual1));
        assertThatIllegalArgumentException().isThrownBy(() -> new LottoAmount(money, wrongManual2));
        assertThatIllegalArgumentException().isThrownBy(() -> new LottoAmount(money, wrongManual4));
    }
}

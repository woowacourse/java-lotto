package lotto.domain.lotto;

import lotto.domain.number.ManualPurchasesNumber;
import lotto.domain.number.PayOut;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ManualPurchasesNumberTest {
    @Test
    @DisplayName("수동으로 구매할 로또 수를 생성한다.")
    void manualPurchasesNumber() {
        assertThat(new ManualPurchasesNumber(1, new PayOut(3000)).getValueAsInt()).isEqualTo(1);
        assertThat(new ManualPurchasesNumber(2, new PayOut(3000)).getValueAsInt()).isEqualTo(2);
        assertThat(new ManualPurchasesNumber(3, new PayOut(3000)).getValueAsInt()).isEqualTo(3);
    }

    @Test
    @DisplayName("구입 금액보다 게임 횟수가 많으면 에러.")
    void numberOfGamesIsMoreThenThePurchaseAmount() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new ManualPurchasesNumber(3, new PayOut(1000)).getValueAsInt()
        ).withMessage("가능한 게임 횟수를 초과했습니다.");
    }
}

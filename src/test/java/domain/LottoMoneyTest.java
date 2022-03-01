package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoMoneyTest {

    @Test
    @DisplayName("돈을 생성하는 기능")
    void createMoney() {
        LottoMoney lottoMoney = new LottoMoney(1000);

        assertThat(lottoMoney).isNotNull();
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -1, 0, 999})
    @DisplayName("돈을 생성하지 못하는 경우")
    void createInvalidMoney(int amount) {
        assertThatThrownBy(() -> new LottoMoney(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구매 금액을 통해 수동, 자동 로또의 갯수를 구하는 기능")
    void calculatePurchaseCount() {
        LottoMoney lottoMoney = new LottoMoney(4000);
        int manualCount = 3;

        LottoPurchaseCount lottoPurchaseCount = lottoMoney.calculateLottoCountRefactor(manualCount);

        assertThat(lottoPurchaseCount).isEqualTo(new LottoPurchaseCount(3, 1));
    }

    @Test
    @DisplayName("구매 금액보다 수동 로또를 구매 갯수가 초과한 경우")
    void calculateInvalidManualCount() {
        LottoMoney lottoMoney = new LottoMoney(2000);
        int manualCount = 3;

        assertThatThrownBy(() -> lottoMoney.calculateLottoCountRefactor(manualCount))
            .isInstanceOf(IllegalArgumentException.class);
    }
}

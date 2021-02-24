package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import lotto.domain.lotto.util.PurchaseCount;
import lotto.domain.lotto.util.LottoMoney;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoMoneyTest {

    private LottoMoney lottoMoney;

    @BeforeEach
    void setUp() {
        lottoMoney = new LottoMoney(10000);
    }

    @Test
    @DisplayName("구매가능한 로또 라인 개수를 반환한다.")
    void testGetCanBuyLottoLineCount() {
        assertThat(lottoMoney.getCanBuyLottoLineCount()).isEqualTo(10);
    }

    @Test
    @DisplayName("로또 구매 개수만큼 금액이 차감된다.")
    void testSpendLottoLine() {
        PurchaseCount purchaseCount = new PurchaseCount(5);
        assertThat(lottoMoney.spendLottoLine(purchaseCount).getCanBuyLottoLineCount()).isEqualTo(5);
    }

}
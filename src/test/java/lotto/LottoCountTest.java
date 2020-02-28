package lotto;

import domain.LottoCount;
import domain.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoCountTest {
    @Test
    @DisplayName("구매금액이 주어질 때 로또 구매 개수 확인")
    void calculateLottoCountTest() {
        Money money = new Money("14800");
        LottoCount lottoCount = new LottoCount(money.getLottoCount(), 3);
        assertThat(lottoCount.getTotalCount()).isEqualTo(14);
        assertThat(lottoCount.getAutoCount()).isEqualTo(11);
        assertThat(lottoCount.getManualCount()).isEqualTo(3);
    }
}

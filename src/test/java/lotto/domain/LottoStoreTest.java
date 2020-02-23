package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoStoreTest {
    @Test
    @DisplayName("로또가 자동으로 생성되는지 확인하는 테스트")
    void correct_lotto_ticket_test() {
        PurchaseAmount purchaseAmount = new PurchaseAmount("5000");
        LottoStore lottoStore = new LottoStore(purchaseAmount);
        LottoTickets lottoTickets = lottoStore.getLottoTickets();
        assertThat(lottoTickets.getLottoTickets()).hasSize(5);
    }
}

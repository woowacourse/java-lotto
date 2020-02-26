package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoStoreTest {
    @Test
    @DisplayName("로또가 자동으로 생성되는지 확인하는 테스트")
    void correct_automatic_lotto_ticket_test() {
        PurchaseAmount purchaseAmount = new PurchaseAmount("5000");
        LottoTicketNumber lottoTicketNumber = new LottoTicketNumber(purchaseAmount.giveLottoTicketNumber(), 0);
        LottoStore lottoStore = new LottoStore(lottoTicketNumber);
        LottoTickets lottoTickets = lottoStore.generateLottoTickets(new ArrayList<>());
        assertThat(lottoTickets.getLottoTickets()).hasSize(5);
    }

    @Test
    @DisplayName("로또가 수동으로 생성되는지 확인하는 테스트")
    void correct_manual_lotto_ticket_test() {
        List<String> manualLottoBallsInputs = new ArrayList<>();
        manualLottoBallsInputs.add("1,2,3,4,5,6");
        PurchaseAmount purchaseAmount = new PurchaseAmount("1000");
        LottoTicketNumber lottoTicketNumber = new LottoTicketNumber(purchaseAmount.giveLottoTicketNumber(), 1);
        LottoStore lottoStore = new LottoStore(lottoTicketNumber);
        LottoTickets lottoTickets = lottoStore.generateLottoTickets(manualLottoBallsInputs);
        assertThat(lottoTickets.getLottoTickets()).hasSize(1);
    }
}

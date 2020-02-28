package lotto.domain;

import lotto.domain.LottoTicketNumber.AutomaticLottoTicketNumber;
import lotto.domain.LottoTicketNumber.ManualLottoTicketNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoStoreTest {
    private final static PurchaseAmount PURCHASE_AMOUNT = new PurchaseAmount("1000");
    private final static int TOTAL_LOTTO_TICKET_NUMBER = PURCHASE_AMOUNT.giveTotalLottoTicketNumber();

    @Test
    @DisplayName("로또가 자동으로 생성되는지 확인하는 테스트")
    void correct_automatic_lotto_ticket_test() {
        AutomaticLottoTicketNumber automaticLottoTicketNumber = new AutomaticLottoTicketNumber(0, TOTAL_LOTTO_TICKET_NUMBER);
        LottoStore lottoStore = new LottoStore(new ManualLottoTicketNumber(1, TOTAL_LOTTO_TICKET_NUMBER), automaticLottoTicketNumber);
        LottoTickets lottoTickets = lottoStore.generateLottoTickets(new ArrayList<>());
        assertThat(lottoTickets.getLottoTickets()).hasSize(1);
    }

    @Test
    @DisplayName("로또가 수동으로 생성되는지 확인하는 테스트")
    void correct_manual_lotto_ticket_test() {
        List<Set<LottoBall>> manualLottoBallsInputs = new ArrayList<>();
        manualLottoBallsInputs.add(LottoBalls.generateLottoBalls("1,2,3,4,5,6"));
        AutomaticLottoTicketNumber automaticLottoTicketNumber = new AutomaticLottoTicketNumber(1, TOTAL_LOTTO_TICKET_NUMBER);
        LottoStore lottoStore = new LottoStore(new ManualLottoTicketNumber(0, TOTAL_LOTTO_TICKET_NUMBER), automaticLottoTicketNumber);
        LottoTickets lottoTickets = lottoStore.generateLottoTickets(manualLottoBallsInputs);
        assertThat(lottoTickets.getLottoTickets()).hasSize(1);
    }
}

package domain;

import controller.LottoController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoTicketsTest {
    @DisplayName("concat 을 통해 ManualLottoTickets 와 AutoLottoTickets 가 합쳐지는지 테스트")
    @Test
    void concatManualLottoTicketsWithAutoLottoTicketsTest() {
        List<String> input = new ArrayList(Arrays.asList(
                "1, 2, 3, 4, 5, 6",
                "3, 4, 5, 6, 7, 8"
        ));
        ManualLottoTickets manualLottoTickets = new ManualLottoTickets(
                ManualLottoTicketsGenerator.generateManualLottoTickets(input));
        Money money = new Money(14000);
        List<LottoTicket> autoLottoTicketList = AutoLottoTicketsGenerator.generateAutoLottoTickets(
                money.getLottoTicketCount());
        AutoLottoTickets autoLottoTickets = new AutoLottoTickets(autoLottoTicketList);

        LottoTickets lottoTickets = new LottoTickets(LottoController.concatManualTicketsWithAutoTickets(
                manualLottoTickets, autoLottoTickets));
        Assertions.assertThat(lottoTickets.getTickets()).containsSequence(manualLottoTickets.getTickets());
        Assertions.assertThat(lottoTickets.getTickets()).containsSequence(autoLottoTickets.getTickets());
    }
}

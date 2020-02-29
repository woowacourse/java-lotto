package lotto.domain.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {

    @Test
    @DisplayName("LottoTicket들로부터 LottoTickets 생성")
    void createLottoTickets() {
        List<LottoTicket> manualLottoTicketList = new ArrayList<>();
        Set<Integer> firstLotto = IntStream.rangeClosed(2, 7).boxed().collect(Collectors.toSet());
        manualLottoTicketList.add(LottoFactory.publishLottoTicketFrom(firstLotto));
        LottoTickets manualLottoTickets = new LottoTickets(manualLottoTicketList);

        List<LottoTicket> autoLottoTicketsList = new ArrayList<>();
        autoLottoTicketsList.add(LottoFactory.publishLottoTicketOfRandom());
        LottoTickets autoLottoTickets = new LottoTickets(autoLottoTicketsList);

        LottoTickets lottoTickets = LottoTickets.createFrom(manualLottoTickets, autoLottoTickets);

        int expectedSize = manualLottoTickets.getLottoTickets().size() + autoLottoTickets.getLottoTickets().size();
        assertThat(lottoTickets.getLottoTickets()).size().isEqualTo(expectedSize);

    }
}

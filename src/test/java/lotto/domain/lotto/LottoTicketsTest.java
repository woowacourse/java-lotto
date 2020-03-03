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
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        Set<LottoNumber> firstLotto = IntStream.rangeClosed(2, 7)
                .mapToObj(LottoNumber::from)
                .collect(Collectors.toSet());
        lottoTicketList.add(LottoTicket.from(firstLotto));

        LottoTickets lottoTickets = LottoTickets.from(lottoTicketList);

        assertThat(lottoTickets.getLottoTickets()).size().isEqualTo(1);
    }
}

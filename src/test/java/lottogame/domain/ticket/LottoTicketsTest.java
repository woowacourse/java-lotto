package lottogame.domain.ticket;

import lottogame.domain.number.LottoNumber;
import lottogame.domain.number.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {

    @Test
    @DisplayName("LottoTicket 삽입 테스트")
    void lottoTicketsAddTest() {
        LottoTicket lottoTicket1 = LottoTicket.of();

        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(lottoTicket1);

        assertThat(lottoTickets.toList().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("수동 티켓이 1개, 자동 티켓이 1개 삽입 되었을때 올바른 결과를 가져온다.")
    void lottoTicketCount() {
        LottoTicket autoTicket = LottoTicket.of();
        List<LottoNumber> lottoNumberGroup = new ArrayList<>();
        for (int i = 1; i <= 6; ++i) {
            lottoNumberGroup.add(LottoNumber.of(i));
        }
        LottoTicket manualTicket = LottoTicket.of(new LottoNumbers(lottoNumberGroup));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(manualTicket);
        lottoTickets.add(autoTicket);

        assertThat(lottoTickets.getAutoTicketsCount()).isEqualTo(1);
        assertThat(lottoTickets.getManualTicketsCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("수동 티켓 1개 구매, 자동 티켓 2개 구매 후 총 티켓이 3개임을 가져온다.")
    void AllLottoTicketsCount() {
        List<LottoNumber> lottoNumberGroup = new ArrayList<>();
        for (int i = 1; i <= 6; ++i) {
            lottoNumberGroup.add(LottoNumber.of(i));
        }
        LottoTicket manualTicket = LottoTicket.of(new LottoNumbers(lottoNumberGroup));
        LottoTickets lottoTickets = new LottoTickets();
        lottoTickets.add(manualTicket);

        LottoTickets lottoAutoTickets = new LottoTickets();
        lottoAutoTickets.add(LottoTicket.of());
        lottoAutoTickets.add(LottoTicket.of());

        lottoTickets.concat(lottoAutoTickets);
        assertThat(lottoTickets.toList().size()).isEqualTo(3);
    }
}

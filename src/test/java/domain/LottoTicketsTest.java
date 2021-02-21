package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    @DisplayName("전체 로또 티켓을 반환한다.")
    @Test
    public void createLottoTickets() {
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(
                new LottoTicket(LottoNumberRepository.shuffleLottoNumbers()),
                new LottoTicket(LottoNumberRepository.shuffleLottoNumbers()),
                new LottoTicket(LottoNumberRepository.shuffleLottoNumbers())
        ));

        assertThat(lottoTickets).isInstanceOf(LottoTickets.class);
    }
}

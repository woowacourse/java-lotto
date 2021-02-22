package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    @DisplayName("LottoTickets는 LottoTicket 리스트를 인자로 받아 생성된다.")
    @Test
    public void createLottoTickets() {
        LottoTickets lottoTickets = new LottoTickets(Arrays.asList(
                LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers()),
                LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers()),
                LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers())
        ));
        assertThat(lottoTickets).isInstanceOf(LottoTickets.class);
    }
}

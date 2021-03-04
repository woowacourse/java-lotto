package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
    LottoTickets lottoTickets;

    @BeforeEach
    @Test
    void setUp() {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.addAll(Arrays.asList(
                LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers()),
                LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers())));
        lottoTickets = LottoTickets.of(lottoTicketList);
    }

    @DisplayName("LottoTickets는 LottoTicket 리스트를 인자로 받아 생성된다.")
    @Test
    public void createLottoTickets() {
        LottoTickets lottoTickets = LottoTickets.of(Arrays.asList(
                LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers()),
                LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers()),
                LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers())
        ));
        assertThat(lottoTickets).isInstanceOf(LottoTickets.class);
    }

    @DisplayName("LottoTickets는 LottoTicket를 추가할 수 있다.")
    @Test
    public void addLottoTicketTest() {
        lottoTickets.add(LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers()));

        assertThat(lottoTickets.size()).isEqualTo(3);
    }

    @DisplayName("LottoTickets는 LottoTickets를 추가로 받을 수 있다.")
    @Test
    public void addAllLottoTicketsTest() {
        LottoTickets lottoTicketsToBeAppended = LottoTickets.of(Arrays.asList(
                LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers()),
                LottoTicket.of(LottoNumberRepository.shuffleLottoNumbers())
        ));
        lottoTickets.addAll(lottoTicketsToBeAppended);

        assertThat(lottoTickets.size()).isEqualTo(4);
    }
}

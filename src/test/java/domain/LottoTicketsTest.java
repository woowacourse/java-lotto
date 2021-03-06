package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

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

    @DisplayName("일치하는 번호 개수에 따른 통계값을 구해 LottoResult로 리턴한.")
    @Test
    public void generateLottoResultTest() {
        WinningNumbers winningNumbers = WinningNumbers.of(
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoNumber.from(7));
        LottoTickets lottoTickets = LottoTickets.of(Arrays.asList(
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 6)),
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 5, 7)),
                LottoTicket.valueOf(Arrays.asList(1, 2, 3, 4, 6, 7)),
                LottoTicket.valueOf(Arrays.asList(9, 10, 11, 12, 13, 14))
        ));

        LottoResult lottoResult = lottoTickets.generateLottoResult(winningNumbers);
        Map<Rank, Integer> lottoStatisticsMap = lottoResult.result();
        assertThat(lottoStatisticsMap).isEqualTo(new HashMap<Rank, Integer>() {
            {
                put(Rank.FIRST, 1);
                put(Rank.SECOND, 2);
                put(Rank.NOTHING, 1);
            }
        });
    }
}

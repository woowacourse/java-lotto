package lotto.service;

import lotto.domain.result.win.prize.PrizeGroup;
import lotto.domain.ticket.LottoStore;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.StubLottoStore;
import lotto.view.dto.BettingMoneyRequestDTO;
import lotto.view.dto.StatisticsResponseDTO;
import lotto.view.dto.WinningLottoRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoServiceTest {

    private LottoService lottoService;

    private static Stream<Arguments> ticketProvider() {
        return Stream.of(
                Arguments.of(1000, Arrays.asList(new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)))),
                Arguments.of(2000, Arrays.asList(new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)), new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6))))
        );
    }

    @BeforeEach
    void setUp() {
        LottoStore lottoStore = new StubLottoStore();
        lottoService = new LottoService(lottoStore);
    }

    @DisplayName("넣은 금액으로 로또 티켓 생성 확인")
    @ParameterizedTest
    @MethodSource("ticketProvider")
    void getLottoTicketBundle(int bettingMoney, List<LottoTicket> expectTickets) {
        //given
        BettingMoneyRequestDTO bettingMoneyRequestDTO = new BettingMoneyRequestDTO(bettingMoney);

        //when
        LottoTicketBundle lottoTicketBundle = lottoService.getLottoTicketBundle(bettingMoneyRequestDTO);

        //then
        assertThat(lottoTicketBundle).isEqualTo(new LottoTicketBundle(expectTickets));
    }


    @DisplayName("발급받은 티켓과 우승번호를 비교하여 통계결과 생성")
    @Test
    void getStatisticsDTO() {
        //given
        List<Integer> lottoNumbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        String winningNumber = "1,2,3,4,5,6";
        int bonusNumber = 7;
        double rate = 2000000;

        LottoTicketBundle lottoTicketBundle = new LottoTicketBundle(Collections.singletonList(new LottoTicket(lottoNumbers)));
        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO(winningNumber, bonusNumber);

        //when
        StatisticsResponseDTO statisticsDTO = lottoService.getStatisticsDTO(lottoTicketBundle, winningLottoRequestDTO);

        //then
        assertAll(
                "통계 생성시 전체 사이즈는 6으로 고정",
                () -> assertThat(statisticsDTO.size()).isEqualTo(6),
                () -> assertThat(statisticsDTO.getDefaultPrize(0)).isEqualTo(2000000000),
                () -> assertThat(statisticsDTO.getMatchCount(0)).isEqualTo(6),
                () -> assertThat(statisticsDTO.getMatchTicketCount(0)).isEqualTo(1),
                () -> assertThat(statisticsDTO.getName(0)).isEqualTo(PrizeGroup.FIRST.name()),
                () -> assertThat(statisticsDTO.getRate()).isEqualTo(rate)
        );

    }
}
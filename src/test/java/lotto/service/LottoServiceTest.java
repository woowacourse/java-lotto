package lotto.service;

import lotto.domain.result.win.prize.PrizeGroup;
import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoStore;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.StubLottoStore;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoServiceTest {

    private LottoService lottoService;

    private static Stream<Arguments> ticketProvider() {
        return Stream.of(
                Arguments.of(1000, Arrays.asList(new LottoTicket(aLottoBalls()))),
                Arguments.of(2000, Arrays.asList(new LottoTicket(aLottoBalls()), new LottoTicket(aLottoBalls())))
        );
    }

    private static Set<LottoBall> aLottoBalls() {
        LottoBall one = LottoBallFactory.findLottoBallByNumber(1);
        LottoBall two = LottoBallFactory.findLottoBallByNumber(2);
        LottoBall three = LottoBallFactory.findLottoBallByNumber(3);
        LottoBall four = LottoBallFactory.findLottoBallByNumber(4);
        LottoBall five = LottoBallFactory.findLottoBallByNumber(5);
        LottoBall six = LottoBallFactory.findLottoBallByNumber(6);
        return new HashSet<>(Arrays.asList(one, two, three, four, five, six));
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
        BettingMoney bettingMoneyRequestDTO = new BettingMoney(bettingMoney);

        //when
        LottoTicketBundle lottoTicketBundle = lottoService.getLottoTicketBundle(bettingMoneyRequestDTO);

        //then
        assertThat(lottoTicketBundle).isEqualTo(new LottoTicketBundle(expectTickets));
    }


    @DisplayName("발급받은 티켓과 우승번호를 비교하여 통계결과 생성")
    @Test
    void getStatisticsDTO() {
        //given
        LottoTicket lottoTicket = new LottoTicket(aLottoBalls());
        String winningNumber = "12,22,33,4,5,6";
        int bonusNumber = 7;
        String rate = "500.0%";

        LottoTicketBundle lottoTicketBundle = new LottoTicketBundle(Collections.singletonList(lottoTicket));
        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO(winningNumber, bonusNumber);

        //when
        StatisticsResponseDTO statisticsDTO = lottoService.getStatisticsDTO(lottoTicketBundle, winningLottoRequestDTO);

        //then
        assertAll(
                "통계 생성시 전체 사이즈는 6으로 고정",
                () -> assertThat(statisticsDTO.size()).isEqualTo(6),
                () -> assertThat(statisticsDTO.getDefaultPrize(4)).isEqualTo(5000),
                () -> assertThat(statisticsDTO.getMatchCount(4)).isEqualTo(3),
                () -> assertThat(statisticsDTO.getMatchTicketCount(4)).isEqualTo(1),
                () -> assertThat(statisticsDTO.getName(4)).isEqualTo(PrizeGroup.FIFTH.name()),
                () -> assertThat(statisticsDTO.getRate()).isEqualTo(rate)
        );

    }
}
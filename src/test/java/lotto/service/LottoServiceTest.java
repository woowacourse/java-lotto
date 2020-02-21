package lotto.service;

import lotto.domain.result.win.prize.PrizeGroup;
import lotto.domain.ticket.BettingMoney;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTicketBundle;
import lotto.domain.ticket.strategy.LottoMachine;
import lotto.view.dto.PrizeResponseBundleDTO;
import lotto.view.dto.WinningLottoRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static lotto.LottoHelper.lottoBalls;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class LottoServiceTest {

    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final int THIRD = 2;
    private static final int FOURTH = 3;
    private static final int FIFTH = 4;
    private static final int SIXTH = 5;

    private static Stream<Arguments> ticketProvider() {
        LottoTicket lottoTicket = new LottoTicket(lottoBalls(Arrays.asList(1, 2, 3, 4, 5, 6)));

        return Stream.of(
                Arguments.of(1000, Arrays.asList(lottoTicket)),
                Arguments.of(2000, Arrays.asList(lottoTicket, lottoTicket))
        );
    }

    @DisplayName("넣은 금액으로 로또 티켓 생성 확인")
    @ParameterizedTest
    @MethodSource("ticketProvider")
    void getLottoTicketBundle(int bettingMoney, List<LottoTicket> resultTickets) {
        //given
        BettingMoney bettingMoneyRequestDTO = new BettingMoney(bettingMoney);

        LottoMachine lottoMachine = money -> resultTickets;
        LottoService lottoService = new LottoService(lottoMachine);

        LottoTicketBundle result = new LottoTicketBundle(resultTickets);

        //when
        LottoTicketBundle lottoTicketBundle = lottoService.getLottoTicketBundle(bettingMoneyRequestDTO);

        //then
        assertThat(lottoTicketBundle).isEqualTo(result);
    }

    @DisplayName("1장으로 1등 당첨")
    @Test
    void winLotteryFirst() {
        //given
        LottoMachine lottoMachine = bettingMoney -> Arrays.asList(new LottoTicket(lottoBalls(Arrays.asList(1, 2, 3, 4, 5, 6))));
        LottoService lottoService = new LottoService(lottoMachine);

        BettingMoney bettingMoney = new BettingMoney(1000);
        String winningNumber = "1,2,3,4,5,6";
        int bonusNumber = 7;
        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO(winningNumber, bonusNumber);

        //when
        LottoTicketBundle lottoTicketBundle = lottoService.getLottoTicketBundle(bettingMoney);
        PrizeResponseBundleDTO statisticsDTO = lottoService.getStatisticsDTO(lottoTicketBundle, winningLottoRequestDTO);

        //then
        assertAll(
                () -> assertThat(statisticsDTO.getName(FIRST)).isEqualTo(PrizeGroup.FIRST.name()),
                () -> assertThat(statisticsDTO.getMatchTicketCount(FIRST)).isEqualTo(1),
                () -> assertThat(statisticsDTO.getMatchTicketCount(SECOND)).isEqualTo(0),
                () -> assertThat(statisticsDTO.getMatchTicketCount(THIRD)).isEqualTo(0),
                () -> assertThat(statisticsDTO.getMatchTicketCount(FOURTH)).isEqualTo(0),
                () -> assertThat(statisticsDTO.getMatchTicketCount(FIFTH)).isEqualTo(0),
                () -> assertThat(statisticsDTO.getMatchTicketCount(SIXTH)).isEqualTo(0)
        );
    }

    @DisplayName("2장으로 2등 당첨 1개 3등 1개")
    @Test
    void winLotterySecondAndThird() {
        //given
        LottoMachine lottoMachine = bettingMoney -> Arrays.asList(
                new LottoTicket(lottoBalls(Arrays.asList(1, 2, 3, 4, 7, 45))),
                new LottoTicket(lottoBalls(Arrays.asList(1, 2, 3, 4, 5, 45))));

        LottoService lottoService = new LottoService(lottoMachine);

        BettingMoney bettingMoney = new BettingMoney(2000);
        String winningNumber = "1,2,3,4,5,6";
        int bonusNumber = 7;
        WinningLottoRequestDTO winningLottoRequestDTO = new WinningLottoRequestDTO(winningNumber, bonusNumber);

        //when
        LottoTicketBundle lottoTicketBundle = lottoService.getLottoTicketBundle(bettingMoney);
        PrizeResponseBundleDTO statisticsDTO = lottoService.getStatisticsDTO(lottoTicketBundle, winningLottoRequestDTO);

        //then
        assertAll(
                () -> assertThat(statisticsDTO.getName(SECOND)).isEqualTo(PrizeGroup.SECOND.name()),
                () -> assertThat(statisticsDTO.getMatchTicketCount(FIRST)).isEqualTo(0),
                () -> assertThat(statisticsDTO.getMatchTicketCount(SECOND)).isEqualTo(1),
                () -> assertThat(statisticsDTO.getMatchTicketCount(THIRD)).isEqualTo(1),
                () -> assertThat(statisticsDTO.getMatchTicketCount(FOURTH)).isEqualTo(0),
                () -> assertThat(statisticsDTO.getMatchTicketCount(FIFTH)).isEqualTo(0),
                () -> assertThat(statisticsDTO.getMatchTicketCount(SIXTH)).isEqualTo(0)
        );
    }
}
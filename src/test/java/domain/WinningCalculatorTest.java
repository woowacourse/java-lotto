package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinningCalculatorTest {
    private WinningLottoTicket winningLottoTicket;
    private List<LottoTicket> lottoTicketList;

    @BeforeEach
    private void setUp() {
        winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6");
        winningLottoTicket.initializeBonusBall(new LottoNumber(7));
        lottoTicketList = new ArrayList<>();
    }

    @DisplayName("WinningCalculator 가 LottoTickets 와 WinningTicket 을 이용해 당첨된 복권이 몇개인지 계산하는 기능 테스트")
    @Test
    void getPrizeCountTest() {
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(7)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(8)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(8),
                new LottoNumber(9)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(8),
                new LottoNumber(9),
                new LottoNumber(10)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(11),
                new LottoNumber(8),
                new LottoNumber(9),
                new LottoNumber(10)))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(
                new LottoNumber(1),
                new LottoNumber(12),
                new LottoNumber(11),
                new LottoNumber(8),
                new LottoNumber(9),
                new LottoNumber(10)))));
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        WinningCalculator winningCalculator = new WinningCalculator();
        winningCalculator.calculateWinningCount(lottoTickets, winningLottoTicket);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.THREE)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FOUR)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FIVE)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FIVE_WITH_BONUS)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.SIX)).isEqualTo(1);
    }
}

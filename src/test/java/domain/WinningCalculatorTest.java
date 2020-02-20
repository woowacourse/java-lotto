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
        winningLottoTicket.initializeBonusBall("7");
        lottoTicketList = new ArrayList<>();
    }

    @DisplayName("WinningCalculator 가 LottoTickets 와 WinningTicket 을 이용해 당첨된 복권이 몇개인지 계산하는 기능 테스트")
    @Test
    public void getPrizeCountTest() {
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 7))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 8))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 4, 8, 9))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 2, 3, 8, 9, 10))));
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        WinningCalculator winningCalculator = new WinningCalculator();
        winningCalculator.calculateWinningCount(lottoTickets, winningLottoTicket);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.THREE)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FOUR)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FIVE)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FIVE_WITH_BONUS)).isEqualTo(1);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.SIX)).isEqualTo(1);
    }

    @DisplayName("당첨 통계가 3개 미만일 때 다음 복권으로 넘어가는 기능 테스트")
    @Test
    public void getPrizeCountUnderThreeTest() {
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(8, 9, 10, 11, 12, 13))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 8, 9, 10, 11, 12))));
        lottoTicketList.add(new LottoTicket(new ArrayList(Arrays.asList(1, 2, 8, 9, 10, 11))));
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        WinningCalculator winningCalculator = new WinningCalculator();
        winningCalculator.calculateWinningCount(lottoTickets, winningLottoTicket);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.THREE)).isEqualTo(0);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FOUR)).isEqualTo(0);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FIVE)).isEqualTo(0);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.FIVE_WITH_BONUS)).isEqualTo(0);
        Assertions.assertThat(winningCalculator.getPrizeTypeValue(PrizeType.SIX)).isEqualTo(0);
    }
}

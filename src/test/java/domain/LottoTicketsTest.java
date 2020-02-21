package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoTicketsTest {
    private WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6");
    private List<LottoTicket> originalLottoTickets = new ArrayList<>();

    @BeforeEach
    void setUp() {
        winningLottoTicket.initializeBonusBall("7");

        originalLottoTickets.add(new LottoTicket(Arrays.asList(1, 2, 3, 12, 13, 14)));
        originalLottoTickets.add(new LottoTicket(Arrays.asList(9, 5, 6, 4, 13, 14)));
    }

    @DisplayName("당첨 티켓과 매칭 테스트")
    @Test
    void lottoTicketsMatchTest() {
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6");
        winningLottoTicket.initializeBonusBall("7");

        LottoTickets lottoTickets = new LottoTickets(originalLottoTickets);
        LottoResults lottoResults = lottoTickets.match(winningLottoTicket);
        Assertions.assertThat(lottoResults.size()).isEqualTo(2);
    }

}

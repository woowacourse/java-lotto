package domain.lotto;

import static domain.lotto.LottoTicketTest.*;
import static domain.lotto.WinningLottoTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import domain.result.LottoResult;
import domain.result.Rank;

public class LottoTicketsTest {

    @Test
    void testLottoTickets() {
        List<LottoTicket> lottoTicketList = getLottoTicketListFixture();
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);
    }

    @ParameterizedTest
    @MethodSource("createRank")
    void getLottoResults(Rank rank) {
        List<LottoTicket> lottoTicketList = getLottoTicketListFixture();
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);
        WinningLotto winningLotto = getWinningLottoFixture();
        LottoResult lottoResult = lottoTickets.getLottoResults(winningLotto);
        assertThat(lottoTickets.getLottoTickets()).isEqualTo(lottoTicketList);
    }

    private static List<Rank> createRank() {
        return Arrays.asList(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH, Rank.MISS);
    }

}

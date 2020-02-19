package domain.lotto;

import static domain.lotto.LottoNumberTest.*;
import static domain.lotto.LottoTicketTest.*;
import static domain.lotto.WinningLottoTest.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoTicketsTest {

    @Test
    void testLottoTickets() {
        List<LottoTicket> lottoTicketList = getLottoTicketsFixture();
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);
    }

    @ParameterizedTest
    @MethodSource("createRank")
    void getLottoResults(Rank rank) {
        List<LottoTicket> lottoTicketList = getLottoTicketsFixture();
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);
        WinningLotto winningLotto = getWinningLottoFixture();
        List<Rank> lottoResults = lottoTickets.getLottoResults(winningLotto);

        assertThat(lottoResults.contains(rank)).isTrue();
    }

    private static List<Rank> createRank() {
        return Arrays.asList(Rank.FIRST, Rank.SECOND, Rank.THIRD, Rank.FOURTH, Rank.FIFTH, Rank.MISS);
    }

}

package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

public class LottoResultsTest {
    private List<LottoTicket> originalLottoTickets = new ArrayList<>();

    @BeforeEach
    void setUp() {
        originalLottoTickets.add(new LottoTicket(Arrays.asList(1, 2, 3, 12, 13, 14)));
        originalLottoTickets.add(new LottoTicket(Arrays.asList(1, 2, 3, 4, 13, 14)));
        originalLottoTickets.add(new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 14)));
        originalLottoTickets.add(new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 7)));
        originalLottoTickets.add(new LottoTicket(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

    @DisplayName("로또 당첨갯수 확인 메서드 테스트")
    @ParameterizedTest
    @CsvSource(value = {"MATCH_THREE,1", "MATCH_FOUR,1", "MATCH_FIVE,1", "MATCH_FIVE_WITH_BONUS,1", "MATCH_SIX,1"})
    void countWinningTickets(String lottoTypeStr, int expected) {
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6", "7");
        LottoTickets lottoTickets = new LottoTickets(originalLottoTickets);
        LottoResults lottoResults = lottoTickets.match(winningLottoTicket);
        HashMap<String, Integer> map = lottoResults.getCountMap();

        Assertions.assertThat(map.get(lottoTypeStr)).isEqualTo(expected);
    }
}

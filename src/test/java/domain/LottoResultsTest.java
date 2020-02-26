package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoResultsTest {

    private List<LottoTicket> originalLottoTickets = new ArrayList<>();

    @BeforeEach
    void setUp() {
        originalLottoTickets.add(new LottoTicket(IntStream.of(1, 2, 3, 12, 13, 14).mapToObj(LottoNumber::new).collect(Collectors.toList())));
        originalLottoTickets.add(new LottoTicket(IntStream.of(1, 2, 3, 4, 13, 14).mapToObj(LottoNumber::new).collect(Collectors.toList())));
        originalLottoTickets.add(new LottoTicket(IntStream.of(1, 2, 3, 4, 5, 14).mapToObj(LottoNumber::new).collect(Collectors.toList())));
        originalLottoTickets.add(new LottoTicket(IntStream.of(1, 2, 3, 4, 5, 7).mapToObj(LottoNumber::new).collect(Collectors.toList())));
        originalLottoTickets.add(new LottoTicket(IntStream.of(1, 2, 3, 4, 5, 6).mapToObj(LottoNumber::new).collect(Collectors.toList())));
    }

    @DisplayName("LottoResults 의 계산 결과값 확인")
    @ParameterizedTest
    @CsvSource(value = {"MATCH_THREE,1", "MATCH_FOUR,1", "MATCH_FIVE,1", "MATCH_FIVE_WITH_BONUS,1", "MATCH_SIX,1"})
    void getLottoResultsTest(RankType rankType, int expected) {
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6", "7");
        LottoTickets lottoTickets = new LottoTickets(originalLottoTickets);
        LottoResults lottoResults = new LottoResults(lottoTickets, winningLottoTicket);
        Map<RankType, Integer> lottoRankResults = lottoResults.getLottoResults();

        Assertions.assertThat(lottoRankResults.get(rankType)).isEqualTo(expected);
    }
}

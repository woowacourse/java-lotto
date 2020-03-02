package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LottoManagerTest {
    private List<LottoTicket> originalLottoTickets = new ArrayList<>();

    @BeforeEach
    void setUp() {
        originalLottoTickets.add(new LottoTicket(IntStream.of(1, 2, 3, 12, 13, 14).mapToObj(LottoNumber::from).collect(Collectors.toList())));
        originalLottoTickets.add(new LottoTicket(IntStream.of(1, 2, 3, 4, 13, 14).mapToObj(LottoNumber::from).collect(Collectors.toList())));
        originalLottoTickets.add(new LottoTicket(IntStream.of(1, 2, 3, 4, 5, 14).mapToObj(LottoNumber::from).collect(Collectors.toList())));
        originalLottoTickets.add(new LottoTicket(IntStream.of(1, 2, 3, 4, 5, 7).mapToObj(LottoNumber::from).collect(Collectors.toList())));
        originalLottoTickets.add(new LottoTicket(IntStream.of(1, 2, 3, 4, 5, 6).mapToObj(LottoNumber::from).collect(Collectors.toList())));
    }

    @DisplayName("Should_매칭한 rankType 만큼 카운트 증가_When_로또티켓들과 당첨티켓을 매칭")
    @ParameterizedTest
    @CsvSource(value = {"MATCH_THREE,1", "MATCH_FOUR,1", "MATCH_FIVE,1", "MATCH_FIVE_WITH_BONUS,1", "MATCH_SIX,1"})
    void countWinningTickets(RankType rankType, int expected) {
        WinningLottoTicket winningLottoTicket = new WinningLottoTicket("1, 2, 3, 4, 5, 6", "7");
        LottoTickets lottoTickets = new LottoTickets(originalLottoTickets);
        Map<RankType, Integer> map = LottoManager.match(lottoTickets, winningLottoTicket).getLottoResults();

        Assertions.assertThat(map.get(rankType)).isEqualTo(expected);
    }

    private static Stream<Arguments> lottoTicketSetUp() {
        return Stream.of(
                Arguments.of(Arrays.asList("1, 2, 3, 4, 5, 6", "1, 2, 3, 4, 5, 6"), new AutoCount(4), 6),
                Arguments.of(Arrays.asList("1, 2, 3, 4, 5, 6", "1, 2, 3, 4, 5, 6", "1, 2, 3, 4, 5, 6"), new AutoCount(7), 10),
                Arguments.of(Arrays.asList("1, 2, 3, 4, 5, 6", "1, 2, 3, 4, 5, 6", "1, 2, 3, 4, 5, 6"), new AutoCount(9), 12)
        );
    }

    @DisplayName("Should_자동과 수동 티켓 갯수의 총 합계만큼 생성한 것 카운트_When_수동 로또 티켓과 자동 횟수를 입력")
    @ParameterizedTest
    @MethodSource("lottoTicketSetUp")
    void generateLottoTicketsTest(List<String> manualLottoTickets, AutoCount autoCount, int expected) {
        LottoTickets lottoTickets = LottoManager.generateLottoTickets(manualLottoTickets, autoCount);
        Assertions.assertThat(lottoTickets.getLottoTickets().size()).isEqualTo(expected);
    }

}

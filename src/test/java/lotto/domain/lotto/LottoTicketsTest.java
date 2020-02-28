package lotto.domain.lotto;

import lotto.domain.result.LottoResult;
import lotto.domain.result.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {

    @Test
    @DisplayName("LottoTicket들로부터 LottoTickets 생성")
    void createLottoTickets() {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        Set<Integer> firstLotto = IntStream.rangeClosed(2, 7).boxed().collect(Collectors.toSet());
        Set<Integer> secondNumbers = IntStream.rangeClosed(1, 6).boxed().collect(Collectors.toSet());
        Set<Integer> thirdNumbers = IntStream.rangeClosed(3, 8).boxed().collect(Collectors.toSet());
        Set<Integer> fourthNumbers = IntStream.rangeClosed(4, 9).boxed().collect(Collectors.toSet());
        Set<Integer> fifthNumbers = IntStream.rangeClosed(5, 10).boxed().collect(Collectors.toSet());
        Set<Integer> missNumbers = IntStream.rangeClosed(6, 11).boxed().collect(Collectors.toSet());
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(firstLotto));
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(secondNumbers));
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(thirdNumbers));
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(fourthNumbers));
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(fifthNumbers));
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(missNumbers));
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);
    }

    @ParameterizedTest
    @MethodSource("getRankCount")
    @DisplayName("LottoTickets는 WinningLotto를 받아서 결과를 생성")
    void createLottoResultsWithWinningLotto(Rank rank, int count) {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        Set<Integer> firstLotto = IntStream.rangeClosed(2, 7).boxed().collect(Collectors.toSet());
        Set<Integer> secondNumbers = IntStream.rangeClosed(1, 6).boxed().collect(Collectors.toSet());
        Set<Integer> thirdNumbers = IntStream.rangeClosed(3, 8).boxed().collect(Collectors.toSet());
        Set<Integer> fourthNumbers = IntStream.rangeClosed(4, 9).boxed().collect(Collectors.toSet());
        Set<Integer> fifthNumbers = IntStream.rangeClosed(5, 10).boxed().collect(Collectors.toSet());
        Set<Integer> missNumbers = IntStream.rangeClosed(6, 11).boxed().collect(Collectors.toSet());
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(firstLotto));
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(secondNumbers));
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(thirdNumbers));
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(fourthNumbers));
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(fifthNumbers));
        lottoTicketList.add(LottoFactory.publishLottoTicketFrom(missNumbers));
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        LottoTicket winningLottoTicket = LottoFactory.publishLottoTicketFrom(IntStream.rangeClosed(2, 7).boxed().collect(Collectors.toSet()));
        LottoNumber bonusLottoNumber = LottoFactory.publishLottoNumberFrom(1);
        WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusLottoNumber);
        LottoResult lottoResult = lottoTickets.getLottoResults(winningLotto);

        assertThat(lottoResult.count(rank)).isEqualTo(count);
    }

    private static Stream<Arguments> getRankCount() {
        return Stream.of(
                Arguments.of(Rank.MISS, 1),
                Arguments.of(Rank.FIFTH, 1),
                Arguments.of(Rank.FOURTH, 1),
                Arguments.of(Rank.THIRD, 1),
                Arguments.of(Rank.SECOND, 1),
                Arguments.of(Rank.FIRST, 1)
        );
    }
}

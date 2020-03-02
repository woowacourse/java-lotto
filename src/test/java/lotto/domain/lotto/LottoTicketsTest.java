package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    private Set<Integer> firstLottoNumbers;
    private Set<Integer> secondLottoNumbers;
    private Set<Integer> thirdLottoNumbers;
    private Set<Integer> fourthLottoNumbers;
    private Set<Integer> fifthLottoNumbers;
    private Set<Integer> missLottoNumbers;

    @BeforeEach
    void setUp() {
        firstLottoNumbers = IntStream.of(1,2,3,4,5,6).boxed().collect(Collectors.toSet());
        secondLottoNumbers = IntStream.of(1,2,3,4,5,7).boxed().collect(Collectors.toSet());
        thirdLottoNumbers = IntStream.of(2,3,4,5,6,7).boxed().collect(Collectors.toSet());
        fourthLottoNumbers = IntStream.of(3,4,5,6,7,8).boxed().collect(Collectors.toSet());
        fifthLottoNumbers = IntStream.of(4,5,6,7,8,9).boxed().collect(Collectors.toSet());
        missLottoNumbers = IntStream.of(5,6,7,8,9,10).boxed().collect(Collectors.toSet());
    }

    @Test
    @DisplayName("LottoTicket들로부터 LottoTickets 생성")
    void createLottoTickets() {
        List<Set<Integer>> lottoTicketsNumbers = Collections.singletonList(firstLottoNumbers);
        assertThat(LottoTickets.publishLottoTickets(lottoTicketsNumbers)).isNotNull();
    }

    @Test
    @DisplayName("수동과 자동 개수에 맞게 생산되 LottoTickets를 합침")
    void createLottoTicketsBasedOnCount() {
        List<Set<Integer>> firstLottoTicketNumbers = Stream.of(firstLottoNumbers, secondLottoNumbers, thirdLottoNumbers)
                .collect(Collectors.toList());
        LottoTickets firstLottoTickets = LottoTickets.publishLottoTickets(firstLottoTicketNumbers);

        List<Set<Integer>> secondLottoTicketNumbers = Stream.of(fourthLottoNumbers, fifthLottoNumbers, missLottoNumbers)
                .collect(Collectors.toList());
        LottoTickets secondLottoTickets = LottoTickets.publishLottoTickets(secondLottoTicketNumbers);

        List<Set<Integer>> targetLottoTicketList = Stream.of(firstLottoNumbers, secondLottoNumbers, thirdLottoNumbers,
                        fourthLottoNumbers, fifthLottoNumbers, missLottoNumbers)
                .collect(Collectors.toList());
        LottoTickets targetLottoTickets = LottoTickets.publishLottoTickets(targetLottoTicketList);

        LottoTickets totalLottoTickets = LottoTickets.createFrom(firstLottoTickets, secondLottoTickets);

        assertThat(totalLottoTickets).isEqualTo(targetLottoTickets);
    }

    @Test
    @DisplayName("LottoTickets는 WinningLotto를 받아서 결과를 생성")
    void createLottoResultsWithWinningLotto() {
        List<Set<Integer>> lottoTicketNumbers = Stream.of(firstLottoNumbers, secondLottoNumbers, thirdLottoNumbers,
                        fourthLottoNumbers, fifthLottoNumbers, missLottoNumbers)
                .collect(Collectors.toList());
        LottoTickets lottoTickets = LottoTickets.publishLottoTickets(lottoTicketNumbers);

        WinningLotto winningLotto = new WinningLotto(firstLottoNumbers, 7);
        assertThat(lottoTickets.getLottoResults(winningLotto)).isNotNull();
    }
}

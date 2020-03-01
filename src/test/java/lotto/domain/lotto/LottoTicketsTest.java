package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    private Set<Integer> firstLottoTicketNumbers;
    private Set<Integer> secondLottoTicketNumbers;
    private Set<Integer> thirdLottoTicketNumbers;
    private Set<Integer> fourthLottoTicketNumbers;
    private Set<Integer> fifthLottoTicketNumbers;
    private Set<Integer> missLottoTicketNumbers;

    @BeforeEach
    void setUp() {
        firstLottoTicketNumbers = IntStream.of(1,2,3,4,5,6).boxed().collect(Collectors.toSet());
        secondLottoTicketNumbers = IntStream.of(1,2,3,4,5,7).boxed().collect(Collectors.toSet());
        thirdLottoTicketNumbers = IntStream.of(2,3,4,5,6,7).boxed().collect(Collectors.toSet());
        fourthLottoTicketNumbers = IntStream.of(3,4,5,6,7,8).boxed().collect(Collectors.toSet());
        fifthLottoTicketNumbers = IntStream.of(4,5,6,7,8,9).boxed().collect(Collectors.toSet());
        missLottoTicketNumbers = IntStream.of(5,6,7,8,9,10).boxed().collect(Collectors.toSet());
    }

    @Test
    @DisplayName("LottoTicket들로부터 LottoTickets 생성")
    void createLottoTickets() {
        List<Set<Integer>> lottoTicketsNumbers = Collections.singletonList(firstLottoTicketNumbers);
        assertThat(LottoTickets.publishLottoTickets(lottoTicketsNumbers)).isNotNull();
    }

    @Test
    @DisplayName("수동과 자동 개수에 맞게 생산되 LottoTickets를 합침")
    void createLottoTicketsBasedOnCount() {
        List<Set<Integer>> firstLottoTicketList = new ArrayList<>();
        firstLottoTicketList.add(firstLottoTicketNumbers);
        firstLottoTicketList.add(secondLottoTicketNumbers);
        firstLottoTicketList.add(thirdLottoTicketNumbers);
        LottoTickets firstLottoTickets = LottoTickets.publishLottoTickets(firstLottoTicketList);

        List<Set<Integer>> secondLottoTicketList = new ArrayList<>();
        secondLottoTicketList.add(fourthLottoTicketNumbers);
        secondLottoTicketList.add(fifthLottoTicketNumbers);
        secondLottoTicketList.add(missLottoTicketNumbers);
        LottoTickets secondLottoTickets = LottoTickets.publishLottoTickets(secondLottoTicketList);

        List<Set<Integer>> targetLottoTicketList = new ArrayList<>();
        targetLottoTicketList.add(firstLottoTicketNumbers);
        targetLottoTicketList.add(secondLottoTicketNumbers);
        targetLottoTicketList.add(thirdLottoTicketNumbers);
        targetLottoTicketList.add(fourthLottoTicketNumbers);
        targetLottoTicketList.add(fifthLottoTicketNumbers);
        targetLottoTicketList.add(missLottoTicketNumbers);
        LottoTickets targetLottoTickets = LottoTickets.publishLottoTickets(targetLottoTicketList);

        LottoTickets totalLottoTickets = LottoTickets.createFrom(firstLottoTickets, secondLottoTickets);

        assertThat(totalLottoTickets.getLottoTickets()).isEqualTo(targetLottoTickets.getLottoTickets());
    }

    @Test
    @DisplayName("LottoTickets는 WinningLotto를 받아서 결과를 생성")
    void createLottoResultsWithWinningLotto() {
        List<Set<Integer>> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(firstLottoTicketNumbers);
        lottoTicketList.add(secondLottoTicketNumbers);
        lottoTicketList.add(thirdLottoTicketNumbers);
        lottoTicketList.add(fourthLottoTicketNumbers);
        lottoTicketList.add(fifthLottoTicketNumbers);
        lottoTicketList.add(missLottoTicketNumbers);
        LottoTickets lottoTickets = LottoTickets.publishLottoTickets(lottoTicketList);

        WinningLotto winningLotto = new WinningLotto(firstLottoTicketNumbers, 7);
        assertThat(lottoTickets.getLottoResults(winningLotto)).isNotNull();
    }
}

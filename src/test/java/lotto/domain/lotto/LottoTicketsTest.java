package lotto.domain.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTicketsTest {

    private LottoNumber numberSeven;
    private LottoTicket firstLottoTicket;
    private LottoTicket secondLottoTicket;
    private LottoTicket thirdLottoTicket;
    private LottoTicket fourthLottoTicket;
    private LottoTicket fifthLottoTicket;
    private LottoTicket missLottoTicket;

    @BeforeEach
    void setUp() {
        LottoNumber numberOne = new LottoNumber(1);
        LottoNumber numberTwo = new LottoNumber(2);
        LottoNumber numberThree = new LottoNumber(3);
        LottoNumber numberFour = new LottoNumber(4);
        LottoNumber numberFive = new LottoNumber(5);
        LottoNumber numberSix = new LottoNumber(6);
        numberSeven = new LottoNumber(7);
        LottoNumber numberEight = new LottoNumber(8);
        LottoNumber numberNine = new LottoNumber(9);
        LottoNumber numberTen = new LottoNumber(10);
        Set<LottoNumber> firstLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberOne, numberTwo, numberThree, numberFour, numberFive, numberSix}).collect(Collectors.toSet());
        Set<LottoNumber> secondLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberOne, numberTwo, numberThree, numberFour, numberFive, numberSeven}).collect(Collectors.toSet());
        Set<LottoNumber> thirdLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberTwo, numberThree, numberFour, numberFive, numberSix, numberEight}).collect(Collectors.toSet());
        Set<LottoNumber> fourthLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberThree, numberFour, numberFive, numberSix, numberSeven, numberEight}).collect(Collectors.toSet());
        Set<LottoNumber> fifthLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberFour, numberFive, numberSix, numberSeven, numberEight, numberNine}).collect(Collectors.toSet());
        Set<LottoNumber> missLottoNumbers = Arrays.stream(
                new LottoNumber[] {numberFive, numberSix, numberSeven, numberEight, numberNine, numberTen}).collect(Collectors.toSet());
        firstLottoTicket = new LottoTicket(firstLottoNumbers);
        secondLottoTicket = new LottoTicket(secondLottoNumbers);
        thirdLottoTicket = new LottoTicket(thirdLottoNumbers);
        fourthLottoTicket = new LottoTicket(fourthLottoNumbers);
        fifthLottoTicket = new LottoTicket(fifthLottoNumbers);
        missLottoTicket = new LottoTicket(missLottoNumbers);
    }

    @Test
    @DisplayName("LottoTicket들로부터 LottoTickets 생성")
    void createLottoTickets() {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(firstLottoTicket);
        lottoTicketList.add(secondLottoTicket);
        lottoTicketList.add(thirdLottoTicket);
        lottoTicketList.add(fourthLottoTicket);
        lottoTicketList.add(fifthLottoTicket);
        lottoTicketList.add(missLottoTicket);
        assertThat(new LottoTickets(lottoTicketList)).isNotNull();
    }

    @Test
    @DisplayName("수동과 자동 개수에 맞게 생산되 LottoTickets를 합침")
    void createLottoTicketsBasedOnCount() {
        List<LottoTicket> firstLottoTicketList = new ArrayList<>();
        firstLottoTicketList.add(firstLottoTicket);
        firstLottoTicketList.add(secondLottoTicket);
        firstLottoTicketList.add(thirdLottoTicket);
        LottoTickets firstLottoTickets = new LottoTickets(firstLottoTicketList);

        List<LottoTicket> secondLottoTicketList = new ArrayList<>();
        secondLottoTicketList.add(fourthLottoTicket);
        secondLottoTicketList.add(fifthLottoTicket);
        secondLottoTicketList.add(missLottoTicket);
        LottoTickets secondLottoTickets = new LottoTickets(secondLottoTicketList);

        List<LottoTicket> targetLottoTicketList = new ArrayList<>();
        targetLottoTicketList.add(firstLottoTicket);
        targetLottoTicketList.add(secondLottoTicket);
        targetLottoTicketList.add(thirdLottoTicket);
        targetLottoTicketList.add(fourthLottoTicket);
        targetLottoTicketList.add(fifthLottoTicket);
        targetLottoTicketList.add(missLottoTicket);
        LottoTickets targetLottoTickets = new LottoTickets(targetLottoTicketList);

        LottoTickets totalLottoTickets = LottoTickets.createFrom(firstLottoTickets, secondLottoTickets);

        assertThat(totalLottoTickets.getLottoTickets()).isEqualTo(targetLottoTickets.getLottoTickets());
    }

    @Test
    @DisplayName("LottoTickets는 WinningLotto를 받아서 결과를 생성")
    void createLottoResultsWithWinningLotto() {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        lottoTicketList.add(firstLottoTicket);
        lottoTicketList.add(secondLottoTicket);
        lottoTicketList.add(thirdLottoTicket);
        lottoTicketList.add(fourthLottoTicket);
        lottoTicketList.add(fifthLottoTicket);
        lottoTicketList.add(missLottoTicket);
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        LottoTicket winningLottoTicket = firstLottoTicket;
        LottoNumber bonusLottoNumber = numberSeven;
        WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusLottoNumber);
        assertThat(lottoTickets.getLottoResults(winningLotto)).isNotNull();
    }
}

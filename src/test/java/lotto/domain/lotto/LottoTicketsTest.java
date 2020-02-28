package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.domain.result.LottoResult;

class LottoTicketsTest {
    private LottoNumber numberOne = new LottoNumber(1);
    private LottoNumber numberTwo = new LottoNumber(2);
    private LottoNumber numberThree = new LottoNumber(3);
    private LottoNumber numberFour = new LottoNumber(4);
    private LottoNumber numberFive = new LottoNumber(5);
    private LottoNumber numberSix = new LottoNumber(6);
    private LottoNumber numberSeven = new LottoNumber(7);
    private LottoNumber numberEight = new LottoNumber(8);
    private LottoNumber numberNine = new LottoNumber(9);
    private LottoNumber numberTen = new LottoNumber(10);
    private Set<LottoNumber> firstLottoNumbers = Arrays.stream(
            new LottoNumber[] {numberOne, numberTwo, numberThree, numberFour, numberFive, numberSix}).collect(Collectors.toSet());
    private Set<LottoNumber> secondLottoNumbers = Arrays.stream(
            new LottoNumber[] {numberOne, numberTwo, numberThree, numberFour, numberFive, numberSeven}).collect(Collectors.toSet());
    private Set<LottoNumber> thirdLottoNumbers = Arrays.stream(
            new LottoNumber[] {numberTwo, numberThree, numberFour, numberFive, numberSix, numberEight}).collect(Collectors.toSet());
    private Set<LottoNumber> fourthLottoNumbers = Arrays.stream(
            new LottoNumber[] {numberThree, numberFour, numberFive, numberSix, numberSeven, numberEight}).collect(Collectors.toSet());
    private Set<LottoNumber> fifthLottoNumbers = Arrays.stream(
            new LottoNumber[] {numberFour, numberFive, numberSix, numberSeven, numberEight, numberNine}).collect(Collectors.toSet());
    private Set<LottoNumber> missLottoNumbers = Arrays.stream(
            new LottoNumber[] {numberFive, numberSix, numberSeven, numberEight, numberNine, numberTen}).collect(Collectors.toSet());
    private LottoTicket firstLottoTicket = new LottoTicket(firstLottoNumbers);
    private LottoTicket secondLottoTicket = new LottoTicket(secondLottoNumbers);
    private LottoTicket thirdLottoTicket = new LottoTicket(thirdLottoNumbers);
    private LottoTicket fourthLottoTicket = new LottoTicket(fourthLottoNumbers);
    private LottoTicket fifthLottoTicket = new LottoTicket(fifthLottoNumbers);
    private LottoTicket missLottoTicket = new LottoTicket(missLottoNumbers);

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
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);
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
        LottoResult lottoResult = lottoTickets.getLottoResults(winningLotto);
    }
}

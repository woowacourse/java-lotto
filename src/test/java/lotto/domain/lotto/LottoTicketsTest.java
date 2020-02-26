package lotto.domain.lotto;

import lotto.domain.result.LottoResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class LottoTicketsTest {

    @Test
    @DisplayName("LottoTicket들로부터 LottoTickets 생성")
    void createLottoTickets() {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        Set<Integer> firstLotto = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).boxed().collect(Collectors.toSet());
        Set<Integer> secondNumbers = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).boxed().collect(Collectors.toSet());
        Set<Integer> thirdNumbers = Arrays.stream(new int[]{2, 3, 4, 5, 6, 8}).boxed().collect(Collectors.toSet());
        Set<Integer> fourthNumbers = Arrays.stream(new int[]{3, 4, 5, 6, 7, 8}).boxed().collect(Collectors.toSet());
        Set<Integer> fifthNumbers = Arrays.stream(new int[]{4, 5, 6, 7, 8, 9}).boxed().collect(Collectors.toSet());
        Set<Integer> missNumbers = Arrays.stream(new int[]{5, 6, 7, 8, 9, 10}).boxed().collect(Collectors.toSet());
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(firstLotto));
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(secondNumbers));
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(thirdNumbers));
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(fourthNumbers));
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(fifthNumbers));
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(missNumbers));
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);
    }

    @Test
    @DisplayName("LottoTickets는 WinningLotto를 받아서 결과를 생성")
    void createLottoResultsWithWinningLotto() {
        List<LottoTicket> lottoTicketList = new ArrayList<>();
        Set<Integer> firstLotto = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).boxed().collect(Collectors.toSet());
        Set<Integer> secondNumbers = Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).boxed().collect(Collectors.toSet());
        Set<Integer> thirdNumbers = Arrays.stream(new int[]{2, 3, 4, 5, 6, 8}).boxed().collect(Collectors.toSet());
        Set<Integer> fourthNumbers = Arrays.stream(new int[]{3, 4, 5, 6, 7, 8}).boxed().collect(Collectors.toSet());
        Set<Integer> fifthNumbers = Arrays.stream(new int[]{4, 5, 6, 7, 8, 9}).boxed().collect(Collectors.toSet());
        Set<Integer> missNumbers = Arrays.stream(new int[]{5, 6, 7, 8, 9, 10}).boxed().collect(Collectors.toSet());
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(firstLotto));
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(secondNumbers));
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(thirdNumbers));
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(fourthNumbers));
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(fifthNumbers));
        lottoTicketList.add(LottoTicketFactory.publishLottoTicketFrom(missNumbers));
        LottoTickets lottoTickets = new LottoTickets(lottoTicketList);

        LottoTicket winningLottoTicket = LottoTicketFactory.publishLottoTicketFrom(Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).boxed().collect(Collectors.toSet()));
        LottoNumber bonusLottoNumber = LottoTicketFactory.publishLottoNumberFrom(7);
        WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusLottoNumber);
        LottoResult lottoResult = lottoTickets.getLottoResults(winningLotto);
    }
}

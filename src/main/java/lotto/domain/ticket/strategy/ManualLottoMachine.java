package lotto.domain.ticket.strategy;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.ball.LottoNumberFactory;
import lotto.domain.ticket.manual.ManualNumber;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualLottoMachine implements LottoMachine {

    @Override
    public List<LottoTicket> buyTickets(int ticketCount, List<ManualNumber> numberBundle) {
        validateTicketAmount(ticketCount, numberBundle);
        return numberBundle.stream()
                .map(ManualNumber::getNumbers)
                .map(this::aLottoTicket)
                .collect(Collectors.toList());
    }

    private void validateTicketAmount(int ticketCount, List<ManualNumber> numberBundle) {
        int manualNumbersSize = numberBundle.size();
        if (ticketCount != manualNumbersSize) {
            throw new IllegalArgumentException(String.format("수동구매 티켓의 갯수(%d)와 입력받은 수동번호의 갯수(%d)가 다릅니다.", ticketCount, manualNumbersSize));
        }
    }

    private LottoTicket aLottoTicket(Set<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumberFactory::findLottoBallByNumber)
                .collect(Collectors.collectingAndThen(Collectors.toSet(), LottoTicket::new));
    }
}

package lotto.domain.ticket.strategy;

import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.manual.ManualNumber;
import lotto.domain.ticket.number.LottoNumberFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualLottoMachine implements LottoMachine {

    private final List<ManualNumber> manualNumbers;

    public ManualLottoMachine(int ticketCount, List<ManualNumber> manualNumbers) {
        this.manualNumbers = manualNumbers;
        validateTicketAmount(ticketCount, manualNumbers);
    }

    @Override
    public List<LottoTicket> buyTickets() {
        return manualNumbers.stream()
                .map(ManualNumber::getNumbers)
                .map(this::makeLottoTicket)
                .collect(Collectors.toList());
    }

    private void validateTicketAmount(int ticketCount, List<ManualNumber> numberBundle) {
        int manualNumbersSize = numberBundle.size();
        if (ticketCount != manualNumbersSize) {
            throw new IllegalArgumentException(String.format("수동구매 티켓의 갯수(%d)와 입력받은 수동번호의 갯수(%d)가 다릅니다.", ticketCount, manualNumbersSize));
        }
    }

    private LottoTicket makeLottoTicket(Set<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumberFactory::findLottoBallByNumber)
                .collect(Collectors.collectingAndThen(Collectors.toSet(), LottoTicket::new));
    }
}

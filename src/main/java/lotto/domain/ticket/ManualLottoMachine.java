package lotto.domain.ticket;

import lotto.domain.customer.Customer;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.util.NullOrEmptyValidator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualLottoMachine implements LottoMachine {

    @Override
    public List<LottoTicket> buyTickets(Customer customer) {
        NullOrEmptyValidator.isNull(customer);

        int numberOfManualTickets = customer.getMoney().getNumberOfManualTickets();
        List<List<Integer>> manualNumbers = customer.getManualNumbers();

        NullOrEmptyValidator.isNullOrEmpty(manualNumbers);
        validateSize(numberOfManualTickets, manualNumbers.size());

        return manualNumbers.stream()
                .map(this::createOneTicket)
                .collect(Collectors.toList());
    }

    private void validateSize(int numberOfTickets, int sizeOfManualNumbers) {
        if (numberOfTickets != sizeOfManualNumbers) {
            throw new IllegalArgumentException(MESSAGE_FOR_UNMATCHED_SIZE);
        }
    }

    private LottoTicket createOneTicket(List<Integer> manualNumbers) {
        NullOrEmptyValidator.isNullOrEmpty(manualNumbers);

        Set<LottoBall> manualBalls = manualNumbers.stream()
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());

        return new LottoTicket(manualBalls);
    }
}

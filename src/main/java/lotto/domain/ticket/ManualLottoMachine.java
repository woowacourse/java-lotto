package lotto.domain.ticket;

import lotto.domain.customer.Customer;
import lotto.domain.customer.PurchaseInfo;
import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.util.NullOrEmptyValidator;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static lotto.domain.customer.Customer.NO_MANUAL_TICKET;

public class ManualLottoMachine implements LottoMachine {

    @Override
    public List<LottoTicket> buyTickets(Customer customer) {
        NullOrEmptyValidator.isNull(customer);

        PurchaseInfo purchaseInfo = customer.getPurchaseInfo();
        if (purchaseInfo.getNumberOfManualTickets() == NO_MANUAL_TICKET) {
            return Collections.emptyList();
        }

        List<List<Integer>> manualNumbers = customer.getManualNumbers();
        return manualNumbers.stream()
                .map(this::createOneTicket)
                .collect(Collectors.toList());
    }

    private LottoTicket createOneTicket(List<Integer> manualNumbers) {
        Set<LottoBall> manualBalls = manualNumbers.stream()
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());

        return new LottoTicket(manualBalls);
    }
}

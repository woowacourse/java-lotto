package lotto.domain.ticket;

import lotto.domain.ticket.ball.LottoBall;
import lotto.domain.ticket.ball.LottoBallFactory;
import lotto.util.NullOrEmptyValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ManualLottoMachine extends LottoMachine {
    private final List<List<Integer>> manualNumbers;

    public ManualLottoMachine(List<List<Integer>> manualNumbers) {
        this.manualNumbers = manualNumbers;
    }

    @Override
    public List<LottoTicket> buyTickets(int numberOfTickets) {
        NullOrEmptyValidator.isNullOrEmpty(manualNumbers);
        validateSize(numberOfTickets, manualNumbers.size());

        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < numberOfTickets; i++) {
            lottoTickets.add(createOneTicket());
        }

        return lottoTickets;
    }

    private void validateSize(int numberOfTickets, int sizeOfManualNumbers) {
        if (numberOfTickets != sizeOfManualNumbers) {
            throw new IllegalArgumentException(MESSAGE_FOR_UNMATCHED_SIZE);
        }
    }

    @Override
    public LottoTicket createOneTicket() {
        Set<LottoBall> manualBalls = manualNumbers.remove(0).stream()
                .map(LottoBallFactory::getLottoBallByNumber)
                .collect(Collectors.toSet());

        return new LottoTicket(manualBalls);
    }
}

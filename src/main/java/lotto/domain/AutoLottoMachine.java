package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoLottoMachine implements LottoMachine {
    @Override
    public List<LottoTicket> createTickets(int numberOfTickets) {
        return IntStream.range(0, numberOfTickets)
            .mapToObj(i -> new LottoTicket())
            .collect(Collectors.toList());
    }
}

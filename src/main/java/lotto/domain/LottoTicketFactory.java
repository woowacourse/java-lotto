package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.vo.PurchaseAmount;

public enum LottoTicketFactory {

    INSTANCE;

    private final List<LottoNumber> availableLottoNumbers = createLottoNumbers();

    private List<LottoNumber> createLottoNumbers() {
        return IntStream.rangeClosed(1, 45)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public List<LottoTicket> createTickets(PurchaseAmount money) {
        int availableTicketNumber = money.getAvailableTicketNumber();
        return IntStream.range(0, availableTicketNumber)
                .mapToObj(i -> createTicketShuffled())
                .collect(Collectors.toUnmodifiableList());
    }

    private LottoTicket createTicketShuffled() {
        Collections.shuffle(availableLottoNumbers);
        return new LottoTicket(new HashSet<>(availableLottoNumbers.subList(0, 6)));
    }
}

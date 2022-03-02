package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoTicket;
import lotto.domain.vo.PurchaseAmount;
import org.jetbrains.annotations.NotNull;

public enum LottoTicketFactory {

    INSTANCE;

    private final List<LottoNumber> availableLottoNumbers = createLottoNumbers();

    private List<LottoNumber> createLottoNumbers() {
        return IntStream.rangeClosed(1, 45)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public List<LottoTicket> createTickets(PurchaseAmount money, List<String> manualTicketNumbers) {

        List<LottoTicket> manualTickets = getManualTickets(manualTicketNumbers);
        int availableTicketCanBuy = money.availableTicketCanBuy();
        List<LottoTicket> autoTickets = getAutoTickets(manualTicketNumbers, availableTicketCanBuy);

        List<LottoTicket> resultTickets = new ArrayList<>();
        resultTickets.addAll(manualTickets);
        resultTickets.addAll(autoTickets);

        return Collections.unmodifiableList(resultTickets);
    }

    private List<LottoTicket> getManualTickets(List<String> manualTicketNumbers) {
        return manualTicketNumbers.stream()
                .map(LottoTicket::new)
                .collect(Collectors.toList());
    }

    private List<LottoTicket> getAutoTickets(List<String> manualTicketNumbers, int availableTicketCanBuy) {
        return IntStream.range(0, availableTicketCanBuy - manualTicketNumbers.size())
                .mapToObj(lottoNumber -> createTicketShuffled())
                .collect(Collectors.toList());
    }

    private LottoTicket createTicketShuffled() {
        Collections.shuffle(availableLottoNumbers);
        return new LottoTicket(new HashSet<>(availableLottoNumbers.subList(0, 6)));
    }
}

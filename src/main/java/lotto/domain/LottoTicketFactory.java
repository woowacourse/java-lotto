package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoTicket;

public enum LottoTicketFactory {

    INSTANCE;

    private static final List<LottoNumber> LOTTO_NUMBER_CACHE = createLottoNumbers();

    private static List<LottoNumber> createLottoNumbers() {
        return IntStream.rangeClosed(1, 45)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public List<LottoTicket> createTickets(TicketPurchaseDecider ticketPurchaseDecider, List<String> manualTicketNumbers) {

        List<LottoTicket> manualTickets = getManualTickets(manualTicketNumbers);
        int autoTicketCount = ticketPurchaseDecider.getAutoTicketCount();
        List<LottoTicket> autoTickets = getAutoTickets(autoTicketCount);

        List<LottoTicket> resultTickets = new LinkedList<>(manualTickets);
        resultTickets.addAll(autoTickets);

        return Collections.unmodifiableList(resultTickets);
    }

    private List<LottoTicket> getManualTickets(List<String> manualTicketNumbers) {
        return manualTicketNumbers.stream()
                .map(LottoTicket::new)
                .collect(Collectors.toList());
    }

    private List<LottoTicket> getAutoTickets(int autoTicketCount) {
        return IntStream.range(0, autoTicketCount)
                .mapToObj(lottoNumber -> createTicketShuffled())
                .collect(Collectors.toList());
    }

    private LottoTicket createTicketShuffled() {
        Collections.shuffle(LOTTO_NUMBER_CACHE);
        return new LottoTicket(new HashSet<>(LOTTO_NUMBER_CACHE.subList(0, 6)));
    }
}

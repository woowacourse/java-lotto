package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {

    private final static int LOTTO_TICKET_PRICE = 1000;
    private final static int MIN_LOTTO_NUMBER = 1;
    private final static int MAX_LOTTO_NUMBER = 45;

    private final Price price;

    public LottoMachine(final Price price) {
        this.price = price;
    }

    public static LottoMachine valueOf(final Price price) {
        return new LottoMachine(price);
    }

    public List<LottoTicket> generateLottoTickets() {
        final List<LottoTicket> lottoTickets = new ArrayList<>();
        final int lottoTicketQuantity = getLottoTicketQuantity(price);
        for (int i = 0; i < lottoTicketQuantity; i++) {
            lottoTickets.add(generateLottoTicket());
        }
        return lottoTickets;
    }

    private int getLottoTicketQuantity(final Price price) {
        return price.getValue() / LOTTO_TICKET_PRICE;
    }

    private LottoTicket generateLottoTicket() {
        return LottoTicket.valueOf(generateLottoNumbers());
    }

    private List<LottoNumber> generateLottoNumbers() {
        final List<LottoNumber> lottoNumbers = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::valueOf)
                .collect(Collectors.toList());

        Collections.shuffle(lottoNumbers);

        return lottoNumbers.stream()
                .limit(LottoTicket.getLottoTicketSize())
                .collect(Collectors.toList());
    }
}

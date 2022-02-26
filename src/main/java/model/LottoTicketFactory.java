package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketFactory {
    private static final LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();

    private static final int LOTTO_PRICE = 1000;

    private final List<LottoNumber> availableLottoNumbers;

    private LottoTicketFactory() {
        availableLottoNumbers = createLottoNumbers();
    }

    public static LottoTicketFactory getInstance() {
        return lottoTicketFactory;
    }

    private List<LottoNumber> createLottoNumbers() {
        return IntStream.rangeClosed(1, 45)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public List<LottoTicket> createTickets(int money) {
        return IntStream.range(0, getAvailableLottoTicketsCount(money))
                .mapToObj(i -> createTicket())
                .collect(Collectors.toUnmodifiableList());
    }

    private int getAvailableLottoTicketsCount(int money) {
        return money / LOTTO_PRICE;
    }

    private LottoTicket createTicket() {
        Collections.shuffle(availableLottoNumbers);
        return new LottoTicket(new ArrayList<>(availableLottoNumbers.subList(0, 6)));
    }
}

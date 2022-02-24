package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        final List<LottoNumber> availableLottoNumbers;
        availableLottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            availableLottoNumbers.add(new LottoNumber(String.valueOf(i)));
        }
        return availableLottoNumbers;
    }

    public List<LottoTicket> createTickets(int money) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < getAvailableLottoTicketsCount(money); i++) {
            lottoTickets.add(createTicket());
        }
        return Collections.unmodifiableList(lottoTickets);
    }

    private int getAvailableLottoTicketsCount(int money) {
        return money / LOTTO_PRICE;
    }

    private LottoTicket createTicket() {
        Collections.shuffle(availableLottoNumbers);
        return new LottoTicket(new ArrayList<>(availableLottoNumbers.subList(0, 6)));
    }
}

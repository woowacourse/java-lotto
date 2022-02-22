package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketFactory {
    private static final LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();

    static final String NOT_ENOUGH_MONEY = "[ERROR] 로또를 구매하려면 최소 천원 이상 투입해야 합니다.";
    private static final int LOTTO_PRICE = 1000;

    private final List<LottoNumber> availableLottoNumbers;

    private LottoTicketFactory() {
        availableLottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            availableLottoNumbers.add(new LottoNumber(String.valueOf(i)));
        }
    }

    public static LottoTicketFactory getInstance() {
        return lottoTicketFactory;
    }

    public List<LottoTicket> createTickets(int money) {
        validateMoney(money);
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < getAvailableLottoTicketsCount(money); i++) {
            Collections.shuffle(availableLottoNumbers);
            lottoTickets.add(createTicket());
        }
        return lottoTickets.stream().collect(Collectors.toUnmodifiableList());
    }

    private void validateMoney(int money) {
        if (money < LOTTO_PRICE) {
            throw new IllegalArgumentException(NOT_ENOUGH_MONEY);
        }
    }

    private int getAvailableLottoTicketsCount(int money) {
        return money / LOTTO_PRICE;
    }

    private LottoTicket createTicket() {
        Collections.shuffle(availableLottoNumbers);
        return new LottoTicket(availableLottoNumbers.subList(0, 6));
    }
}

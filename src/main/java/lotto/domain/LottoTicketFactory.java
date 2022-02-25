package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.lottonumbers.LottoTicket;

public class LottoTicketFactory {
    private static final int LOTTO_PRICE = 1000;

    private static final List<LottoNumber> availableLottoNumbers;

    static {
        availableLottoNumbers = createLottoNumbers();
    }

    private LottoTicketFactory() {
        throw new IllegalStateException("[ERROR] LottoTicketFactory 는 유틸 클래스이므로 인스턴스 생성을 불허 합니다.");
    }

    private static List<LottoNumber> createLottoNumbers() {
        return IntStream.rangeClosed(1, 45)
                .mapToObj(i -> new LottoNumber(String.valueOf(i)))
                .collect(Collectors.toList());
    }

    public static List<LottoTicket> createTickets(int money) {
        int ticketNumbers = getAvailableLottoTicketsCount(money);
        return IntStream.range(0, ticketNumbers)
                .mapToObj(i -> createTicketShuffled())
                .collect(Collectors.toUnmodifiableList());
    }

    private static int getAvailableLottoTicketsCount(int money) {
        return money / LOTTO_PRICE;
    }

    private static LottoTicket createTicketShuffled() {
        Collections.shuffle(availableLottoNumbers);
        return new LottoTicket(new ArrayList<>(availableLottoNumbers.subList(0, 6)));
    }
}

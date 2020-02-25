package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class LottoTicketFactory {
    private static final List<LottoNumber> numbers;
    private static final Money TICKET_PRICE = Money.of(1_000);
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;
    private static final int SUBLIST_FROM_INDEX = 0;
    private static final int SUBLIST_TO_INDEX = 6;

    static {
        numbers = IntStream.rangeClosed(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
                .mapToObj(LottoNumber::new)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public static List<LottoTicket> createLottoTickets(Money purchaseMoney) {
        int lottoCount = purchaseMoney.calculateQuotient(TICKET_PRICE);

        return Stream.generate(LottoTicketFactory::createLottoTicket).limit(lottoCount)
                .collect(toList());
    }

    private static LottoTicket createLottoTicket() {
        List<LottoNumber> copiedNumbers = new ArrayList<>(numbers);
        Collections.shuffle(copiedNumbers);
        List<LottoNumber> subNumbers = copiedNumbers.subList(SUBLIST_FROM_INDEX, SUBLIST_TO_INDEX);
        return new LottoTicket(subNumbers);
    }
}

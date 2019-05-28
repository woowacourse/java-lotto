package lotto.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoTicketsGenerator {
    private static final int COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET = 6;
    private static final int START = 0;
    private static final int MIN_RANDOM_NUMBER = 1;
    private static final int MAX_RANDOM_NUMBER = 45;
    private static final Random RANDOM = new Random();

    public static LottoTickets generateLottoTickets(int purchaseQuantity) {
        List<LottoTicket> lottoTickets = IntStream.range(START, purchaseQuantity)
                .mapToObj(i -> generateRandomLottoTicket())
                .collect(Collectors.toList());
        return new LottoTickets(lottoTickets);
    }

    private static LottoTicket generateRandomLottoTicket() {
        TreeSet<LottoNumber> lottoTicket = new TreeSet<>();
        while (lottoTicket.size() < COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET) {
            lottoTicket.add(new LottoNumber(generateRandomNumber()));
        }

        return new LottoTicket(new ArrayList<>(lottoTicket));
    }

    private static int generateRandomNumber() {
        return RANDOM.nextInt(MAX_RANDOM_NUMBER) + MIN_RANDOM_NUMBER;
    }
}
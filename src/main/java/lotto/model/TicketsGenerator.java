package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TicketsGenerator {

    private final static Random RANDOM = new Random();

    public static List<Ticket> createAutoTickets(int autoNumbers) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < autoNumbers; i++) {
            tickets.add(new Ticket(createAutoTicket()));
        }
        return tickets;
    }

    private static List<LottoNumber> createAutoTicket() {
        return RANDOM.ints(LottoNumber.MINIMUM_NUMBER, LottoNumber.MAXIMUM_NUMBER + 1)
            .distinct()
            .limit(WinNumbers.LOTTO_NUMBER_LENGTH)
            .sorted()
            .mapToObj(LottoNumber::getLottoNumber)
            .collect(Collectors.toList());
    }
}
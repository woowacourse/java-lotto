package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicketsGenerator {

    public static List<Ticket> createAutoTickets(int autoNumbers) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < autoNumbers; i++) {
            tickets.add(new Ticket(createAutoTicket()));
        }
        return tickets;
    }

    private static List<LottoNumber> createAutoTicket() {
        List<Integer> lottoNumbers = IntStream
            .range(LottoNumber.MINIMUM_NUMBER, LottoNumber.MAXIMUM_NUMBER + 1)
            .boxed()
            .collect(Collectors.toList());
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.stream()
            .limit(Ticket.LOTTO_NUMBER_LENGTH)
            .sorted()
            .map(LottoNumber::getLottoNumber)
            .collect(Collectors.toList());
    }
}
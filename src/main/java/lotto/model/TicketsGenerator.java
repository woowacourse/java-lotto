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
        List<Integer> lottoNumbers = IntStream.range(1, 46).boxed().collect(Collectors.toList());
        Collections.shuffle(lottoNumbers);
        return lottoNumbers.stream().limit(6).sorted().map(LottoNumber::getLottoNumber)
            .collect(Collectors.toList());
    }
}
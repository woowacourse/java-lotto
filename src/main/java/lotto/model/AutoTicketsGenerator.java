package lotto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AutoTicketsGenerator implements TicketsGenerator {

    @Override
    public List<Ticket> generate(int autoCount) {
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < autoCount; i++) {
            tickets.add(new Ticket(createAutoTicket()));
        }
        return tickets;
    }

    private List<LottoNumber> createAutoTicket() {
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
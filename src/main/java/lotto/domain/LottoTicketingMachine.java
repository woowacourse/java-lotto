package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketingMachine {
    public static LottoTickets generateLottoTickets(long numOfTickets) {
        List<Integer> candidates = initCandidates();

        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < numOfTickets; i++) {
            Collections.shuffle(candidates);
            LottoTicket lottoTicket = generateLottoTicket(candidates.subList(0, LottoTicket.SIZE_OF_LOTTO));
            tickets.add(lottoTicket);
        }
        return new LottoTickets(tickets);
    }

    private static List<Integer> initCandidates() {
        List<Integer> candidates = new ArrayList<>();
        for (int number = LottoNumber.FIRST_NUMBER; number <= LottoNumber.LAST_NUMBER; number++) {
            candidates.add(number);
        }
        return candidates;
    }

    private static LottoTicket generateLottoTicket(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new LottoTicket(lottoNumbers);
    }
}

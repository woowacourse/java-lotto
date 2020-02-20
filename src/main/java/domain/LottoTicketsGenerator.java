package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketsGenerator {
    private LottoTicketsGenerator() {
    }

    public static List<LottoTicket> generateLottoTickets(int number) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        List<Integer> lottoNumbers = new LottoNumbers().getLottoNumbers();
        for (int i = 0; i < number; i++) {
            Collections.shuffle(lottoNumbers);
            lottoTickets.add(new LottoTicket(lottoNumbers.stream()
                    .limit(6)
                    .collect(Collectors.toList())));
        }
        return lottoTickets;
    }
}

package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbersGenerator {
    private static List<Integer> lottoNumbers;

    static {
        lottoNumbers = IntStream.rangeClosed(1, 45)
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<LottoTicket> generateLottoTickets(int number) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Collections.shuffle(lottoNumbers);
            lottoTickets.add(new LottoTicket(lottoNumbers.stream()
                    .limit(6)
                    .collect(Collectors.toList())));
        }
        return lottoTickets;
    }
}

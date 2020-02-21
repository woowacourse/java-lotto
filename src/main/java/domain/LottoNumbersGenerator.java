package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumbersGenerator {
    private static final int START_LOTTO_RANGE = 1;
    private static final int END_LOTTO_RANGE = 45;
    private static final int START_LOTTO_INDEX = 0;
    private static final int END_LOTTO_INDEX = 6;

    private static List<Integer> lottoNumbers;

    static {
        lottoNumbers = IntStream.rangeClosed(START_LOTTO_RANGE, END_LOTTO_RANGE)
                .boxed()
                .collect(Collectors.toList());
    }

    public static List<LottoTicket> generateLottoTickets(int number) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = START_LOTTO_INDEX; i < number; i++) {
            Collections.shuffle(lottoNumbers);
            lottoTickets.add(new LottoTicket(lottoNumbers.stream()
                    .limit(END_LOTTO_INDEX)
                    .collect(Collectors.toList())));
        }
        return lottoTickets;
    }
}

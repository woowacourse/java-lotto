package lotto.domain;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    public final static int LOTTO_PRICE = 1000;

    private static final int START_INCLUSIVE = 1;
    private static final int END_INCLUSIVE = 45;
    private static final List<Integer> LOTTO_TOTAL_NUMBERS = IntStream.rangeClosed(START_INCLUSIVE, END_INCLUSIVE)
            .boxed()
            .collect(toList());

    public LottoTickets issueManual(List<List<Integer>> manualNumbers) {
        return new LottoTickets(manualNumbers);
    }

    public LottoTickets issueAuto(int lottoCount) {
        List<List<Integer>> numbers = IntStream.range(0, lottoCount)
                .mapToObj(noneUsed -> generateRandomNumbers(6))
                .collect(toList());

        return new LottoTickets(numbers);
    }

    private List<Integer> generateRandomNumbers(int size) {
        List<Integer> lottoTotalNumbers = new ArrayList<>(LOTTO_TOTAL_NUMBERS);
        Collections.shuffle(lottoTotalNumbers);

        return lottoTotalNumbers.stream()
                .limit(size)
                .collect(toList());
    }
}

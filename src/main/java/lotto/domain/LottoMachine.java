package lotto.domain;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {

    public final static Money LOTTO_PRICE = new Money(1000);

    private static final int LOTTO_NUMBERS_SIZE = 6;
    private static final int START_INCLUSIVE = 1;
    private static final int END_INCLUSIVE = 45;

    private static final List<LottoNumber> LOTTO_TOTAL_NUMBERS = IntStream.rangeClosed(START_INCLUSIVE, END_INCLUSIVE)
            .mapToObj(LottoNumber::of)
            .collect(toList());

    public LottoTickets issueManual(List<List<Integer>> numbers) {
        List<List<LottoNumber>> lottoNumbers = numbers.stream()
                .map(this::parseLottoNumbers)
                .collect(toList());

        return new LottoTickets(lottoNumbers);
    }

    private List<LottoNumber> parseLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::of)
                .collect(toList());
    }

    public LottoTickets issueAuto(int lottoCount) {
        List<List<LottoNumber>> numbers = IntStream.range(0, lottoCount)
                .mapToObj(noneUsed -> generateRandomLottoNumbers(LOTTO_NUMBERS_SIZE))
                .collect(toList());

        return new LottoTickets(numbers);
    }

    private List<LottoNumber> generateRandomLottoNumbers(int size) {
        List<LottoNumber> lottoTotalNumbers = new ArrayList<>(LOTTO_TOTAL_NUMBERS);
        Collections.shuffle(lottoTotalNumbers);

        return lottoTotalNumbers.stream()
                .limit(size)
                .collect(toList());
    }
}

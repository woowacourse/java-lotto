package lotto.domain.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lotto.domain.number.LottoNumber;

public class LottoGenerator {

    private static final LottoGenerator LOTTO_GENERATOR = new LottoGenerator();
    private static final int LOTTO_NUMBER_MINIMUM = 1;
    private static final int LOTTO_NUMBER_MAXIMUM = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    private LottoGenerator() {
        lottoNumbers = IntStream
            .rangeClosed(LOTTO_NUMBER_MINIMUM, LOTTO_NUMBER_MAXIMUM)
            .mapToObj(LottoNumber::valueOf)
            .collect(toList());
    }

    public static LottoGenerator getInstance() {
        return LOTTO_GENERATOR;
    }

    public LottoTicket newLottoTicket(int count) {
        return Stream.generate(this::generateRandomLottoNumber)
            .limit(count)
            .map(LottoNumbers::new)
            .collect(collectingAndThen(toList(), LottoTicket::new));
    }

    private List<LottoNumber> generateRandomLottoNumber() {
        Collections.shuffle(lottoNumbers);

        return new ArrayList<>(lottoNumbers)
            .stream()
            .limit(LOTTO_NUMBER_COUNT)
            .sorted(Comparator.comparingInt(LottoNumber::toInt))
            .collect(toList());
    }
}

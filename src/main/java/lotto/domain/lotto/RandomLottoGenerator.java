package lotto.domain.lotto;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.Payout;

public class RandomLottoGenerator {

    private static final RandomLottoGenerator RANDOM_LOTTO_GENERATOR = new RandomLottoGenerator();

    private final List<LottoNumber> lottoNumbers;

    private RandomLottoGenerator() {
        lottoNumbers = LottoNumber.getAllLottoNumbers();
    }

    public static RandomLottoGenerator getInstance() {
        return RANDOM_LOTTO_GENERATOR;
    }

    public LottoTicket buyLottoTicket(Payout payout) {
        return Stream.generate(this::nextLottoNumbers)
            .limit(payout.getNumberOfStuff(LottoTicket.getLottoPrice()))
            .collect(collectingAndThen(toList(), LottoTicket::new));
    }

    private LottoNumbers nextLottoNumbers() {
        Collections.shuffle(lottoNumbers);

        return new LottoNumbers(lottoNumbers.stream()
            .limit(LottoNumbers.getLottoNumberCount())
            .collect(Collectors.toSet()));
    }
}

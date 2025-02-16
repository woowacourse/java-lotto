package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.utils.NumberGenerator;
import lotto.utils.Parser;
import lotto.utils.Splitter;

public class LottoGenerator {

    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    public LottoBundle makeLottoBundle(int lottoQuantity) {
        return new LottoBundle(Stream.generate(this::makeLotto)
                .limit(lottoQuantity)
                .collect(Collectors.toList()));
    }


    private Lotto makeLotto() {
        return new Lotto(
                NumberGenerator.numberGeneratorWithUniqueValues(LOTTO_SIZE, LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER).stream()
                        .map(LottoNumber::of)
                        .collect(Collectors.toSet())
        );
    }

    public WinningNumbers makeWinningNumbers(String winningNumber, String bonusNumber) {

        List<Integer> lottoNumbers = Parser.parseToIntegers(Splitter.splitByComma(winningNumber));

        List<LottoNumber> lotto = lottoNumbers.stream()
                .map(LottoNumber::new)
                .toList();
        return new WinningNumbers(new Lotto(new HashSet<>(lotto)), new LottoNumber(Parser.parseToInteger(bonusNumber)));
    }
}

package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.utils.NumberGenerator;
import lotto.utils.Parser;
import lotto.utils.Splitter;

public class LottoGenerator {

    public LottoBundle makeLottoBundle(AmountPaid amount, int lottoQuantity) {
        return new LottoBundle(Stream.generate(this::makeLotto)
                .limit(lottoQuantity)
                .collect(Collectors.toList()));
    }


    public Lotto makeLotto() {
        return new Lotto(
                NumberGenerator.numberGeneratorWithUniqueValues(6, 1, 45).stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );
    }

    public WinningNumbers makeWinningNumbers(String winningNumber, String bonusNumber) {

        List<Integer> lottoNumbers = Parser.parseToIntegers(Splitter.splitByComma(winningNumber));
        List<LottoNumber> lotto = new ArrayList<>();
        for (int lottoNumber : lottoNumbers) {
            lotto.add(new LottoNumber(lottoNumber));
        }
        return new WinningNumbers(new Lotto(lotto), new LottoNumber(Parser.parseToInteger(bonusNumber)));
    }
}

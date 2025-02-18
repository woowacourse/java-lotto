package lotto.domain;

import java.util.List;
import java.util.stream.Stream;

public class LottoMachine {
    private LottoMachine() {}

    public static List<Lotto> issueLottos(final int lottoAmount) {
        return Stream.generate(NumbersGenerator::generateLottoNumbers)
                .map(Lotto::new)
                .limit(lottoAmount)
                .toList();
    }
}

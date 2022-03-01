package domain;

import domain.generator.LottoGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
    public static List<Lotto> generateLotto(final int number, LottoGenerator lottoGenerator) {
        final List<Lotto> issuedLotto = new ArrayList<>();

        Count count = new Count(number);
        while (!count.isEnd()) {
            count = count.decrease();
            issuedLotto.add(lottoGenerator.generate());
        }
        return Collections.unmodifiableList(issuedLotto);
    }
}

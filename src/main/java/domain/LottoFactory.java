package domain;

import domain.generator.LottoGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {

    public static List<Lotto> generateLotto(final int totalCount, final int manualCount,
                                            LottoGenerator lottoGenerator) {
        final List<Lotto> issuedLotto = new ArrayList<>();
        Count count = new Count(totalCount);
        while (!count.isEnd()) {
            count = count.decrease();
            issuedLotto.add(generateLotto(lottoGenerator));
        }
        return Collections.unmodifiableList(issuedLotto);
    }

    private static Lotto generateLotto(final LottoGenerator lottoGenerator) {
        return lottoGenerator.generate();
    }
}

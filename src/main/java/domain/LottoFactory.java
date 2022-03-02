package domain;

import domain.generator.AutoLottoGenerator;
import domain.generator.LottoGenerator;
import domain.generator.ManualLottoGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {

    public static Lotto generateLotto(final LottoGenerator lottoGenerator) {
        return lottoGenerator.generate();
    }

    public static List<Lotto> generateManualLottoGroup(final List<List<String>> issuedManualLottoInput) {
        final List<Lotto> issuedManualLotto = new ArrayList<>();
        for (List<String> manualLotto : issuedManualLottoInput) {
            issuedManualLotto.add(generateLotto(new ManualLottoGenerator(manualLotto)));
        }
        return Collections.unmodifiableList(issuedManualLotto);
    }

    public static List<Lotto> generateAutoLottoGroup(final int count) {
        final List<Lotto> issuedAutoLotto = AccumulateAutoLottoWithCount(new AutoLottoGenerator(), new Count(count));
        return Collections.unmodifiableList(issuedAutoLotto);
    }

    private static List<Lotto> AccumulateAutoLottoWithCount(final LottoGenerator lottoGenerator, Count count) {
        final List<Lotto> issuedLotto = new ArrayList<>();
        while (!count.isEnd()) {
            count = count.decrease();
            issuedLotto.add(generateLotto(lottoGenerator));
        }
        return issuedLotto;
    }

}


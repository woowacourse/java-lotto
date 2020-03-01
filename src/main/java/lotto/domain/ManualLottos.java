package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.util.NullValidator.validateNull;

public class ManualLottos implements LottosGenerator {

    public Lottos generate(LottoCount lottoCount) {
        validateNull(lottoCount);
        List<String[]> manualLottoLines = lottoCount.getManualLottos();

        List<Lotto> manualLottos = manualLottoLines.stream()
                .map(Lotto::from)
                .collect(Collectors.toList());
        return new Lottos(manualLottos);
    }
}

package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.util.NullValidator.validateNull;

public class ManualLottos implements LottosGenerator {
    private final List<String[]> manualLottoLines;

    public ManualLottos(List<String[]> manualLottoLines) {
        validateNull(manualLottoLines);
        this.manualLottoLines = manualLottoLines;
    }

    public Lottos generate() {
        List<Lotto> manualLottos = manualLottoLines.stream()
                .map(Lotto::from)
                .collect(Collectors.toList());
        return new Lottos(manualLottos);
    }
}

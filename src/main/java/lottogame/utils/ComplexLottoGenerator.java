package lottogame.utils;

import lottogame.domain.lotto.Lotto;

import java.util.List;

public class ComplexLottoGenerator implements LottoGenerator {
    private final ManualLottoGenerator manualLottoGenerator;
    private final AutoLottoGenerator autoLottoGenerator;

    public ComplexLottoGenerator(ManualLottoGenerator manualLottoGenerator, AutoLottoGenerator autoLottoGenerator) {
        this.manualLottoGenerator = manualLottoGenerator;
        this.autoLottoGenerator = autoLottoGenerator;
    }

    @Override
    public List<Lotto> generateLottos() {
        List<Lotto> lottos = manualLottoGenerator.generateLottos();
        lottos.addAll(autoLottoGenerator.generateLottos());
        return lottos;
    }
}

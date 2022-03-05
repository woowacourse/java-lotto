package model;

import java.util.List;
import model.generator.ManualGenerator;
import model.generator.RandomGenerator;

public class LottoMachine {
    private final IssuedLottos autoIssuedLottos;
    private final IssuedLottos manualIssuedLottos;

    public LottoMachine(List<Lotto> manualLottos, Budget budget) {
        this.manualIssuedLottos = new IssuedLottos(new ManualGenerator(manualLottos));
        this.autoIssuedLottos = new IssuedLottos(new RandomGenerator(getAutoCount(budget)));
    }

    private int getAutoCount(Budget budget) {
        return budget.getMaxCountForLottoIssue() - manualIssuedLottos.getLottosCount();
    }

    public IssuedLottos getAllLottos() {
        return IssuedLottos.merge(manualIssuedLottos, autoIssuedLottos);
    }

    public int getManualLottoCount() {
        return manualIssuedLottos.getLottosCount();
    }

    public int getAutoLottoCount() {
        return autoIssuedLottos.getLottosCount();
    }

    public LottoResult summarize(WinningLottoNumbers winningLottoNumbers) {
        return getAllLottos().summarize(winningLottoNumbers);
    }
}

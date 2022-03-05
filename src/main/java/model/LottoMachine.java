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

    public IssuedLottos IssueAllLottos() {
        return IssuedLottos.merge(manualIssuedLottos, autoIssuedLottos);
    }

    public int manualLottoCount() {
        return manualIssuedLottos.getLottosCount();
    }

    public int autoLottoCount() {
        return autoIssuedLottos.getLottosCount();
    }
}

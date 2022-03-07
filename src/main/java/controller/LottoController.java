package controller;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;
import model.Budget;
import model.IssuedLottos;
import model.Lotto;
import model.LottoNumber;
import model.LottoResult;
import model.ManualLottoCount;
import model.WinningLottoNumbers;
import model.generator.RandomGenerator;

public class LottoController {

    public Budget getBudget(String inputBudget) {
        return Budget.parse(inputBudget);
    }

    public ManualLottoCount getManualCount(String inputManualCount, Budget budget) {
        return ManualLottoCount.parse(inputManualCount, budget);
    }

    public IssuedLottos getManualLottos(List<List<String>> manualLottosTokens) {
        return manualLottosTokens.stream()
                .map(Lotto::parse)
                .collect(collectingAndThen(toList(), IssuedLottos::new));
    }

    public IssuedLottos getAutoLottos(Budget budget, IssuedLottos manualLottos) {
        return IssuedLottos.generatedBy(new RandomGenerator(getAutoCount(budget, manualLottos)));
    }

    private int getAutoCount(Budget budget, IssuedLottos manualLottos) {
        return budget.getMaxCountForLottoIssue() - manualLottos.getLottosCount();
    }

    public IssuedLottos getAllLottos(IssuedLottos manualLottos, IssuedLottos autoLottos) {
        return IssuedLottos.merge(manualLottos, autoLottos);
    }

    public WinningLottoNumbers getWinningLottoNumbers(List<String> strings, String s) {
        Lotto winningLotto = Lotto.parse(strings);
        LottoNumber bonusLottoNumber = LottoNumber.parse(s);
        return new WinningLottoNumbers(winningLotto, bonusLottoNumber);
    }

    public LottoResult getResultOf(IssuedLottos allLottos, WinningLottoNumbers winningLottoNumbers) {
        return allLottos.summarize(winningLottoNumbers);
    }
}

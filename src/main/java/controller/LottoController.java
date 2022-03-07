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

    public IssuedLottos getAutoLottos(Budget budget, IssuedLottos issuedLottos) {
        return IssuedLottos.generatedBy(new RandomGenerator(getAffordableLottoCount(budget, issuedLottos)));
    }

    private int getAffordableLottoCount(Budget budget, IssuedLottos issuedLottos) {
        return budget.getMaxCountForLottoIssue() - issuedLottos.getLottosCount();
    }

    public IssuedLottos getAllLottos(IssuedLottos manualLottos, IssuedLottos autoLottos) {
        return IssuedLottos.merge(manualLottos, autoLottos);
    }

    public WinningLottoNumbers getWinningLottoNumbers(List<String> lottoNumberTokens, String bonusNumberToken) {
        Lotto winningLotto = Lotto.parse(lottoNumberTokens);
        LottoNumber bonusLottoNumber = LottoNumber.parse(bonusNumberToken);
        return new WinningLottoNumbers(winningLotto, bonusLottoNumber);
    }

    public LottoResult getResultOf(IssuedLottos allLottos, WinningLottoNumbers winningLottoNumbers) {
        return allLottos.summarize(winningLottoNumbers);
    }
}

package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import model.Budget;
import model.IssuedLottos;
import model.Lotto;
import model.LottoMachine;
import model.LottoNumber;
import model.LottoResult;
import model.ManualLottoCount;
import model.WinningLottoNumbers;
import view.InputView;
import view.OutputView;

public class LottoController {

    public void run() {
        Budget budget = InputView.getUntilValid(() -> Budget.parse(InputView.inputBudget()));
        LottoMachine lottoMachine = new LottoMachine(getManualLottos(budget), budget);
        OutputView.printIssuedLottos(lottoMachine.getManualLottoCount(), lottoMachine.getAutoLottoCount(),
                getNumbersOf(lottoMachine.getAllLottos()));

        WinningLottoNumbers winningLottoNumbers = InputView.getUntilValid(() -> getWinningLottoNumbers());
        LottoResult totalResult = lottoMachine.summarize(winningLottoNumbers);
        OutputView.printResult(totalResult.getResultMap(), totalResult.getProfitRate(budget));
    }

    private List<Lotto> getManualLottos(Budget budget) {
        ManualLottoCount manualCount = InputView.getUntilValid(
                () -> ManualLottoCount.parse(InputView.inputManualCount(), budget));
        return inputManualLottos(manualCount);
    }

    private List<Lotto> inputManualLottos(ManualLottoCount manualLottoCount) {
        InputView.printManualLottoMessage();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; !manualLottoCount.isSameCount(i); i++) {
            lottos.add(parseManualLotto());
        }
        return lottos;
    }

    private Lotto parseManualLotto() {
        return InputView.getUntilValid(() -> Lotto.parse(InputView.inputLottos()));
    }

    private List<Set<Integer>> getNumbersOf(IssuedLottos issuedLottos) {
        return issuedLottos.getLottos().stream()
                .map(lotto -> toInts(lotto.getLottoNumbers()))
                .collect(Collectors.toList());
    }

    private Set<Integer> toInts(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::intValue)
                .collect(Collectors.toSet());
    }

    private WinningLottoNumbers getWinningLottoNumbers() {
        Lotto winningLotto = InputView.getUntilValid(() -> Lotto.parse(InputView.inputWinningLotto()));
        LottoNumber bonusLottoNumber = InputView.getUntilValid(() -> LottoNumber.parse(InputView.inputBonusNumber()));
        return new WinningLottoNumbers(winningLotto, bonusLottoNumber);
    }
}

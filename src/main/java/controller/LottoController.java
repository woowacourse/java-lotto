package controller;

import static view.OutputView.printIssuedLottoNumbers;
import static view.OutputView.printResult;

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

public class LottoController {

    public void run() {
        Budget budget = InputView.getUntilValid(() -> Budget.parse(InputView.inputMoney()));
        ManualLottoCount manualCount = InputView.getUntilValid(
                () -> ManualLottoCount.parse(InputView.inputManualCount(), budget));
        LottoMachine lottoMachine = new LottoMachine(getManualLottos(manualCount), budget);
        IssuedLottos issueAllLottos = lottoMachine.IssueAllLottos();
        printIssuedLottoNumbers(lottoMachine.manualLottoCount(), lottoMachine.autoLottoCount(), getNumbersOf(issueAllLottos));

        WinningLottoNumbers winningLottoNumbers = InputView.getUntilValid(() -> getWinningLottoNumbers());
        LottoResult totalResult = issueAllLottos.summary(winningLottoNumbers);
        printResult(totalResult.getResultMap(), totalResult.getProfitRate(budget));
    }

    private List<Lotto> getManualLottos(ManualLottoCount manualLottoCount) {
        return InputView.getUntilValid(() -> inputManualLotto(manualLottoCount));
    }

    private List<Lotto> inputManualLotto(ManualLottoCount manualLottoCount) {
        InputView.printManualLottoMessage();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; !manualLottoCount.hasCountAs(i); i++) {
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

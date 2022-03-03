package controller;

import static view.OutputView.printIssuedLottoNumbers;
import static view.OutputView.printResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.IssuedLottos;
import model.Lotto;
import model.LottoNumber;
import model.LottoResult;
import model.Budget;
import model.ManualLottoCount;
import model.WinningLottoNumbers;
import model.generator.RandomLottoGenerator;
import view.InputView;

public class LottoController {

    public void run() {
        Budget inputBudget = InputView.getUntilValid(() -> Budget.parse(InputView.inputMoney()));
        IssuedLottos issuedLottos = new IssuedLottos(inputBudget, new RandomLottoGenerator(), getManualLottos());
        printIssuedLottoNumbers(issuedLottos.getManualLottoCount(), issuedLottos.getAutoLottoCount(), getNumbersOf(issuedLottos));

        WinningLottoNumbers winningLottoNumbers = InputView.getUntilValid(() -> getWinningLottoNumbers());
        LottoResult result = issuedLottos.summary(winningLottoNumbers);

        printResult(result.getResultMap(), result.getProfitRate());
    }

    private List<Lotto> getManualLottos() {
        ManualLottoCount manualLottoCount = InputView.getUntilValid(() -> ManualLottoCount.parse(InputView.inputManualCount()));
        List<Lotto> inputManualLottos = InputView.getUntilValid(() -> inputManualLotto(manualLottoCount));
        return inputManualLottos;
    }

    private List<Lotto> inputManualLotto(ManualLottoCount manualLottoCount) {
        InputView.printManualLottoMessage();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0 ; !manualLottoCount.hasCountAs(i); i++) {
            lottos.add(parseManualLotto());
        }
        return lottos;
    }

    private Lotto parseManualLotto() {
        return InputView.getUntilValid(() -> parseLotto(InputView.inputManualLottoNumbers()));
    }

    private Lotto parseLotto(List<String> tokens) {
        return Lotto.of(tokens.stream()
                .map(LottoNumber::parse)
                .collect(Collectors.toList()));
    }

    private List<Set<Integer>> getNumbersOf(IssuedLottos issuedLotto) {
        return Stream.of(issuedLotto.getManualIssuedLotto(), issuedLotto.getAutoIssuedLotto())
                .flatMap(lottos -> lottos.stream())
                .map(lotto -> toInts(lotto.getLottoNumbers()))
                .collect(Collectors.toList());
    }

    private Set<Integer> toInts(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::intValue)
                .collect(Collectors.toSet());
    }

    private WinningLottoNumbers getWinningLottoNumbers() {
        Lotto winningLotto = InputView.getUntilValid(() -> parseLotto(InputView.inputLottoNumbers()));
        LottoNumber bonusLottoNumber = InputView.getUntilValid(() -> LottoNumber.parse(InputView.inputBonusNumber()));
        return new WinningLottoNumbers(winningLotto, bonusLottoNumber);
    }
}

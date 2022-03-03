package controller;

import static view.OutputView.printIssuedLottoNumbers;
import static view.OutputView.printResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import model.Lotto;
import model.LottoMachine;
import model.LottoNumber;
import model.LottoResult;
import model.Budget;
import model.WinningLottoNumbers;
import model.generator.RandomLottoGenerator;
import view.InputView;

public class LottoController {

    public void run() {
        Budget inputBudget = InputView.getUntilValid(() -> Budget.parse(InputView.inputMoney()));
        LottoMachine lottoMachine = new LottoMachine(inputBudget, new RandomLottoGenerator());
        List<Lotto> issuedLottos = lottoMachine.issueAutoLottos();
        printIssuedLottoNumbers(getNumbersOf(issuedLottos));

        WinningLottoNumbers winningLottoNumbers = InputView.getUntilValid(() -> getWinningLottoNumbers());
        LottoResult result = winningLottoNumbers.summarize(issuedLottos, inputBudget);
        printResult(result.getResultMap(), result.getProfitRate());
    }

    private List<Set<Integer>> getNumbersOf(List<Lotto> issuedLottoNumbers) {
        return issuedLottoNumbers
                .stream()
                .map(lotto -> toInts(lotto.getLottoNumbers()))
                .collect(Collectors.toList());
    }

    private Set<Integer> toInts(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::intValue)
                .collect(Collectors.toSet());
    }

    private WinningLottoNumbers getWinningLottoNumbers() {
        Lotto winningLotto = InputView.getUntilValid(() -> Lotto.of(parseLottoNumbers()));
        LottoNumber bonusLottoNumber = InputView.getUntilValid(() -> LottoNumber.parse(InputView.inputBonusNumber()));
        return new WinningLottoNumbers(winningLotto, bonusLottoNumber);
    }

    private List<LottoNumber> parseLottoNumbers() {
        return InputView.inputLottoNumbers().stream()
                .map(LottoNumber::parse)
                .collect(Collectors.toList());
    }
}

package controller;

import static view.InputView.inputWithMessage;
import static view.OutputView.printIssuedLottoNumbers;
import static view.OutputView.printResult;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import model.Lotto;
import model.LottoMachine;
import model.LottoNumber;
import model.LottoResult;
import model.Money;
import model.WinningLottoNumbers;
import model.generator.RandomLottoGenerator;
import view.InputView;
import view.OutputView;

public class LottoController {

    private static final String DELIMITER = ",";
    private static final int INCLUDE_NULL = -1;

    public void run() {
        Money inputMoney = getUntilValid(this::getMoneyFromUser);
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoGenerator(1, 45));
        List<Lotto> issuedLottos = lottoMachine.issueLotto(inputMoney);
        printIssuedLottoNumbers(getNumbersOf(issuedLottos));

        WinningLottoNumbers winningLottoNumbers = getUntilValid(this::getWinningLottoNumbersFromUser);
        LottoResult result = winningLottoNumbers.summarize(issuedLottos, inputMoney);
        printResult(result.getResultMap(), result.getProfitRate());
    }

    private <T> T getUntilValid(Supplier<T> supplier) {
        T t;
        do {
            t = getFrom(supplier);
        } while (t == null && InputView.isRepeatable());
        return t;
    }

    private <T> T getFrom(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            OutputView.printErrorMessage(e);
            return null;
        }
    }

    private Money getMoneyFromUser() {
        return inputWithMessage("구입금액을 입력해 주세요.", Money::parse);
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

    private WinningLottoNumbers getWinningLottoNumbersFromUser() {
        Lotto winningLotto = getWinningLotto();
        LottoNumber bonusNumber = getBonusNumber();
        return new WinningLottoNumbers(winningLotto, bonusNumber);
    }

    private Lotto getWinningLotto() {
        List<LottoNumber> inputLottoNumbers = inputWithMessage("지난 주 당첨 번호를 입력해 주세요.",
                this::parseLottoNumbers);
        return Lotto.of(inputLottoNumbers);
    }

    private List<LottoNumber> parseLottoNumbers(String text) {
        String[] tokens = text.split(DELIMITER, INCLUDE_NULL);
        return Arrays.stream(tokens)
                .map(token -> LottoNumber.parse(token.trim()))
                .collect(Collectors.toList());
    }

    private LottoNumber getBonusNumber() {
        return inputWithMessage("보너스 볼을 입력해 주세요.", LottoNumber::parse);
    }
}

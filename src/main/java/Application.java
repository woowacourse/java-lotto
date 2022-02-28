
import static view.InputView.inputWithMessage;
import static view.InputView.isRepeatable;
import static view.OutputView.printErrorMessage;
import static view.OutputView.printIssuedLottoNumbers;
import static view.OutputView.printResult;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import model.LottoMachine;
import model.LottoNumber;
import model.Lotto;
import model.LottoResult;
import model.Money;
import model.generator.RandomLottoGenerator;
import model.WinningLottoNumbers;
import view.InputView;
import view.OutputView;
import view.parser.BonusNumberParser;
import view.parser.LottoNumbersParser;
import view.parser.MoneyParser;
import view.parser.Parser;

public class Application {

    public static final Parser<Integer> MONEY_PARSER = new MoneyParser();
    public static final Parser<List<Integer>> LOTTO_NUMBERS_PARSER = new LottoNumbersParser();
    public static final Parser<Integer> BONUS_NUMBER_PARSER = new BonusNumberParser();

    public static void main(String[] args) {
        try {
            run();
        } catch(Exception e) {
            printErrorMessage(e);
        }
    }

    private static void run() {
        Money inputMoney = getUntilValid(Application::getMoneyFromUser);

        List<Lotto> issuedLottos = issueLottoNumbers(inputMoney);

        printIssuedLottoNumbers(getNumbersOf(issuedLottos));

        WinningLottoNumbers winningLottoNumbers = getUntilValid(Application::getWinningLottoNumbersFromUser);
        LottoResult result = winningLottoNumbers.summarize(issuedLottos, inputMoney);
        printResult(result);
    }

    private static <T> T getUntilValid(Supplier<T> supplier) {
        T t;
        do {
            t = getFrom(supplier);
        } while(t == null && isRepeatable());
        return t;
    }

    private static <T> T getFrom(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            OutputView.printErrorMessage(e);
            return null;
        }
    }

    private static Money getMoneyFromUser() {
        int moneyAmount = InputView.inputWithMessage("구입금액을 입력해 주세요.", MONEY_PARSER::parse);
        Money inputMoney = new Money(moneyAmount);
        return inputMoney;
    }

    private static List<Lotto> issueLottoNumbers(Money inputMoney) {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoGenerator(1, 45));
        return lottoMachine.issueLotto(inputMoney);
    }

    private static List<Set<Integer>> getNumbersOf(List<Lotto> issuedLottoNumbers) {
        return issuedLottoNumbers
                .stream()
                .map(lotto -> toInts(lotto.getLottoNumbers()))
                .collect(Collectors.toList());
    }

    private static Set<Integer> toInts(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoNumber::getIntValue)
                .collect(Collectors.toSet());
    }

    private static WinningLottoNumbers getWinningLottoNumbersFromUser() {
        Lotto winningLotto = getWinningLotto();
        LottoNumber bonusNumber = getBonusNumber();
        return new WinningLottoNumbers(winningLotto, bonusNumber);
    }

    private static Lotto getWinningLotto() {
        List<Integer> inputLottoNumbers = inputWithMessage("지난 주 당첨 번호를 입력해 주세요.",
                LOTTO_NUMBERS_PARSER::parse);
        return Lotto.of(inputLottoNumbers);
    }

    private static LottoNumber getBonusNumber() {
        int bonusNumberInput = inputWithMessage("보너스 볼을 입력해 주세요.", BONUS_NUMBER_PARSER::parse);
        return new LottoNumber(bonusNumberInput);
    }
}

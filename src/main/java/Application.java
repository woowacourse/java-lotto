
import static view.InputView.getUntilValid;
import static view.InputView.inputWithMessage;
import static view.OutputView.printErrorMessage;
import static view.OutputView.printIssuedLottoNumbers;
import static view.OutputView.printResult;

import java.util.List;
import model.LottoMachine;
import model.LottoNumber;
import model.LottoNumbers;
import model.LottoResult;
import model.Money;
import model.generator.RandomLottoNumbersGenerator;
import model.WinningLottoNumbers;
import view.InputView;
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

        List<LottoNumbers> issuedLottoNumbers = issueLottoNumbers(inputMoney);
        printIssuedLottoNumbers(issuedLottoNumbers);

        WinningLottoNumbers winningLottoNumbers = getUntilValid(Application::getWinningLottoNumbersFromUser);
        LottoResult result = winningLottoNumbers.summarize(issuedLottoNumbers, inputMoney);
        printResult(result);
    }

    private static Money getMoneyFromUser() {
        int moneyAmount = InputView.inputWithMessage("구입금액을 입력해 주세요.", MONEY_PARSER::parse);
        Money inputMoney = new Money(moneyAmount);
        return inputMoney;
    }

    private static List<LottoNumbers> issueLottoNumbers(Money inputMoney) {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumbersGenerator(1, 45));
        return lottoMachine.issueLotto(inputMoney);
    }

    private static WinningLottoNumbers getWinningLottoNumbersFromUser() {
        List<Integer> inputLottoNumbers = inputWithMessage("지난 주 당첨 번호를 입력해 주세요.",
                LOTTO_NUMBERS_PARSER::parse);
        int bonusNumber = inputWithMessage("보너스 볼을 입력해 주세요.", BONUS_NUMBER_PARSER::parse);
        return new WinningLottoNumbers(new LottoNumbers(inputLottoNumbers), new LottoNumber(bonusNumber));
    }
}

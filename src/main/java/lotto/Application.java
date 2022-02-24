package lotto;

import static lotto.view.InputView.inputSelectiveRepeatably;
import static lotto.view.OutputView.printErrorMessage;
import static lotto.view.OutputView.printIssuedLottoNumbers;
import static lotto.view.OutputView.printResult;

import java.util.List;
import lotto.model.LottoMachine;
import lotto.model.Number;
import lotto.model.Lotto;
import lotto.model.Statistic;
import lotto.model.Money;
import lotto.model.RandomLottoGenerator;
import lotto.model.WinningLotto;
import lotto.view.NumberParser;
import lotto.view.LottoParser;
import lotto.view.MoneyParser;
import lotto.view.OutputView;
import lotto.view.Parser;

public class Application {

    public static final Parser<Money> MONEY_PARSER = new MoneyParser();
    public static final Parser<Lotto> LOTTO_NUMBERS_PARSER = new LottoParser();
    public static final Parser<Number> BONUS_NUMBER_PARSER = new NumberParser();

    public static void main(String[] args) {
        try {
            run();
        } catch(Exception e) {
            printErrorMessage(e);
        }
    }

    private static void run() {
        Money inputMoney = inputSelectiveRepeatably("구입금액을 입력해 주세요.", MONEY_PARSER::parse,
            OutputView::printErrorMessage);
        List<Lotto> issuedLottoNumbers = issueLottoNumbers(inputMoney);
        printIssuedLottoNumbers(issuedLottoNumbers);
        WinningLotto winningLotto = createWinningLottoNumbers();
        Statistic result = winningLotto.summarize(issuedLottoNumbers, inputMoney);
        printResult(result);
    }

    private static List<Lotto> issueLottoNumbers(Money inputMoney) {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoGenerator(1, 45));
        return lottoMachine.issueLotto(inputMoney);
    }

    private static WinningLotto createWinningLottoNumbers() {
        Lotto lottoNumbers = inputSelectiveRepeatably("지난 주 당첨 번호를 입력해 주세요.",
                LOTTO_NUMBERS_PARSER::parse, OutputView::printErrorMessage);
        Number bonusNumber = inputSelectiveRepeatably("보너스 볼을 입력해 주세요.", BONUS_NUMBER_PARSER::parse,
                OutputView::printErrorMessage);
        return new WinningLotto(lottoNumbers, bonusNumber);
    }
}

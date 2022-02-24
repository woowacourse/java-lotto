package lotto;

import static lotto.view.InputView.repeatablyExecute;
import static lotto.view.InputView.repeatablyInput;
import static lotto.view.OutputView.printErrorMessage;
import static lotto.view.OutputView.printIssuedLottoes;
import static lotto.view.OutputView.printMessage;
import static lotto.view.OutputView.printResult;

import java.util.List;
import lotto.model.LottoMachine;
import lotto.model.Number;
import lotto.model.Lotto;
import lotto.model.Statistic;
import lotto.model.Money;
import lotto.model.RandomLottoGenerator;
import lotto.model.WinnerLotto;
import lotto.view.NumberParser;
import lotto.view.LottoParser;
import lotto.view.MoneyParser;
import lotto.view.OutputView;
import lotto.view.Parser;

public class Application {

    public static final Parser<Money> MONEY_PARSER = new MoneyParser();
    public static final Parser<Lotto> LOTTO_PARSER = new LottoParser();
    public static final Parser<Number> NUMBER_PARSER = new NumberParser();

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            printMessage(e.getMessage());
        }
    }

    private static void run() {
        Money inputMoney = repeatablyInput("구입금액을 입력해 주세요.", MONEY_PARSER::parse,
            OutputView::printErrorMessage);
        List<Lotto> lottoes = issueLottoNumbers(inputMoney);
        printIssuedLottoes(lottoes);
        Statistic statistic = createWinnerLotto().summarize(lottoes, inputMoney);
        printResult(statistic);
    }

    private static List<Lotto> issueLottoNumbers(Money inputMoney) {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoGenerator(1, 45));
        return lottoMachine.issueLotto(inputMoney);
    }

    private static WinnerLotto createWinnerLotto() {
        return repeatablyExecute(() -> new WinnerLotto(inputWinnerLotto(), inputBonus()),
            OutputView::printErrorMessage);
    }

    private static Lotto inputWinnerLotto() {
        return repeatablyInput("지난 주 당첨 번호를 입력해 주세요.", LOTTO_PARSER::parse,
            OutputView::printErrorMessage);
    }

    private static Number inputBonus() {
        return repeatablyInput("보너스 볼을 입력해 주세요.", NUMBER_PARSER::parse,
            OutputView::printErrorMessage);
    }
}

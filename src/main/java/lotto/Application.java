package lotto;

import static lotto.view.InputView.createMoney;
import static lotto.view.InputView.createWinnerLotto;
import static lotto.view.OutputView.printIssuedLottoes;
import static lotto.view.OutputView.printMessage;
import static lotto.view.OutputView.printResult;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoMachine;
import lotto.model.Money;
import lotto.model.RandomLottoGenerator;
import lotto.model.Statistic;

public class Application {

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            printMessage("종료되었습니다!");
        }
    }

    private static void run() {
        Money money = createMoney();
        List<Lotto> lottoes = issueLottoNumbers(money);
        printIssuedLottoes(lottoes);
        Statistic statistic = createWinnerLotto().summarize(lottoes, money);
        printResult(statistic);
    }

    private static List<Lotto> issueLottoNumbers(Money inputMoney) {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoGenerator(1, 45));
        return lottoMachine.issueLotto(inputMoney);
    }
}

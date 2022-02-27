package lotto;

import static lotto.model.LottoNumber.LOTTO_NUMBER_LOWER_BOUND;
import static lotto.model.LottoNumber.LOTTO_NUMBER_UPPER_BOUND;
import static lotto.view.InputView.inputMoney;
import static lotto.view.InputView.inputWinnerLotto;
import static lotto.view.OutputView.printLottoes;
import static lotto.view.OutputView.printMessage;
import static lotto.view.OutputView.printStatistic;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoGenerator;
import lotto.model.LottoMachine;
import lotto.model.Money;
import lotto.model.RandomLottoGenerator;
import lotto.model.Statistic;
import lotto.model.WinnerLotto;
import lotto.view.exception.LottoFinishedException;

public class Application {

    public static void main(String[] args) {
        try {
            run();
        } catch (LottoFinishedException e) {
            printMessage("종료되었습니다!");
        }
    }

    private static void run() {
        Money inputMoney = inputMoney();
        List<Lotto> lottoes = issueLottoes(inputMoney);
        printLottoes(lottoes);
        WinnerLotto winnerLotto = inputWinnerLotto();
        Statistic statistic = winnerLotto.summarize(lottoes);
        printStatistic(statistic);
    }

    private static List<Lotto> issueLottoes(Money inputMoney) {
        return lottoMachine().issueLotto(inputMoney);
    }

    private static LottoMachine lottoMachine() {
        return new LottoMachine(lottoGenerator());
    }

    private static LottoGenerator lottoGenerator() {
        return new RandomLottoGenerator(LOTTO_NUMBER_LOWER_BOUND, LOTTO_NUMBER_UPPER_BOUND);
    }

}

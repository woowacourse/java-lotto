package lotto;

import static lotto.model.Rank.FIFTH;
import static lotto.model.Rank.FIRST;
import static lotto.model.Rank.FOURTH;
import static lotto.model.Rank.SECOND;
import static lotto.model.Rank.THIRD;
import static lotto.view.InputView.inputBonusText;
import static lotto.view.InputView.inputLottoText;
import static lotto.view.InputView.inputMoneyText;

import java.util.Map;
import java.util.function.Supplier;
import lotto.model.Lotto;
import lotto.model.LottoGenerator;
import lotto.model.LottoMachine;
import lotto.model.LottoNumber;
import lotto.model.Lottoes;
import lotto.model.Money;
import lotto.model.Statistic;
import lotto.model.WinnerLotto;
import lotto.model.exception.DuplicatedNumberException;
import lotto.model.exception.InvalidLottoSizeException;
import lotto.model.exception.InvalidNumberRangeException;
import lotto.model.exception.InvalidRankException;
import lotto.model.exception.LottoException;
import lotto.view.LottoParser;
import lotto.view.OutputView;

public class Application {

    private static final Map<Class<? extends Exception>, String> EXCEPTION_MESSAGE_MAP =
        Map.of(DuplicatedNumberException.class, "중복된 로또 번호는 입력할 수 없습니다.",
            InvalidLottoSizeException.class, "로또 번호 갯수는 6개여야 합니다.",
            InvalidRankException.class, "일치하는 로또 번호 갯수는 0 ~ 6 사이여야 합니다.",
            InvalidNumberRangeException.class, "로또 번호는 1 ~ 45 사이여야 합니다.");

    public static void main(String[] args) {
        Money money = initMoney();
        LottoMachine lottoMachine = createLottoMachine();
        Lottoes lottoes = issueLottoes(lottoMachine, money);
        WinnerLotto winnerLotto = initWinnerLotto();
        summarize(winnerLotto, lottoes);
    }

    private static LottoMachine createLottoMachine() {
        return new LottoMachine(LottoGenerator.randomLottoGenerator());
    }

    private static Money initMoney() {
        return createTemplate(Application::createMoney);
    }

    private static Money createMoney() {
        String moneyText = inputMoneyText(Application::handleException);
        int amount = Integer.parseInt(moneyText.trim());
        return new Money(amount);
    }

    private static <T> T createTemplate(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (LottoException e) {
            handleException(e);
            return supplier.get();
        }
    }

    private static void handleException(Exception e) {
        OutputView.printMessage(errorMessage(e));
    }

    private static String errorMessage(Exception e) {
        if (EXCEPTION_MESSAGE_MAP.containsKey(e.getClass())) {
            return EXCEPTION_MESSAGE_MAP.get(e.getClass());
        }
        return e.getMessage();
    }

    private static Lottoes issueLottoes(LottoMachine lottoMachine, Money money) {
        Lottoes lottoes = lottoMachine.issueLotto(money);
        OutputView.printLottoSize(lottoes.size());
        for (Lotto lotto : lottoes) {
            OutputView.printLotto(lotto.getIntValues());
        }
        return lottoes;
    }

    private static WinnerLotto initWinnerLotto() {
        return createTemplate(() -> new WinnerLotto(createLotto(), createBonusNumber()));
    }

    private static Lotto createLotto() {
        LottoParser lottoParser = new LottoParser();
        String lottoText = inputLottoText(Application::handleException);
        return lottoParser.convert(lottoText);
    }

    private static LottoNumber createBonusNumber() {
        String bonusText = inputBonusText(Application::handleException);
        int bonus = Integer.parseInt(bonusText.trim());
        return new LottoNumber(bonus);
    }

    private static void summarize(WinnerLotto winnerLotto, Lottoes lottoes) {
        Statistic statistic = winnerLotto.summarize(lottoes);
        OutputView.printStatistic(statistic.getCountByRank(FIRST), statistic.getCountByRank(SECOND),
            statistic.getCountByRank(THIRD), statistic.getCountByRank(FOURTH),
            statistic.getCountByRank(FIFTH), statistic.getProfitRate().getDoubleValue());
    }
}

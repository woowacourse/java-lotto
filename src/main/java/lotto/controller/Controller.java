package lotto.controller;

import static lotto.model.Rank.FIFTH;
import static lotto.model.Rank.FIRST;
import static lotto.model.Rank.FOURTH;
import static lotto.model.Rank.SECOND;
import static lotto.model.Rank.THIRD;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
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
import lotto.view.InputView;
import lotto.view.LottoParser;
import lotto.view.OutputView;
import verus.controller.RunnableTemplate;
import verus.view.InputTemplate;

public class Controller {

    private static final Map<Class<? extends Exception>, String> EXCEPTION_MESSAGE_MAP =
        Map.of(DuplicatedNumberException.class, "중복된 로또 번호는 입력할 수 없습니다.",
            InvalidLottoSizeException.class, "로또 번호 갯수는 6개여야 합니다.",
            InvalidRankException.class, "일치하는 로또 번호 갯수는 0 ~ 6 사이여야 합니다.",
            InvalidNumberRangeException.class, "로또 번호는 1 ~ 45 사이여야 합니다.");

    private final RunnableTemplate runnableTemplate;
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputStream inputStream, OutputStream outputStream) {
        runnableTemplate = new RunnableTemplate(new InputTemplate(inputStream, outputStream));
        inputView = new InputView(inputStream, outputStream);
        outputView = new OutputView(outputStream);
    }

    public void run() {
        LottoMachine lottoMachine = createLottoMachine();
        Money money = runnableTemplate.repeatablyRun(this::initMoney,
            this::handleException, LottoException.class);
        Lottoes lottoes = runnableTemplate.repeatablyRun(() -> issueLottoes(lottoMachine, money),
            this::handleException, LottoException.class);
        WinnerLotto winnerLotto = runnableTemplate.repeatablyRun(this::initWinnerLotto,
            this::handleException, LottoException.class);
        runnableTemplate.repeatablyRun(() -> summarize(winnerLotto, lottoes),
            this::handleException, LottoException.class);
    }

    private LottoMachine createLottoMachine() {
        return new LottoMachine(LottoGenerator.randomLottoGenerator());
    }

    private Money initMoney() {
        String moneyText = inputView.inputMoneyText(this::handleException);
        int amount = Integer.parseInt(moneyText.trim());
        return new Money(amount);
    }

    private void handleException(Exception e) {
        outputView.printMessage(errorMessage(e));
    }

    private static String errorMessage(Exception e) {
        if (EXCEPTION_MESSAGE_MAP.containsKey(e.getClass())) {
            return EXCEPTION_MESSAGE_MAP.get(e.getClass());
        }
        return e.getMessage();
    }

    private Lottoes issueLottoes(LottoMachine lottoMachine, Money money) {
        Lottoes lottoes = lottoMachine.issueLotto(money);
        outputView.printLottoSize(lottoes.size());
        for (Lotto lotto : lottoes) {
            outputView.printLotto(lotto.getIntValues());
        }
        return lottoes;
    }

    private WinnerLotto initWinnerLotto() {
        return new WinnerLotto(createLotto(), createBonusNumber());
    }

    private Lotto createLotto() {
        LottoParser lottoParser = new LottoParser();
        String lottoText = inputView.inputLottoText(this::handleException);
        return lottoParser.convert(lottoText);
    }

    private LottoNumber createBonusNumber() {
        String bonusText = inputView.inputBonusText(this::handleException);
        int bonus = Integer.parseInt(bonusText.trim());
        return new LottoNumber(bonus);
    }

    private void summarize(WinnerLotto winnerLotto, Lottoes lottoes) {
        Statistic statistic = winnerLotto.summarize(lottoes);
        outputView.printStatistic(statistic.getCountByRank(FIRST), statistic.getCountByRank(SECOND),
            statistic.getCountByRank(THIRD), statistic.getCountByRank(FOURTH),
            statistic.getCountByRank(FIFTH), statistic.getProfitRate().getDoubleValue());
    }
}

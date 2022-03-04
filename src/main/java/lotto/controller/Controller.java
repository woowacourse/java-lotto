package lotto.controller;

import static java.util.stream.Collectors.toList;
import static lotto.controller.ControllerTemplate.supplierTemplate;
import static lotto.model.Rank.FIFTH;
import static lotto.model.Rank.FIRST;
import static lotto.model.Rank.FOURTH;
import static lotto.model.Rank.SECOND;
import static lotto.model.Rank.THIRD;
import static lotto.view.InputView.inputBonusText;
import static lotto.view.InputView.inputManualLottoesText;
import static lotto.view.InputView.inputMoneyText;
import static lotto.view.InputView.inputWinnerLottoText;

import java.util.List;
import lotto.model.Lotto;
import lotto.model.LottoGenerator;
import lotto.model.LottoMachine;
import lotto.model.LottoNumber;
import lotto.model.Lottoes;
import lotto.model.Money;
import lotto.model.Statistic;
import lotto.model.WinnerLotto;
import lotto.view.LottoConvertor;
import lotto.view.OutputView;

public class Controller {

    private static final LottoConvertor LOTTO_CONVERTOR = new LottoConvertor();

    private Controller() {

    }

    public static void run() {
        Money money = supplierTemplate(Controller::createMoney, ExceptionHandler::handle);
        LottoMachine lottoMachine = supplierTemplate(() -> createLottoMachine(money),
            ExceptionHandler::handle);
        Lottoes issuedLottoes = issueLottoes(lottoMachine);
        WinnerLotto winnerLotto = supplierTemplate(Controller::createWinnerLotto,
            ExceptionHandler::handle);
        summarize(issuedLottoes, winnerLotto);
    }

    private static Money createMoney() {
        String moneyText = inputMoneyText(ExceptionHandler::handle);
        int amount = Integer.parseInt(moneyText.trim());
        return new Money(amount);
    }

    private static LottoMachine createLottoMachine(Money money) {
        return createLottoMachine(money, manualLottoes());
    }

    private static Lottoes manualLottoes() {
        List<String> manualLottoesText = inputManualLottoesText(ExceptionHandler::handle);
        return createManualLottoes(manualLottoesText);
    }

    private static Lottoes createManualLottoes(List<String> manualLottoesText) {
        List<Lotto> manualLottoes = manualLottoesText.stream()
            .map(LOTTO_CONVERTOR::convert)
            .collect(toList());
        return new Lottoes(manualLottoes);
    }

    private static LottoMachine createLottoMachine(Money money, Lottoes manualLottoes) {
        return new LottoMachine(LottoGenerator.randomLottoGenerator(), money, manualLottoes);
    }

    private static Lottoes issueLottoes(LottoMachine lottoMachine) {
        OutputView.printLottoSize(lottoMachine.getManualLottoesSize(),
            lottoMachine.getAutoLottoesSize());
        Lottoes lottoes = lottoMachine.issueLotto();
        for (Lotto lotto : lottoes) {
            OutputView.printLotto(lotto.getIntValues());
        }
        return lottoes;
    }

    private static WinnerLotto createWinnerLotto() {
        return new WinnerLotto(createLotto(), createBonusNumber());
    }

    private static Lotto createLotto() {
        String lottoText = inputWinnerLottoText(ExceptionHandler::handle);
        return LOTTO_CONVERTOR.convert(lottoText);
    }

    private static LottoNumber createBonusNumber() {
        String bonusText = inputBonusText(ExceptionHandler::handle);
        int bonus = Integer.parseInt(bonusText.trim());
        return new LottoNumber(bonus);
    }

    private static void summarize(Lottoes issuedLottoes, WinnerLotto winnerLotto) {
        Statistic statistic = winnerLotto.summarize(issuedLottoes);
        OutputView.printStatistic(statistic.getCountByRank(FIRST), statistic.getCountByRank(SECOND),
            statistic.getCountByRank(THIRD), statistic.getCountByRank(FOURTH),
            statistic.getCountByRank(FIFTH), statistic.getProfitRate().getDoubleValue());
    }

}

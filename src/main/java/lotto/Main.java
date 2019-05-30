package lotto;

import lotto.domain.*;
import lotto.domain.domainexception.InvalidCustomCountException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Main {
    public static void main(String[] args) {
        Money money = insertMoney();
        int countOfCustomLottos = countOfCustomLottos(money);

        LottoPaper lottoPaper = createLottoPaper(money, countOfCustomLottos);

        OutputView.printPurchase(money, countOfCustomLottos);
        OutputView.printLottoPaper(lottoPaper);

        WinningLotto winningLotto = insertWinningLotto();

        Statistics statistics = new Statistics(lottoPaper.matchLotto(winningLotto));

        OutputView.printStatistics(statistics);
    }

    private static Money insertMoney() {
        try {
            return new Money(InputView.insertMoney());
        } catch (Exception e) {
            return insertMoney();
        }
    }

    private static int countOfCustomLottos(Money money) {
        try {
            int countOfCustomLottos = InputView.countOfCustomLottos();
            validCountOfCustomLotto(money, countOfCustomLottos);

            return countOfCustomLottos;
        } catch (Exception e) {
            return countOfCustomLottos(money);
        }
    }

    private static void validCountOfCustomLotto(Money money, int countOfCustomLottos) {
        checkMaximum(money, countOfCustomLottos);
        checkMinimum(countOfCustomLottos);
    }

    private static void checkMaximum(Money money, int countOfCustomLottos) {
        if (money.howManyLotto() - countOfCustomLottos < 0) {
            throw new InvalidCustomCountException("구매 금액보다 많은 로또는 구매가 불가능합니다.");
        }
    }
    private static void checkMinimum(int countOfCustomLottos){
        if (countOfCustomLottos < 0) {
            throw new InvalidCustomCountException("0장 보다 작을순 없습니다.");
        }
    }

    private static LottoPaper createLottoPaper(Money money, int countOfCustomLottos) {
        try {
            LottoOMRCard lottoOMRCard = new LottoOMRCard();

            for (int i = 0; i < countOfCustomLottos; i++) {
                lottoOMRCard.addCustomLotto(InputView.inputCustomLotto());
            }

            LottoVendingMachine lottoVendingMachine = new LottoVendingMachine();

            return lottoVendingMachine.buyLotto(money, lottoOMRCard);
        } catch (Exception e) {
            return createLottoPaper(money, countOfCustomLottos);
        }
    }

    private static WinningLotto insertWinningLotto() {
        try {
            Lotto Lotto = CustomLottoGenerator.makeLotto(InputView.inputWinningLotto());
            LottoNumber bonusNumber = LottoNumber.generateNumber(InputView.inputBonusNumber());

            return new WinningLotto(Lotto, bonusNumber);
        } catch (Exception e) {
            return insertWinningLotto();
        }

    }
}

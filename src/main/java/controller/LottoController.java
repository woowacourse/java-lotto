package controller;

import domain.*;

import java.util.SortedMap;

import view.InputView;
import view.OutputView;

public class LottoController {

    public static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String ERROR_BONUS_NUMBER_CONTAIN_MESSAGE = "지난주 당첨번호와 보너스가 중복일 수 없습니다.";

    public void start() {
        final Money money = getMoney();
        final Count manualCount = getManualCount(money);
        final IssuedLotto issuedLotto = new IssuedLotto();

        OutputView.printManualLottoInstruction();
        OutputView.printLotto(issuedLotto.issue(money, manualCount), manualCount.getCount());

        SortedMap<RankPrize, Integer> rankCounts = pickLotto(issuedLotto);

        OutputView.printWinStatistics(rankCounts);
        OutputView.printWinProfit(money.calculateProfit(Prize.calculatePrize(rankCounts)));
    }

    private SortedMap<RankPrize, Integer> pickLotto(IssuedLotto issuedLotto) {
        final Lotto lastWinLotto = getWinLotto();
        final LottoNumber bonusNumber = getBonusNumber(lastWinLotto);
        final CorrectNumbers correctNumbers = new CorrectNumbers();
        return correctNumbers.run(issuedLotto, lastWinLotto, bonusNumber);
    }

    private Money getMoney() {
        try {
            return new Money(InputView.getMoney());
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getMoney();
        }
    }

    private Count getManualCount(Money money) {
        try {
            return Count.getManualCount(InputView.getManualCount(), money);
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getManualCount(money);
        }
    }

    public static String getManualLotto() {
        return InputView.getManualLotto();
    }

    private Lotto getWinLotto() {
        try {
            return new Lotto(InputView.getWinLotto());
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getWinLotto();
        }
    }

    private LottoNumber getBonusNumber(final Lotto lotto) {
        try {
            LottoNumber bonusNumber = LottoNumber.getLottoNumber(InputView.getBonusNumber());
            if (isBonusNumberContain(lotto, bonusNumber)) {
                throw new IllegalArgumentException(ERROR_BONUS_NUMBER_CONTAIN_MESSAGE);
            }
            return bonusNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getBonusNumber(lotto);
        }
    }

    private boolean isBonusNumberContain(final Lotto lotto, final LottoNumber bonusNumber) {
        return lotto.isContainNumber(bonusNumber);
    }
}

package controller;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoNumber;
import domain.Money;
import domain.RankPrice;
import java.util.SortedMap;
import view.InputView;
import view.OutputView;

public class LottoController {

    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String ERROR_BONUS_NUMBER_CONTAIN_MESSAGE = "지난주 당첨번호와 보너스가 중복일 수 없습니다.";

    public void start() {
        final LottoFactory lottoFactory = new LottoFactory(getMoney());
        OutputView.printLotto(lottoFactory.issueLotto());

        Lotto lastWinLotto = getWinLotto();
        LottoNumber bonusNumber = getBonusNumber(lastWinLotto);

        SortedMap<RankPrice, Integer> rankCounts = lottoFactory.run(lastWinLotto, bonusNumber);

        OutputView.printWinStatistics(rankCounts);
        OutputView.printWinProfit(lottoFactory.calculateProfit(rankCounts));
    }

    private Money getMoney() {
        try {
            Money money = new Money(InputView.getMoney());
            return money;
        } catch (IllegalArgumentException e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            return getMoney();
        }
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
            LottoNumber bonusNumber = new LottoNumber(InputView.getBonusNumber());
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

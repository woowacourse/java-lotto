package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoService;
import domain.RankPrice;
import java.util.List;
import java.util.SortedMap;
import view.InputView;
import view.OutputView;

public class LottoController {

    private static final String ERROR_MESSAGE = "[ERROR] ";
    private static final String ERROR_BONUS_NUMBER_CONTAIN_MESSAGE = "지난주 당첨번호와 보너스가 중복일 수 없습니다.";

    LottoService lottoService;

    public LottoController() {
        initService();
    }

    private void initService() {
        try {
            lottoService = new LottoService(InputView.getMoney());
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE + e.getMessage());
            initService();
        }
    }

    public void start() {
        final List<Lotto> issuedLotto = lottoService.issueLotto();
        OutputView.printLotto(issuedLotto);

        Lotto lastWinLotto = getWinLotto();
        LottoNumber bonusNumber = getBonusNumber(lastWinLotto);

        SortedMap<RankPrice, Integer> rankCounts = lottoService.run(lastWinLotto, bonusNumber, issuedLotto);

        OutputView.printWinStatistics(rankCounts);
        OutputView.printWinProfit(lottoService.calculateProfit(rankCounts));
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

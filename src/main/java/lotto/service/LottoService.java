package lotto.service;

import lotto.Exception.InvalidCustomLottoNumberException;
import lotto.Exception.InvalidPurchaseException;
import lotto.Exception.InvalidWinningLottoException;
import lotto.InputValidator;
import lotto.domain.*;
import lotto.domain.DAO.ResultDAO;
import lotto.domain.DAO.UserLottoDAO;
import lotto.domain.DAO.WinningLottoDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class LottoService {

    public static CustomLottoCount createCustomLottoCount(String customLottoCountInput, Money money) {
        if (InputValidator.isNotValidCustomLottoCount(customLottoCountInput, money)) {
            throw new InvalidPurchaseException("올바른 수동로또 개수를 입력해주세요.");
        }
        return CustomLottoCountFactory
                .createCustomLottoCount(Integer.parseInt(customLottoCountInput), money);
    }

    public static Money createMoney(String moneyInput) {
        if (InputValidator.isNotValidPrice(moneyInput)) {
            throw new InvalidPurchaseException("올바른 구매금액을 입력해주세요.");
        }
        return MoneyFactory.createMoney(Integer.parseInt(moneyInput));
    }

    public static Lottoes createLottoes(Money money, String[] array) {
        if (InputValidator.isNotValidCustomLottoes(array)) {
            throw new InvalidCustomLottoNumberException("올바른 수동로또 번호를 입력해 주세요.");
        }
        return LottoFactory.createLottoes(array, money);
    }

    public static WinningLotto createWinningLotto(List<String> winnigNumberInput, String bonusBall) {
        Lotto lotto = LottoFactory.createLotto(winnigNumberInput);
        if (InputValidator.isNotValidLotto(winnigNumberInput)
                || InputValidator.isNotValidWinningLotto(lotto, bonusBall)) {
            throw new InvalidWinningLottoException("올바른 당첨번호를 입력해 주세요.");
        }
        return LottoFactory.createWinningLotto(lotto, Integer.parseInt(bonusBall));
    }

    public static void InsertWinningLottoInfoData(String bonusBall, WinningLotto winningLotto) throws SQLException {
        WinningLottoDAO.addWinningLottoInfo(winningLotto.getLotto().toString(), Integer.parseInt(bonusBall));
    }

    public static void InsertResultData(Calculator calculator, Money money) throws SQLException {
        ResultDAO.addResult(calculator.getWholeMoney(), calculator.getMatchCounts().toString(),
                calculator.getRate(money));
    }

    public static void selectUserLottoNumbersByCurrentRound(Map<String, Object> model, int inquiredRound) throws SQLException {
        UserLottoDAO.selectUserLottoNumbersByCurrentRound(model, inquiredRound);
    }

    public static void selectWinningNumbersByCourrentRound(Map<String, Object> model, int inquiredRound) throws SQLException {
        WinningLottoDAO.selectWinningNumbersByCurrentRound(model, inquiredRound);
    }

    public static void selectWholeResultByCurrentRound(Map<String, Object> model, int inquiredRound) throws SQLException {
        ResultDAO.selectWholeResultByCurrentRound(model, inquiredRound);
    }

    public static void InsertUserLottoNumbers(List<Lotto> lottoList) throws SQLException {
        int currentRound = UserLottoDAO.getCurrentLottoRound() + 1;
        for (int i = 0; i < lottoList.size(); i++) {
            UserLottoDAO.addUserLottoNumbers(lottoList.get(i).toString(), currentRound);
        }
    }
}

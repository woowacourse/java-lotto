package lotto.service;

import lotto.Exception.InvalidCustomLottoNumberException;
import lotto.Exception.InvalidPurchaseException;
import lotto.Exception.InvalidWinningLottoException;
import lotto.InputValidator;
import lotto.domain.*;

import java.util.List;

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
}

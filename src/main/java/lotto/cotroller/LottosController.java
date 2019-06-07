package lotto.cotroller;

import lotto.domain.Money;
import lotto.domain.lottoTicket.Lotto;
import lotto.domain.lottoTicket.Lottos;
import lotto.view.InputView;

import java.util.List;

public class LottosController {
    public static Lottos request(Money userMoney) {
        try {
            int manualLottoNumber = getManualLottoNumber(userMoney);
            List<Lotto> manualLottos = getManualLottos(manualLottoNumber);
            int autoLottoNumber = getAutoLottoNumber(userMoney, manualLottoNumber);
            return new Lottos(manualLottos, autoLottoNumber);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return request(userMoney);
        }
    }

    private static int getManualLottoNumber(Money userMoney) {
        try {
            return userMoney.checkPurchaseLotto(InputView.purchaseManualLotto());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getManualLottoNumber(userMoney);
        }
    }

    private static List<Lotto> getManualLottos(int manualLottoNumber) {
        try {
            return InputView.manualLottoNumber(manualLottoNumber);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getManualLottos(manualLottoNumber);
        }
    }

    private static int getAutoLottoNumber(Money userMoney, int manualLottoNumber) {
        try {
            return userMoney.getAutoLottoNumber(manualLottoNumber);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return getAutoLottoNumber(userMoney, manualLottoNumber);
        }
    }
}

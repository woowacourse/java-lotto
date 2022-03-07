package lotto.controller;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Payment;
import lotto.domain.WinningLotto;
import lotto.view.Entering;
import lotto.view.KeyEnter;

import java.util.ArrayList;
import java.util.List;

import static lotto.view.Input.*;
import static lotto.view.Output.*;

public class Creator {
    private static final Entering entering = new KeyEnter();

    private Creator() {}

    public static Payment createPayment() {
        try {
            return new Payment(inputPayment(entering));
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createPayment();
        }
    }

    public static LottoCount createLottoCount(final Payment payment) {
        try {
            int manualCount = inputManualCount(entering);
            return new LottoCount(payment, manualCount);
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createLottoCount(payment);
        }
    }

    public static Lottos createManualLottos(final LottoCount lottoCount) {
        List<List<Ball>> manualLottos = new ArrayList<>();
        for (int i = 0; i < lottoCount.getManualCount(); i++) {
            selectManualLottoNumbers(manualLottos);
        }
        try {
            return LottoGenerator.pickManualLottos(manualLottos);
        } catch (IllegalArgumentException exception) {
            printErrorMessage(exception.getMessage());
            return createManualLottos(lottoCount);
        }
    }

    public static Lottos createAutoLottos(final LottoCount lottoCount) {
        return LottoGenerator.pickAutoLottos(lottoCount);
    }

    public static Lotto createLottoNumbers() {
        try {
            return Lotto.from(inputLottoNumbers(entering));
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createLottoNumbers();
        }
    }

    public static WinningLotto createWinningLotto(final Lotto winningNumbers) {
        try {
            Ball bonusBall = Ball.of(inputBonusBall(entering));
            return new WinningLotto(winningNumbers, bonusBall);
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createWinningLotto(winningNumbers);
        }
    }

    public static LottoResult createLottoResult(final Lottos lottos, final WinningLotto winningLotto) {
        return lottos.compareWinningLotto(winningLotto);
    }

    private static void selectManualLottoNumbers(final List<List<Ball>> manualLottos) {
        try {
            manualLottos.add(inputLottoNumbers(entering));
        } catch (IllegalArgumentException exception) {
            printErrorMessage(exception.getMessage());
            selectManualLottoNumbers(manualLottos);
        }
    }
}

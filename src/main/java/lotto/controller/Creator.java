package lotto.controller;

import lotto.domain.Ball;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.Payment;
import lotto.domain.Profit;
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

    public static Lottos createLottos(final int lottoCount) {
        List<Lotto> lottos = new ArrayList<>(lottoCount);
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto(Lotto.selectRandomBalls()));
        }
        return new Lottos(lottos);
    }

    public static Lotto createWinningNumbers() {
        try {
            return new Lotto(inputWinningNumbers(entering));
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createWinningNumbers();
        }
    }

    public static WinningLotto createWinningLotto(final Lotto winningNumbers) {
        try {
            Ball bonusBall = new Ball(inputBonusBall(entering));
            return new WinningLotto(winningNumbers, bonusBall);
        } catch (IllegalArgumentException error) {
            printErrorMessage(error.getMessage());
            return createWinningLotto(winningNumbers);
        }
    }

    public static LottoResult createLottoResult() {
        return new LottoResult();
    }

    public static Profit createProfit() {
        return new Profit();
    }
}

package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.WinningInform;
import lotto.view.InputView;

public class InputController {
    private final InputView inputView;

    public InputController(InputView inputView) {
        this.inputView = inputView;
    }

    public Money getMoney(int lottoPrice) {
        while (true) {
            try {
                int amount = inputView.readMoneyAmount();

                return new Money(amount, lottoPrice);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public WinningInform getWinningInform() {
        while (true) {
            try {
                Lotto winningLotto = getWinningLotto();
                int bonus = getBonus();

                return new WinningInform(winningLotto, LottoNumber.of(bonus));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto getWinningLotto() {
        while (true) {
            try {
                List<Integer> numbers = inputView.readWinningNumbers();

                return new Lotto(LottoNumber.from(numbers));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getBonus() {
        while (true) {
            try {
                return inputView.readBonusNumber();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

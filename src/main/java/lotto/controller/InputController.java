package lotto.controller;

import java.util.List;
import lotto.common.utill.InputParser;
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
                String moneyInput = inputView.read("구입금액을 입력해주세요.");

                int moneyAmount = InputParser.parseToInt(moneyInput);
                return new Money(moneyAmount, lottoPrice);
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

                return new WinningInform(winningLotto, new LottoNumber(bonus));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto getWinningLotto() {
        while (true) {
            try {
                String input = inputView.read("지난 주 당첨 번호를 입력해 주세요.");

                List<Integer> numbers = InputParser.parseToList(input);

                return new Lotto(LottoNumber.from(numbers));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getBonus() {
        while (true) {
            try {
                int bonus = getInt("보너스 번호를 입력해주세요.");

                return bonus;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getInt(String prompt) {
        while (true) {
            try {
                String input = inputView.read(prompt);

                return InputParser.parseToInt(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}

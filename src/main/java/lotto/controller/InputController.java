package lotto.controller;

import static lotto.common.constant.Constant.*;
import static lotto.common.exception.ErrorMessage.ERROR_BONUS_OUT_OF_RANGE;
import static lotto.common.exception.ErrorMessage.ERROR_DUPLICATE_WINNING_AND_BONUS;

import java.util.List;
import lotto.common.utill.InputParser;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.dto.WinningInform;
import lotto.view.InputView;

public class InputController {
    private final InputView inputView;

    public InputController(InputView inputView) {
        this.inputView = inputView;
    }

    public Money getMoney(String prompt) {
        while (true) {
            try {
                int number = getInt(prompt);

                return new Money(number);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public WinningInform getWinningInformation() {
        Lotto matchLotto = getMatchLotto("지난 주 당첨 번호를 입력해 주세요.");

        while (true) {
            try {
                int bonus = getBonus("보너스 번호를 입력해주세요.");
                validateBonusDuplicate(matchLotto, bonus);

                return new WinningInform(matchLotto, bonus);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private Lotto getMatchLotto(String prompt) {
        while (true) {
            try {
                String input = inputView.read(prompt);

                List<Integer> numbers = InputParser.parseToList(input);

                return new Lotto(numbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getBonus(String prompt) {
        while (true) {
            try {
                int bonus = getInt(prompt);
                validateBonusRange(bonus);
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

    private void validateBonusRange(int bonus) {
        if(bonus < LOTTO_RANGE_MINIMUM || bonus > LOTTO_RANGE_MAXIMUM) {
            throw new IllegalArgumentException(ERROR_BONUS_OUT_OF_RANGE);
        }
    }

    private void validateBonusDuplicate(Lotto matchLotto, int bonus) {
        if(matchLotto.containsNumber(bonus)) {
            throw new IllegalArgumentException(ERROR_DUPLICATE_WINNING_AND_BONUS);
        }
    }

}

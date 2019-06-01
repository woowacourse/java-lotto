package lotto.controller;

import lotto.domain.CustomLottoCount;
import lotto.domain.CustomLottoCountFactory;
import lotto.InputValidator;
import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Controller {
    public static void main(String[] args) {
        String priceInput = InputView.promptPrice();
        Money money = createMoney(priceInput); // Controller -> createMoney
        String customLottoCountInput = InputView.promptCoustomLottoCount();
        CustomLottoCount customLottoCount = createCustomLottoCount(customLottoCountInput, money); // Controller -> createCustomLottoCount
        String[] customLottoNumbersInput = InputView.promptCustomLotto(customLottoCount);
        Lottoes lottoes = createLottoes(customLottoCount,customLottoNumbersInput,money); // Controller -> createLottoes

        OutputView.printLottoes(lottoes,customLottoCount);

        List<String> winningNumberInput = InputView.promptWinningNumber();
        Lotto winnigLottoNumbers = createWinningLottoNumbers(winningNumberInput); // Controller -> createWinningLottoNumbers
        String bonusBall = InputView.promptBonusBall();
        WinningLotto winningLotto = createWinningLotto(winnigLottoNumbers, bonusBall);// Controller -> createWinningLotto
        Result result = ResultFactory.createResult();
        result.calculateResult(lottoes, winningLotto);

        OutputView.printReults(result, money);
    }

    private static Money createMoney(String moneyInput) {
        while (InputValidator.isNotValidPrice(moneyInput)) {
            System.out.println("올바른 금액을 입력해 주세요 (1000원단위)");
            moneyInput = InputView.promptPrice();
        }
        Money money = MoneyFactory.createMoney(Integer.parseInt(moneyInput));
        return money;
    }

    private static CustomLottoCount createCustomLottoCount(String customLottoCountInput, Money money) {
        while (InputValidator.isNotValidCustomLottoCount(customLottoCountInput, money)) {
            System.out.println("올바른 수동로또 수를 입력해 주세요(금액에 맞게)");
            customLottoCountInput = InputView.promptCoustomLottoCount();
        }
        return CustomLottoCountFactory.createCustomLottoCount(Integer.parseInt(customLottoCountInput), money);
    }

    private static Lottoes createLottoes(CustomLottoCount customLottoCount, String[] customLottoNumbersInput,Money money) {
        while(InputValidator.isNotValidCustomLottoes(customLottoNumbersInput)){
            System.out.println("올바른 로또의 숫자를 입력해주세요(공백불가, 1~45범위의 로또숫자)");
            customLottoNumbersInput = InputView.promptCustomLotto(customLottoCount);
        }
        return LottoFactory.createLottoes(customLottoNumbersInput,money);

    }

    private static Lotto createWinningLottoNumbers(List<String> winningNumberInput) {
        while (InputValidator.isNotValidLotto(winningNumberInput)) {
            System.out.println("올바른 당첨로또 번호를 입력해 주세요.");
            winningNumberInput = InputView.promptWinningNumber();
        }
        return LottoFactory.createLotto(winningNumberInput);
    }

    private static WinningLotto createWinningLotto(Lotto winnigLottoNumbers, String bonusBall) {
        while (InputValidator.isNotValidWinningLotto(winnigLottoNumbers, bonusBall)) {
            System.out.println("보너스볼은 당첨번호와 중복이 안되며, 2개이상 입력이 불가능합니다.");
            bonusBall = InputView.promptBonusBall();
        }
        return LottoFactory.createWinningLotto(winnigLottoNumbers, Integer.parseInt(bonusBall));
    }
}

package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static Money askMoney() {
        OutputView.printMessage("구입 금액을 입력해 주세요.");
        String money = SCANNER.nextLine();
        try {
            return Money.of(money);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askMoney();
        }
    }

    public static LottoAmount askLottoAmount(Money money) {
        OutputView.printMessage(System.lineSeparator() + "수동으로 구매할 로또 수를 입력해 주세요.");
        String manualAmount = SCANNER.nextLine();

        try {
            return LottoAmount.of(money, manualAmount);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askLottoAmount(money);
        }
    }

    public static Lottos askManualLottoNumbers(int manualAmount) {
        OutputView.printMessage(System.lineSeparator() + "수동으로 구매할 번호를 입력해 주세요.");

        try {
            return makeManualLottos(manualAmount);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askManualLottoNumbers(manualAmount);
        }
    }

    private static Lottos makeManualLottos(int manualAmount) {
        List<String> manualLottos = new ArrayList<>(manualAmount);
        for (int i = 0; i < manualAmount; i++) {
            manualLottos.add(SCANNER.nextLine());
        }
        return Lottos.of(manualLottos);
    }

    public static Lotto askLastWinningLottoNumber() {
        OutputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        String input = SCANNER.nextLine();

        try {
            return Lotto.ofLotto(input);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askLastWinningLottoNumber();
        }
    }

    public static LottoNumber askBonusNumber() {
        OutputView.printMessage("보너스 볼을 입력해 주세요.");
        String bonusNumber = SCANNER.nextLine();

        try {
            return LottoNumber.from(bonusNumber);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askBonusNumber();
        }
    }
}

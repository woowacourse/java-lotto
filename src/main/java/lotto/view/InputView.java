package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.Money;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Money askMoney() {
        OutputView.printMessage("구입 금액을 입력해 주세요.");
        String money = scanner.nextLine();
        try {
            return new Money(money);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askMoney();
        }
    }

    public static List<LottoNumber> askLastWinningLottoNumber() {
        OutputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        try {
            return makeWinningLottoNumbers(input);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askLastWinningLottoNumber();
        }
    }

    private static List<LottoNumber> makeWinningLottoNumbers(String input) {
        List<String> splitNumbers = Arrays.asList(input.split(","));
        return splitNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }


    public static LottoNumber askBonusNumber() {
        OutputView.printMessage("보너스 볼을 입력해 주세요.");
        String bonusNumber = scanner.nextLine();
        try {
            return new LottoNumber(bonusNumber);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askBonusNumber();
        }
    }
}

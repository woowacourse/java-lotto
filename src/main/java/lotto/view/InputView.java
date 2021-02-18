package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.WinningLotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Money askMoney() {
        OutputView.printMessage("구입 금액을 입력해 주세요.");
        String money = scanner.nextLine();

        return new Money(money);
    }

    public static WinningLotto askLastWinningLotto() {
        OutputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();

        List<String> splitNumbers = Arrays.asList(input.split(", "));
        List<LottoNumber> LastLottoNumbers = splitNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new WinningLotto(new Lotto(LastLottoNumbers), askBonusNumber());
    }

    private static LottoNumber askBonusNumber() {
        OutputView.printMessage("보너스 볼을 입력해 주세요.");
        String bonusNumber = scanner.nextLine();
        return new LottoNumber(bonusNumber);
    }
}

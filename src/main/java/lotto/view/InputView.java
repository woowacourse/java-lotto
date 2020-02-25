package lotto.view;

import java.util.Scanner;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.utils.StringUtils;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Money inputPurchaseAmount() {
        System.out.println("구입금액을 입력해주세요");
        return new Money(StringUtils.parseInt(scanner.nextLine()));
    }

    public static Lotto inputLastWeekWinningNumbers() {
        System.out.println("지난주 당첨번호을 입력해주세요");
        String input = scanner.nextLine();
        return Lotto.createWinningLotto(StringUtils.parseWithDelimiter(input));
    }

    public static LottoNumber inputBonusNumber() {
        System.out.println("보너스볼을 입력해주세요");
        return new LottoNumber(StringUtils.parseInt(scanner.nextLine()));
    }
}

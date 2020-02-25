package lotto.view;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.utils.StringUtils;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Money inputPurchaseAmount() {
        System.out.println("구입금액을 입력해주세요");
        return new Money(StringUtils.parseInt(scanner.nextLine()));
    }

    public static WinningLotto inputWinningLotto() {
        Set<LottoNumber> winningNumbers = inputLastWeekWinningNumbers();
        LottoNumber bonusNumber = inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private static Set<LottoNumber> inputLastWeekWinningNumbers() {
        System.out.println("지난주 당첨번호을 입력해주세요");
        String input = scanner.nextLine();
        return StringUtils.parseWithDelimiter(input).stream().map(LottoNumber::new).collect(
            Collectors.toSet());
    }

    private static LottoNumber inputBonusNumber() {
        System.out.println("보너스볼을 입력해주세요");
        return new LottoNumber(StringUtils.parseInt(scanner.nextLine()));
    }

}

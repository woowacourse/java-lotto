package lotto.view;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.utils.StringUtils;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Money inputPurchaseAmount() {
        System.out.println("구입금액을 입력해주세요");
        return new Money(StringUtils.parseInt(scanner.nextLine()));
    }

    public static Money inputManualLottoAmount() {
        System.out.println("수동으로 구입할 로또 수를 입력해주세요");
        return new Money(StringUtils.parseInt(scanner.nextLine()));
    }

    public static Lottos inputManualLottos(Money manualLottoAmount) {
        final Set<Lotto> lottos = new HashSet<>();
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < manualLottoAmount.getValue(); i++) {
            String input = scanner.nextLine();
            lottos.add(new Lotto(StringUtils.parseWithDelimiter(input).stream().map(LottoNumber::new).collect(
                Collectors.toSet())));
        }
        return new Lottos(lottos);
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

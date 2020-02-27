package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottosGenerator;
import lotto.domain.Money;
import lotto.utils.StringUtils;
import lotto.view.errors.InvalidInputException;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Money inputPurchaseAmount() {
        System.out.println("구입금액을 입력해주세요");
        try {
            return new Money(StringUtils.parseInt(scanner.nextLine()));
        } catch (NumberFormatException e) {
            throw new InvalidInputException("숫자가 아닌 문자를 입력하였습니다.");
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(e.getMessage());
        }

    }

    public static Lotto inputLastWeekWinningNumbers() {
        System.out.println("지난주 당첨번호을 입력해주세요.");
        String input = scanner.nextLine();
        return Lotto.createWinningLotto(StringUtils.parseWithDelimiter(input));
    }

    public static int inputNumberToBuyManually() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return StringUtils.parseInt(scanner.nextLine());
    }

    public static List<Set<LottoNumber>> inputManualLottos(int numberToBuy) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Set<LottoNumber>> lottoNumbersBasket = new ArrayList<>();
        for (int i = 0; i < numberToBuy; i++) {
            Set<LottoNumber> lottoNumbers = StringUtils.parseWithDelimiter(scanner.nextLine()).stream().map(LottoNumber::new).collect(Collectors.toSet());
            lottoNumbersBasket.add(lottoNumbers);
        }

        return lottoNumbersBasket;
    }

    public static LottoNumber inputBonusNumber() {
        System.out.println("보너스볼을 입력해주세요");
        return new LottoNumber(StringUtils.parseInt(scanner.nextLine()));
    }
}

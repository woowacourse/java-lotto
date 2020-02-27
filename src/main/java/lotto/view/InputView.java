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

    public static int inputNumberToBuyManually(int lottosSize) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            int numberToBuyManually = StringUtils.parseInt(scanner.nextLine());

            if (numberToBuyManually < 0) {
                throw new InvalidInputException(String.format("%d보다 작은 숫자의 로또를 수동으로 구매할 수 없습니다.", 0));
            }

            if (lottosSize < numberToBuyManually) {
                throw new InvalidInputException(String.format("수동으로 구매할 수 있는 로또 개수(%s개)를 초과하였습니다.", lottosSize));
            }
            return numberToBuyManually;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("숫자가 아닌 문자를 입력하였습니다.");
        }
    }

    public static List<Set<LottoNumber>> inputManualLottos(int numberToBuy) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Set<LottoNumber>> lottoNumbersBasket = new ArrayList<>();
        try {
            for (int i = 0; i < numberToBuy; i++) {
                Set<LottoNumber> lottoNumbers = StringUtils.parseWithDelimiter(scanner.nextLine()).stream().map(LottoNumber::new).collect(Collectors.toSet());
                if (lottoNumbers.size() != 6) {
                    throw new InvalidInputException(String.format("로또 번호의 개수는 %d개여야 합니다.", 6));
                }
                lottoNumbersBasket.add(lottoNumbers);
            }

            return lottoNumbersBasket;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("숫자가 아닌 문자를 입력하였습니다.");
        }

    }

    public static LottoNumber inputBonusNumber() {
        System.out.println("보너스볼을 입력해주세요");
        return new LottoNumber(StringUtils.parseInt(scanner.nextLine()));
    }
}

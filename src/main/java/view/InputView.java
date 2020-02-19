package view;

import domain.LottoNumber;
import domain.Money;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    private static final String NUMBER_REGEX = "^[0-9]+$";

    private static Scanner scanner = new Scanner(System.in);

    public static Money enterMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        int money = parseInt(scanner.nextLine());
        return new Money(money);
    }

    private static int parseInt(String input) {
        validateNumber(input);
        return Integer.parseInt(input);
    }

    private static void validateNumber(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new NumberFormatException("숫자를 입력해주세요.");
        }
    }

    public static Set<LottoNumber> enterLastWeekWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Set<LottoNumber> lastWeekWinningNumbers = parseWinningNumbers(scanner.nextLine());
        return lastWeekWinningNumbers;
    }

    private static Set<LottoNumber> parseWinningNumbers(String input) {
        List<LottoNumber> lottoNumbers = Arrays.asList(input.split(",")).stream()
                .map(InputView::parseLottoNumber)
                .collect(Collectors.toList());
        return new HashSet<>(lottoNumbers);
    }

    public static LottoNumber enterBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return parseLottoNumber(scanner.nextLine());
    }

    private static LottoNumber parseLottoNumber(String input) {
        int parsedInt = parseInt(input);
        return LottoNumber.getLottoNumber(parsedInt);
    }
}

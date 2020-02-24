package view;

import domain.numberscontainer.LottoNumber;
import domain.Money;
import domain.numberscontainer.BonusNumberDTO;
import domain.numberscontainer.SixLottoNumbersDTO;
import strategy.TicketFactory;

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

    public static SixLottoNumbersDTO enterLastWeekWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        SixLottoNumbersDTO lastWeekWinningNumbers = parseWinningNumbers(scanner.nextLine());
        return lastWeekWinningNumbers;
    }

    private static SixLottoNumbersDTO parseWinningNumbers(String input) {
        Set<LottoNumber> lottoNumbers = Arrays.asList(input.split(",")).stream()
                .map(String::trim)
                .map(Integer::new)
                .map(LottoNumber::getLottoNumber)
                .collect(Collectors.toSet());
        return new SixLottoNumbersDTO(new HashSet<>(lottoNumbers));
    }

    public static BonusNumberDTO enterBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber = parseInt(scanner.nextLine());
        return new BonusNumberDTO(LottoNumber.getLottoNumber(bonusNumber));
    }
}

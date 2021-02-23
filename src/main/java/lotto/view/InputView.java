package lotto.view;

import lotto.domain.LottoNumber;
import lotto.exception.LottoCustomException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class InputView {

    private static final String SEPARATOR = ",";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public static int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (Exception e) {
            throw new LottoCustomException("구입 금액은 숫자만 가능합니다.");
        }
    }

    public int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return parseInt(scanner.nextLine());
    }

    public Set<LottoNumber> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        return splitAndWrap(scanner.nextLine());
    }

    public int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해주세요.");
        return parseInt(scanner.nextLine());
    }

    private Set<LottoNumber> splitAndWrap(String winningNumbers) {
        return Arrays.stream(winningNumbers.split(SEPARATOR))
                .map(String::trim)
                .map(InputView::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }
}

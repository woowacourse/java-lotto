package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String SEPARATOR = ", ";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int getMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        return translateInteger(scanner.nextLine());
    }

    private int translateInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }
    }

    public List<String> getNormalWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        String inputWinningNumbers = scanner.nextLine();

        return Arrays.asList(inputWinningNumbers.split(SEPARATOR));
    }

    public String getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");

        return scanner.nextLine();
    }

}

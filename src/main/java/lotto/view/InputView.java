package lotto.view;

import lotto.domain.Lotto;
import lotto.validator.InputValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    private String readLine() {
        return scanner.nextLine();
    }

    private List<Integer> parseWinningNumbers(String content) {
        String spacelessContent = content.trim().replace(" ", "");
        List<String> splitContent = Arrays.stream(spacelessContent.split(",")).toList();
        InputValidator.validateWinningNumbers(splitContent);
        return splitContent.stream().map(Integer::parseInt).toList();
    }

    public int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String content = this.readLine();
        System.out.println();
        InputValidator.validateBlank(content);
        InputValidator.validateNumberFormat(content);
        return Integer.parseInt(content);
    }

    public Lotto readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String content = this.readLine();
        System.out.println();
        List<Integer> winningNumbers = this.parseWinningNumbers(content);
        return new Lotto(winningNumbers);
    }

    public int readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String content = this.readLine();
        System.out.println();
        InputValidator.validateNumberFormat(content);
        return Integer.parseInt(content);
    }
}

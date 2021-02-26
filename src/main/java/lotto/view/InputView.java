package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String NUMBER_DELIMITER = ",";
    private final Scanner scanner;
    private final OutputView outputView;

    public InputView(Scanner scanner, OutputView outputView) {
        this.scanner = scanner;
        this.outputView = outputView;
    }

    public int getTicketMoney() {
        outputView.printMessage("구입금액을 입력해 주세요.");
        return getInt();
    }

    public int getManualLottoCount() {
        outputView.printMessage("수동으로 구매할 로또 수를 입력해 주세요");
        return getInt();
    }

    public int getBonusBallNumber() {
        outputView.printMessage("보너스 볼을 입력해 주세요.");
        return getInt();
    }


    public int getInt() {
        try {
            String input = scanner.nextLine()
                                  .trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력은 정수여야 합니다.");
        }
    }

    public List<Integer> getLottoNumbers() {
        try {
            String input = scanner.nextLine()
                                  .trim();
            return Arrays.stream(input.split(NUMBER_DELIMITER))
                         .map(String::trim)
                         .map(Integer::new)
                         .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력은 정수여야 합니다.");
        }
    }

    public List<Integer> getWinningLottoNumbers() {
        outputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        return getLottoNumbers();
    }

}

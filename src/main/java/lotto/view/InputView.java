package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner;
    private static final String NUMBER_DELIMITER = ",";

    public InputView(Scanner scanner) {
        this.scanner = scanner;
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

    public List<Integer> getWinningNumbers() {
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

}

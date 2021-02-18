package lotto.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import lotto.utils.ValidateUtils;

public class InputView {
    public static final String SPLITTER = ",";

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputValue() {
        return ValidateUtils.parseInt(scanner.nextLine());
    }

    public Set<String> inputWinningNumbers() {
        return new HashSet<String>(Arrays.asList(scanner.nextLine().split(SPLITTER)));
    }
}

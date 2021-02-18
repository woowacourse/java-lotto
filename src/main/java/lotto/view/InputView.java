package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputValue() {
        return scanner.nextLine().trim();
    }

    public List<String> inputWinningLottoNumbers() {
        String[] input = scanner.nextLine().split(",");
        return Arrays.stream(input)
            .map(number -> number.trim())
            .collect(Collectors.toList());
    }
}

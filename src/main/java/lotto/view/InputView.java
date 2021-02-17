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

    public int inputDecimal() {
        return scanner.nextInt();
    }

    public List<Integer> inputToIntegerList() {
        String input = scanner.nextLine();
        List<String> splitNumbers = Arrays.asList(input.split(","));

        return stringsToIntegers(splitNumbers);
    }

    public List<Integer> stringsToIntegers(List<String> strings) {
        try {
            return strings.stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}

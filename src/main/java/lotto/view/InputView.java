package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.utils.RefineUtils;

public class InputView {

    public static final String SPLITTER = ",";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int inputValue() {
        return RefineUtils.refineIntegerValue(scanner.nextLine());
    }

    public List<Integer> inputNumbers() {
        return Arrays.stream(scanner.nextLine()
            .split(SPLITTER))
            .map(RefineUtils::refineIntegerValue)
            .collect(Collectors.toList());
    }
}

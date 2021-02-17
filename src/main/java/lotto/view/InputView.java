package lotto.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import lotto.utils.ValidateUtils;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner){
        this.scanner = scanner;
    }

    public String inputValue() {
        return scanner.nextLine();
    }

    public Set<String> inputWinningNumbers() {
        return new HashSet<String>(Arrays.asList(inputValue().split(",")));
    }
}

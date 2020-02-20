package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputPurchaseMoney() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static List<Integer> inputSixNumbers() {
        return Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int inputBonusNumber() {
        return Integer.parseInt(scanner.nextLine());
    }
}

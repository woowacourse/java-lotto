package lotto.model.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ManualRandomNumberGenerator implements NumberGenerator {
    public static final String NUMBER_DELIMITER = ", ";
    private static final Scanner scanner = new Scanner(System.in);

    @Override
    public List<Integer> generate(int size) {
        return Arrays.stream(scanner.nextLine().split(NUMBER_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}

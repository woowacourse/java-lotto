package lotto.view.inputview;

import lotto.utils.NullCheckUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbersParser {
    private static final String ERROR_NO_INPUT = "입력이 없습니다.";

    public static List<Integer> getWinningNumbers(String inputNumbers) {
        NullCheckUtil.checkNullInput(inputNumbers);
        checkNoInput(inputNumbers);

        return Arrays.stream(inputNumbers.replaceAll(" ", "").split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static void checkNoInput(String inputPrice) {
        if (inputPrice.isEmpty()) {
            throw new IllegalArgumentException(ERROR_NO_INPUT);
        }
    }
}

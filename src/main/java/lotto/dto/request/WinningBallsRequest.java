package lotto.dto.request;

import lotto.dto.util.ConvertUtil;

import java.util.Arrays;
import java.util.List;

import static lotto.dto.util.ConvertUtil.convertToNumber;

public record WinningBallsRequest(
    List<Integer> winningNumbers,
    int bonusNumber
) {
    public static final String DELIMITER = ",";

    public static WinningBallsRequest of(String winningNumbers, String bonusNumber) {
        convertToList(winningNumbers);
        return new WinningBallsRequest(convertToList(winningNumbers), convertToNumber(bonusNumber));
    }

    private static List<Integer> convertToList(String winningNumbers) {
        String[] split = winningNumbers.split(DELIMITER);
        return Arrays.stream(split)
            .map(ConvertUtil::convertToNumber)
            .toList();
    }
}

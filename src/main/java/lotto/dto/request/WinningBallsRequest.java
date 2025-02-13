package lotto.dto.request;

import java.util.ArrayList;
import java.util.List;

import static lotto.dto.ConvertUtil.convertToNumber;

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
        List<Integer> numbers = new ArrayList<>();
        for (String data : split) {
            numbers.add(convertToNumber(data));
        }
        return numbers;
    }
}

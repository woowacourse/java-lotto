package view;

import constant.Constants;
import constant.ErrorMessage;
import constant.OutputMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WinLottoView {
    public void printWinNumberGuide() {
        System.out.println(OutputMessage.WIN_NUMBERS);
    }

    public void readWinNumbers() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        List<String> winNumbers = Arrays.stream(input.split(Constants.DELIMETER))
                .map(String::trim)
                .toList();
        validateNumberCount(winNumbers);
    }

    private void validateNumberCount(List<String> winNumbers) {
        if (winNumbers.size() != Constants.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_COUNT_EXCEPTION);
        }
    }
}

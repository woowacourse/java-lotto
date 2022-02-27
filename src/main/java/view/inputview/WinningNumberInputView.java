package view.inputview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import validator.LottoNumberValidator;

public class WinningNumberInputView extends ConsoleInputView<Set<Integer>> {
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입럭해 주세요.";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "입력된 값이 정수가 아닙니다.";
    private static final String LOTTO_SIZE_ERROR_MESSAGE = "중복 없이 6개의 숫자를 입력해 주세요.";
    private static final int LOTTO_TICKET_SIZE = 6;

    @Override
    public Set<Integer> getUserInputData() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        try {
            return inputWinningNumber();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getUserInputData();
        }
    }

    private Set<Integer> inputWinningNumber() {
        String inputData = inputLine();
        List<String> splitInputData = splitInputData(inputData);
        winningNumberSizeCheck(splitInputData);
        splitInputData.forEach(splitNumber -> LottoNumberValidator.validate(parse(splitNumber)));
        return splitInputData.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
    }

    private void winningNumberSizeCheck(List<String> splitInputData) {
        if (isInValidSizeOrDuplicateNumber(splitInputData)) {
            throw new IllegalArgumentException(LOTTO_SIZE_ERROR_MESSAGE);
        }
    }

    private boolean isInValidSizeOrDuplicateNumber(List<String> splitInputData) {
        return splitInputData.size() != LOTTO_TICKET_SIZE
                && (new HashSet<>(splitInputData)).size() != LOTTO_TICKET_SIZE;
    }

    private List<String> splitInputData(String inputData) {
        return Arrays.stream(inputData.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static int parse(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
    }
}

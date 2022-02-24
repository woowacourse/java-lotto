package view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import validator.LottoNumberValidator;

public class WinningNumberInputView implements InputView<List<Integer>> {

    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입럭해 주세요.";
    private static final String SEPARATOR = ",";

    @Override
    public List<Integer> getUserInputData() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        try {
            return inputWinningNumber();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getUserInputData();
        }
    }


    private List<Integer> inputWinningNumber() {
        String inputData = scanner.nextLine();
        List<String> splitInputData = splitInputData(inputData);
        splitInputData.forEach(LottoNumberValidator::validate);

        return splitInputData.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private List<String> splitInputData(final String inputData) {
        return Arrays.stream(inputData.split(SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}

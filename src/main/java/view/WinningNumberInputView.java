package view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import validator.LottoNumberValidator;

public class WinningNumberInputView implements InputView<List<Integer>> {

    @Override
    public List<Integer> getUserInputData() {
        String inputData = scanner.nextLine();
        List<String> strings = Arrays.stream(inputData.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        strings.forEach(LottoNumberValidator::validate);

        return strings.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }


    public List<Integer> printInputWinningNumberAndGet() {
        System.out.println("지난 주 당첨 번호를 입럭해 주세요.");
        try {
            return getUserInputData();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return printInputWinningNumberAndGet();
        }
    }
}

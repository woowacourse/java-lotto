package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String COMMA = ",";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    // todo : 예외처리 메세지 작성 후 try catch 해줄 것
    public int takeLottoMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        return inputDecimal();
    }

    public List<Integer> inputWinningNumbers(){
        System.out.println("지난주 당첨번호를 입력해주세요.");
        return inputToIntegerList();
    }

    public int takeBonusNumber(){
        System.out.println("보너스 번호를 입력해주세요.");
        return inputDecimal();
    }

    //todo: 예외처리 메세지 작성시 try catch 추가
    private int inputDecimal() {
        return Integer.parseInt(scanner.nextLine());
    }

    private List<Integer> inputToIntegerList() {
        String input = scanner.nextLine();
        List<String> splitNumbers = Arrays.asList(input.split(COMMA));

        return stringsToIntegers(splitNumbers);
    }

    private List<Integer> stringsToIntegers(List<String> strings) {
        try {
            return strings.stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}

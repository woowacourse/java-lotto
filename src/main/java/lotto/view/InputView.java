package lotto.view;

import lotto.util.Spliter;

import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<Integer> inputManualLottoNumbers() {
        System.out.println("수동으로 구매할 번호를 입력해주세요.");
        return inputLottoNumbers();
    }

    private static List<Integer> inputLottoNumbers() {
        String input = SCANNER.nextLine();
        return Spliter.splitInput(input);
    }
}

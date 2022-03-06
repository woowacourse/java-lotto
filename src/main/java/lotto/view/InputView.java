package lotto.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.utils.IntegerUtils;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    private static class InputViewHelper {
        private static final InputView INSTANCE = new InputView();
    }

    public static InputView getInstance() {
        return InputViewHelper.INSTANCE;
    }

    private String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private List<String> input(String prompt, int repeat) {
        System.out.print(prompt);
        return IntStream.range(0, repeat)
            .mapToObj(it -> scanner.nextLine())
            .collect(Collectors.toList());
    }

    public String inputMoney() {
        return input("구입금액을 입력해 주세요.\n");
    }

    public List<String> inputLottoLines() {
        int lottoCount = IntegerUtils.parse(input("수동으로 구매할 로또 수를 입력해 주세요.\n"));
        return input("수동으로 구매할 번호를 입력해 주세요.\n", lottoCount);
    }

    public String inputWinningNumber() {
        return input("지난 주 당첨 번호를 입력해 주세요.\n");
    }

    public String inputBonusBall() {
        return input("보너스 볼을 입력해 주세요.\n");
    }
}

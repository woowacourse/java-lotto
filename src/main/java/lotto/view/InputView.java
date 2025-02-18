package lotto.view;

import java.util.List;
import java.util.Scanner;
import lotto.common.utill.InputParser;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int readMoneyAmount() {
        String read = read("구입금액을 입력해주세요.");
        return InputParser.parseToInt(read);
    }

    public List<Integer> readWinningNumbers() {
        String read = read("지난 주 당첨 번호를 입력해 주세요.");
        return InputParser.parseToList(read);
    }

    public int readBonusNumber() {
        String read = read("보너스 번호를 입력해주세요.");
        return InputParser.parseToInt(read);
    }

    private String read(String prompt) {
        System.out.println(prompt);;
        return scanner.nextLine();
    }

}

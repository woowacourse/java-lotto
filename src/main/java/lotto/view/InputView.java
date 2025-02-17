package lotto.view;

import java.util.Scanner;
import lotto.common.utill.InputParser;

public class InputView implements AutoCloseable {
    private Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public int readMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        String input = scanner.nextLine();

        return InputParser.parseToInt(input);
    }
    public String read(String prompt) {
        System.out.println(prompt);;
        return scanner.nextLine();
    }

    @Override
    public void close() throws Exception {
        this.scanner.close();
    }
}

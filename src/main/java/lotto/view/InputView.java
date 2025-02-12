package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private Scanner scanner = new Scanner(System.in);

    public int readPayment() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값을 입력할 수 없습니다.");
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자가 아닌 값을 입력할 수 없습니다.");
        }
    }

    public List<Integer> readWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String input = scanner.nextLine();
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값을 입력할 수 없습니다.");
        }

        String[] split = input.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (String data : split) {
            try {
                int number = Integer.parseInt(data);
                numbers.add(number);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 숫자가 아닌 값을 입력할 수 없습니다.");
            }
        }
        return numbers;
    }

    public int readBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값을 입력할 수 없습니다.");
        }

        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자가 아닌 값을 입력할 수 없습니다.");
        }
    }
}

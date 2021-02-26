package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int getGameMoney() {
        try {
            final String userInput = scanner.nextLine();
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            OutputView.printErrorMessage("1000 이상의 자연수만 입력 가능합니다.");
            return getGameMoney();
        }
    }

    public static int getManualLottoNumber() {
        try {
            final String userInput = scanner.nextLine();
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            OutputView.printErrorMessage("자연수만 입력 가능합니다.");
            return getManualLottoNumber();
        }
    }

    public static List<Integer> getWinningLotto() {
        try {
            final String userInput = scanner.nextLine();
            return Arrays.stream(userInput.split(","))
                    .map(number -> Integer.parseInt(number.trim()))
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            OutputView.printErrorMessage(", 를 사용하여 구분되게 입력해 주세요");
            return getWinningLotto();
        }
    }

    public static int getBonusBall() {
        try {
            final String userInput = scanner.nextLine();
            return Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            OutputView.printErrorMessage("자연수를 입력해 주세요.");
            return getBonusBall();
        }
    }

    public static List<List<Integer>> getManualLotto(final int manualLottoAmount) {
        try {
            List<List<Integer>> manualLottoNumber = new ArrayList<>();
            for (int i = 0; i < manualLottoAmount; i++) {
                final String userInput = scanner.nextLine();
                manualLottoNumber.add(Arrays.stream(userInput.split(","))
                        .map(number -> Integer.parseInt(number.trim()))
                        .collect(Collectors.toList()));
            }
            return manualLottoNumber;
        } catch (NumberFormatException e) {
            OutputView.printErrorMessage(", 를 사용하여 구분되게 입력해 주세요");
            return getManualLotto(manualLottoAmount);
        }
    }
}

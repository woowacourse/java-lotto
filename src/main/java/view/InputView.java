package view;

import domain.Lotto;
import domain.LottoBall;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public static Lotto getWinningLotto() {
        try {
            final String userInput = scanner.nextLine();
            final String[] splitInput = userInput.split(",");
            List<LottoBall> lottoBalls = new ArrayList<>();
            Arrays.stream(splitInput)
                    .forEach( num -> lottoBalls.add(LottoBall.valueOf(Integer.parseInt(num)) ));
            return new Lotto(lottoBalls);
        } catch (Exception e) {
            OutputView.printErrorMessage("똑바로 입력 좀 해라");
            return getWinningLotto();
        }
    }

    public static LottoBall getBonusBall() {
        try {
            final String userInput = scanner.nextLine();
            final LottoBall lottoBall = LottoBall.valueOf(Integer.parseInt(userInput));
            return lottoBall;
        } catch (Exception e) {
            OutputView.printErrorMessage("제발 똑바로 입력해줘");
            return getBonusBall();
        }
    }
}

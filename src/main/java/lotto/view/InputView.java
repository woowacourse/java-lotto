package lotto.view;

import lotto.dto.request.PaymentRequest;
import lotto.dto.request.WinningBallsRequest;

import java.util.Scanner;

public class InputView {
    private static final String PAYMENT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner = new Scanner(System.in);

    public PaymentRequest readPayment() {
        System.out.println(PAYMENT_MESSAGE);
        return PaymentRequest.from(scanner.nextLine());
    }

    public WinningBallsRequest readWinningBalls() {
        return WinningBallsRequest.of(readWinningNumbers(), readBonusNumber());
    }

    private String readWinningNumbers() {
        System.out.println(WINNING_NUMBER_MESSAGE);
        return scanner.nextLine();
    }

    private String readBonusNumber() {
        System.out.println(BONUS_NUMBER_MESSAGE);
        return scanner.nextLine();
    }
}

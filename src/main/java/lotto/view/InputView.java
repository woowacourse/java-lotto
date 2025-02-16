package lotto.view;

import static lotto.view.OutputMessage.*;

import java.util.Scanner;

import lotto.dto.request.PaymentRequest;
import lotto.dto.request.WinningBallsRequest;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public PaymentRequest readPayment() {
        System.out.println(PAYMENT_MESSAGE.getMessage());
        return PaymentRequest.from(scanner.nextLine());
    }

    public WinningBallsRequest readWinningBalls() {
        return WinningBallsRequest.of(readWinningNumbers(), readBonusNumber());
    }

    private String readWinningNumbers() {
        System.out.println(WINNING_NUMBER_MESSAGE.getMessage());
        return scanner.nextLine();
    }

    private String readBonusNumber() {
        System.out.println(BONUS_NUMBER_MESSAGE.getMessage());
        return scanner.nextLine();
    }
}

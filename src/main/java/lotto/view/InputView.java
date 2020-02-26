package lotto.view;

import lotto.domain.LottoBall;
import lotto.domain.LottoTicket;
import lotto.domain.TicketingCount;
import lotto.domain.Money;
import lotto.util.InputValidationUtil;

import java.util.List;
import java.util.Scanner;

public class InputView {
    static Scanner scanner = new Scanner(System.in);

    public static Money inputPurchaseAmount() {
        try {
            String purchaseAmountInput = scanner.nextLine();
            return new Money(purchaseAmountInput);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPurchaseAmount();
        }
    }

    public static TicketingCount inputManualTicketingCount(TicketingCount ticketingCount){
        try{
            String manualTicketingCountInput = scanner.nextLine();
            TicketingCount manualTicketingCount = new TicketingCount(manualTicketingCountInput);
            InputValidationUtil.validateOverTicketingCount(ticketingCount,manualTicketingCount);
            return manualTicketingCount;
        }
        catch (RuntimeException e){
            OutputView.printErrorMessage(e.getMessage());
            return inputManualTicketingCount(ticketingCount);
        }
    }

    public static List<LottoBall> InputWinningTicket() {
        try {
            OutputView.printAnswerWinningBalls();
            LottoTicket winningTicketFactory = generateLottoTicket();
            return winningTicketFactory.getLottoTicket();
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return InputWinningTicket();
        }
    }

    public static List<LottoBall> InputManualLottoTicket(){
        try{
            OutputView.printManualLottoTicketing();
            LottoTicket manualLottoTicket = generateLottoTicket();
            return manualLottoTicket.getLottoTicket();
        }catch(RuntimeException e){
            OutputView.printErrorMessage(e.getMessage());
            return InputManualLottoTicket();
        }
    }

    private static LottoTicket generateLottoTicket() {
        String winningTicket = scanner.nextLine();
        return new LottoTicket(winningTicket);
    }

    public static int InputBonusBall() {
        try {
            OutputView.printAnswerBonusBall();
            String bonusBall = scanner.nextLine();
            return InputValidationUtil.returnNumberWithNumberCheck(bonusBall);
        } catch (RuntimeException e) {
            OutputView.printErrorMessage(e.getMessage());
            return InputBonusBall();
        }
    }
}

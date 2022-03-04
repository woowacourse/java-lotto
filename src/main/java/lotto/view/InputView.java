package lotto.view;

import static lotto.view.OutputView.NEW_LINE;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lotto.utils.IntegerUtils;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = scanner.nextLine();
        return IntegerUtils.parse(input);
    }

    public String inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public int inputBonusBall() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String input = scanner.nextLine();
        return IntegerUtils.parse(input);
    }

    public int inputManualLottoCount() {
        System.out.println(NEW_LINE + "수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();
        return IntegerUtils.parse(input);
    }
    
    public List<String> inputManualLottoTickets(int manualCount) {
        System.out.println(NEW_LINE + "수동으로 구매할 번호를 입력해 주세요.");
        return getManualLottoTickets(manualCount);
    }

    private List<String> getManualLottoTickets(int manualCount) {
        List<String> lottoTickets = new ArrayList<>();
        for (int lottoCount = manualCount; lottoCount > 0; lottoCount--) {
            lottoTickets.add(scanner.nextLine());
        }
        return lottoTickets;
    }
}

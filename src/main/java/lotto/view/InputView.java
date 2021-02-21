package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCAANNER = new Scanner(System.in);
    public static final String DELIMITER = ", ";

    public static Money askMoney() {
        OutputView.printMessage("구입 금액을 입력해 주세요.");
        String money = SCAANNER.nextLine();
        try {
            return new Money(money);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askMoney();
        }
    }

    public static LottoAmount askLottoAmount(Money money) {
        OutputView.printMessage(System.lineSeparator() + "수동으로 구매할 로또 수를 입력해 주세요.");
        String manualAmount = SCAANNER.nextLine();

        try {
            return new LottoAmount(money, manualAmount);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askLottoAmount(money);
        }
    }

    public static Lottos askManualLottoNumbers(int manualAmount) {
        OutputView.printMessage(System.lineSeparator() + "수동으로 구매할 번호를 입력해 주세요.");

        try {
            return new Lottos(makeManualLottos(manualAmount));
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askManualLottoNumbers(manualAmount);
        }
    }

    private static List<Lotto> makeManualLottos(int manualAmount) {
        List<Lotto> manualLottos = new ArrayList<>();
        for (int i = 0; i < manualAmount; i++) {
            Lotto manualLotto = new Lotto(makeWinningLottoNumbers(SCAANNER.nextLine()));
            manualLottos.add(manualLotto);
        }
        return manualLottos;
    }

    public static List<LottoNumber> askLastWinningLottoNumber() {
        OutputView.printMessage("지난 주 당첨 번호를 입력해 주세요.");
        String input = SCAANNER.nextLine();

        try {
            return makeWinningLottoNumbers(input);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askLastWinningLottoNumber();
        }
    }

    private static List<LottoNumber> makeWinningLottoNumbers(String input) {
        List<String> splitNumbers = Arrays.asList(input.split(DELIMITER));

        return splitNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }


    public static LottoNumber askBonusNumber() {
        OutputView.printMessage("보너스 볼을 입력해 주세요.");
        String bonusNumber = SCAANNER.nextLine();

        try {
            return new LottoNumber(bonusNumber);
        } catch (Exception e) {
            OutputView.printError(e.getMessage());
            return askBonusNumber();
        }
    }
}

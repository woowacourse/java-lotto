package lotto.view;

import lotto.domain.LottoSize;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getPayment() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getManualLottoSize() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static Optional<ArrayList<String>> getManualLottoNumbers(LottoSize manualLottoSize) {
        int lottoSize = manualLottoSize.getLottoSize();
        if (lottoSize == 0) {
            return Optional.empty();
        }

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        ArrayList<String> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < lottoSize; i++) {
            lottoNumbers.add(scanner.nextLine());
        }
        return Optional.of(lottoNumbers);
    }

    public static String getWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}

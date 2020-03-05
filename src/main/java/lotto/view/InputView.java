package lotto.view;

import lotto.domain.LottoSize;

import java.util.*;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DELIMITER = ",";

    public static String getPayment() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String getManualLottoSize() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<List<String>> getManualLottoNumbers(LottoSize manualLottoSize) {
        int lottoSize = manualLottoSize.getLottoSize();
        List<List<String>> lottoNumbers = new ArrayList<>();
        if (lottoSize == 0) {
            return lottoNumbers;
        }

        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        for (int i = 0; i < lottoSize; i++) {
            lottoNumbers.add(Arrays.asList(scanner.nextLine().split(DELIMITER)));
        }
        return lottoNumbers;
    }

    public static List<String> getWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return Arrays.asList(scanner.nextLine().split(DELIMITER));
    }

    public static String getBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}

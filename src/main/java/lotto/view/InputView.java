package lotto.view;

import lotto.domain.lottoTicket.Lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String INPUT_PRICE = "구입 금액을 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_NUMBER = "수동으로 구매할 번호를 입력해 주세요.";

    public static int userPrice() {
        System.out.println(INPUT_PRICE);
        String price = scanner.nextLine();
        return convertNumber(price);
    }

    public static Integer purchaseManualLotto() {
        System.out.println(INPUT_MANUAL_LOTTO_COUNT);
        String purchaseLotto = scanner.nextLine();
        return convertNumber(purchaseLotto);
    }

    public static List<Lotto> manualLottoNumber(int count) {
        System.out.println(INPUT_MANUAL_LOTTO_NUMBER);
        List<Lotto> convertLottoNumbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            List<Integer> userNumbers = convertLottoNumbers(scanner.nextLine());
            convertLottoNumbers.add(new Lotto(userNumbers));
        }
        return convertLottoNumbers;
    }

    public static List<Integer> winningLotto() {
        System.out.println(INPUT_WINNING_LOTTO);
        String winningLottoNumbers = scanner.nextLine();
        return convertLottoNumbers(winningLottoNumbers);
    }

    public static Integer bonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER);
        String bonusNumber = scanner.nextLine();
        return convertNumber(bonusNumber);
    }

    private static List<Integer> convertLottoNumbers(String userNumbers) {
        String lottoNumbers = userNumbers.replaceAll(" ", "");
        List<String> splitLottoNumbers = Arrays.asList(lottoNumbers.split(","));
        List<Integer> convertLottoNumbers = new ArrayList<>();
        splitLottoNumbers.stream()
                .forEach(number -> convertLottoNumbers.add(convertNumber(number)));
        return convertLottoNumbers;
    }

    private static Integer convertNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("숫자만 입력이 가능합니다.");
        }
    }
}

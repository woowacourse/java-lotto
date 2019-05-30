package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoNumber;
import lotto.domain.exception.LottoNumberCreateException;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    private static String DELIMITER = ",";
    private static String BLANK = "";
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {

    }

    public static int inputBuyMoney() {
        System.out.println("구입금액을 입력해주세요");
        return inputNumber();
    }

    public static int inputManualPurchaseCount() {
        System.out.println("수동으로 구매할 로또 개수를 입력하세요");
        return inputNumber();
    }

    public static LottoNumber inputBonusLottoNumber() {
        System.out.println("보너스 볼 번호를 입력하세요");
        int bonusNum = inputNumber();
        try {
            return LottoNumber.of(bonusNum);
        } catch (LottoNumberCreateException e) {
            System.out.println(e.getMessage());
            return inputBonusLottoNumber();
        }
    }

    private static int inputNumber() {
        try {
            return Integer.parseInt(input());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력하세요");
            return inputNumber();
        }
    }

    public static List<Integer> inputManualNumbers() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요. ex) 1, 2, 3, 4, 5, 6");
        return inputNumbers();
    }

    public static Lotto inputWinningLotto() {
        System.out.println("지난 주 당첨번호들을 입력해 주세요.");
        List<Integer> lottoNumbers = inputNumbers();
        LottoFactory lottoFactory = new LottoFactory();
        return lottoFactory.create(lottoNumbers);
    }

    private static List<Integer> inputNumbers() {
        String input = input();
        List<String> strings = Arrays.asList(input.split(DELIMITER));
        return strings.stream()
                .map(String::trim)
                .map(Integer::new)
                .collect(Collectors.toList());
    }

    private static String input() {
        Optional<String> optInput = Optional.ofNullable(SCANNER.nextLine());
        return optInput.orElse(BLANK);
    }
}

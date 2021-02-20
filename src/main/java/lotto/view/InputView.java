package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.LottoCount;
import lotto.domain.LottoNumber;
import lotto.domain.PayAmount;

public class InputView {

    private static final String PAY_AMOUNT_QUESTION = "구입금액을 입력해 주세요.";
    private static final String WINNING_LOTTO_NUMBER_QUESTION = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_LOTTO_NUMBER_QUESTION = "보너스 볼을 입력해 주세요.";
    private static final String DELIMITER = ",";
    private static final String MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해주세요.";

    private final Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public PayAmount readPayAmount() {
        String input = read(PAY_AMOUNT_QUESTION);
        System.out.println();
        return PayAmount.from(input);
    }

    public List<Integer> readWinningLotto() {
         return readWinningLottoExceptBonusNumber();
    }

    private List<Integer> readWinningLottoExceptBonusNumber() {
        String input = read(WINNING_LOTTO_NUMBER_QUESTION);
        String[] lottoNumbers = input.split(DELIMITER);
        return Arrays.stream(lottoNumbers)
                     .map(number -> Integer.parseInt(number.trim()))
                     .map(number -> LottoNumber.from(number).getLottoNum())
                     .collect(Collectors.toList());
    }

    public LottoNumber readBonusNumber() {
        String input = read(BONUS_LOTTO_NUMBER_QUESTION);
        return LottoNumber.from(input);
    }

    public LottoCount readManualLottoCount() {
        String input = read(MANUAL_LOTTO_COUNT);
        int lottoCount = Integer.parseInt(input.trim());
        System.out.println();
        return new LottoCount(lottoCount);
    }

    private String read(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

}

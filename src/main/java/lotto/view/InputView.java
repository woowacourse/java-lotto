package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.UserPurchase;
import lotto.domain.WinningLottoNumbers;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static UserPurchase getUserPurchase() {
        System.out.println("구입금액을 입력해 주세요.");
        String purchasePriceInput = scanner.nextLine();

        int purchasePrice = Integer.parseInt(purchasePriceInput);
        return new UserPurchase(purchasePrice);
    }

    public static WinningLottoNumbers getWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningNumbersInput = scanner.nextLine();

        List<LottoNumber> lottoNumbers = Arrays.stream(winningNumbersInput.split(", "))
            .map(inputNumber -> new LottoNumber(Integer.parseInt(inputNumber)))
            .collect(Collectors.toList());

        LottoTicket lottoTicket = new LottoTicket(lottoNumbers);

        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusNumberInput = scanner.nextLine();
        System.out.println();

        LottoNumber bonusNumber = new LottoNumber(Integer.parseInt(bonusNumberInput));

        return new WinningLottoNumbers(lottoTicket, bonusNumber);
    }
}

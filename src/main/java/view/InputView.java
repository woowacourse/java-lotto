package view;

import domain.Lotto;
import domain.WinningLotto;
import domain.vo.Money;
import domain.vo.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    public Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return Money.of(readLine());
    }

    public WinningLotto inputWinningLotto() {
        Lotto winningNumbers = inputWinningNumbers();
        LottoNumber bonusNumber = inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }

    private Lotto inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningRawInput = readLine();
        List<Integer> winningNumbers
                = Arrays.stream(winningRawInput.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
        return new Lotto(winningNumbers);
    }

    private LottoNumber inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusRawInput = readLine();
        return LottoNumber.from(bonusRawInput);
    }

    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}

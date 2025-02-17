package view;

import domain.Lotto;
import domain.Money;
import domain.LottoNumber;
import domain.WinningLotto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private final BufferedReader bufferedReader;

    public InputView() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Money inputMoney() throws IOException {
        System.out.println("구입금액을 입력해 주세요.");
        return Money.of(bufferedReader.readLine());
    }

    public WinningLotto inputWinningLotto() throws IOException {
        Lotto winningNumbers = inputWinningNumbers();
        LottoNumber bonusLottoNumber = inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusLottoNumber);
    }

    private Lotto inputWinningNumbers() throws IOException {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningRawInput = bufferedReader.readLine();
        List<Integer> winningNumbers
                = Arrays.stream(winningRawInput.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .toList();
        return new Lotto(winningNumbers);
    }

    private LottoNumber inputBonusNumber() throws IOException {
        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusRawInput = bufferedReader.readLine();
        return LottoNumber.from(bonusRawInput);
    }

}

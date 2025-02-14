import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class InputView {
    private BufferedReader bufferedReader;

    public InputView() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public Money inputMoney() throws IOException {
        System.out.println("구입금액을 입력해 주세요.");
        return Money.of(bufferedReader.readLine());
    }

    public WinningLotto inputWinningLotto() throws IOException {
        Lotto winningNumbers = inputWinningNumbers();
        Number bonusNumber = inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
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

    private Number inputBonusNumber() throws IOException {
        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusRawInput = bufferedReader.readLine();
        return Number.from(bonusRawInput);
    }
}

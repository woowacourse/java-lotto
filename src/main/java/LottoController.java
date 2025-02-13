import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LottoController {

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("구입금액을 입력해 주세요.");
        Price price = new Price(bufferedReader.readLine());
        LottoMachine lottoMachine = new LottoMachine(price);

        int ticket = lottoMachine.getTicket();
        System.out.println(ticket + "개를 구매했습니다.");

        Lottos lottos = lottoMachine.generateLotto();
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.toString());
        }

        WinningNumberWithBonusNumber winningNumberWithBonusNumber
                = new WinningNumberWithBonusNumber(getWinningNumber(bufferedReader), getBonusNumber(bufferedReader));

        LottoResult lottoResult = LottoWinningChecker.calculateResult(lottos, winningNumberWithBonusNumber);
        System.out.println(lottoResult.result().toString());


    }

    private static Lotto getWinningNumber(BufferedReader bufferedReader) throws IOException {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return new Lotto(Arrays.stream(bufferedReader.readLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    private static int getBonusNumber(BufferedReader bufferedReader) throws IOException {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(bufferedReader.readLine());
    }
}

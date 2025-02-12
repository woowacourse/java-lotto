import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LottoController {

    public void run() throws IOException {
        int money = inputMoney();
        int lottoCount = getLottoCount(money);
        printLottoCount(lottoCount);

        List<Lotto> lottos = new ArrayList<>();
        for(int i = 0; i < lottoCount; ++i) {
            lottos.add(new Lotto(RandomNumbersGenerator.generateUniqueNumbers(1, 45, 6)));
        }

        printLottos(lottos);
    }

    private int inputMoney() throws IOException {
        System.out.println("구입금액을 입력해 주세요.");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String moneyRawInput = bufferedReader.readLine();
        return Integer.parseInt(moneyRawInput);
    }

    private int getLottoCount(int money) {
        return money / 1000;
    }

    private void printLottoCount(int count) {
        System.out.printf("%d개를 구매했습니다.\n", count);
    }

    private void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getInfo());
        }
    }
}

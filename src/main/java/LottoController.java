import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() throws IOException {
        Money money = inputMoney();
        int lottoCount = getLottoCount(money);
        printLottoCount(lottoCount);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; ++i) {
            lottos.add(new Lotto(RandomNumbersGenerator.generateUniqueNumbers(1, 45, 6)));
        }
        printLottos(lottos);

        inputWinningNumbers();
    }

    private WinningLotto inputWinningNumbers() throws IOException {
        return inputView.inputWinningLotto();
    }

    private Money inputMoney() throws IOException {
        Money money = inputView.inputMoney();
        return money;
    }

    private int getLottoCount(Money money) {
        return money.getValue() / 1000;
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

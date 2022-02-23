import java.util.List;

public class LottoMachine {

    public void start() {

        Money money = new Money(InputView.askInputMoney());
        int count = money.generateCount();
        OutputView.printCountOfLotto(count);
        Lottos lottos = new Lottos();
        lottos.generateLottos(count);
        OutputView.printLottos(lottos);
        List<Integer> winningNumber = InputView.askInputWinningNumber();
    }
}

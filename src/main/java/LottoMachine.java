public class LottoMachine {

    public void start() {

        Money money = new Money(InputView.askInputMoney());
        OutputView.printCountOfLotto(money.generateCount());

    }
}

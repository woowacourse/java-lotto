package lotto;

import lotto.model.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoLauncher {

    public static void main(String[] args) {
        //1
        Money money = generateMoney();

        //2
        ManualLottoCount manualLottoCount = generateManualLottoCount(money);

        //3
        LottoMachine manualLottoMachine = new ManualLottoMachine(manualLottoCount);
        Lottos manualLottos = generateLottos(manualLottoMachine);
        LottoMachine automaticLottoMachine = new AutomaticLottoMachine(money, manualLottoCount);
        Lottos automaticLottos = generateLottos(automaticLottoMachine);

        //4
        OutputView.printLottos(manualLottos, automaticLottos);

        //5
        WinningLotto winningLotto = generateWinningLotto();

        Lottos totalLottos = manualLottos.append(automaticLottos);
        LottoResult lottoResult = new LottoResult(money, totalLottos.getPrizes(winningLotto));

        OutputView.printStatistics(lottoResult);
    }

    private static WinningLotto generateWinningLotto() {
        try {
            Lotto winningLottoTicket = new Lotto(InputView.askWinningLottoNumbers());
            LottoNumber bonusNumber = new LottoNumber(InputView.askBonusNumber());
            return new WinningLotto(winningLottoTicket, bonusNumber);
        } catch (Exception e) {
            System.out.println("잘못된 번호 입력이 있습니다!");
            return generateWinningLotto();
        }
    }

    private static Lottos generateLottos(LottoMachine machine) {
        try {
            return machine.generateLottos();
        } catch (Exception e) {
            System.out.println("잘못된 입력이 있습니다 처음부터 다시 입력하세요!");
            return generateLottos(machine);
        }
    }

    private static ManualLottoCount generateManualLottoCount(Money money) {
        try {
            int input = InputView.askManualLottoCount();
            return new ManualLottoCount(input, money);
        } catch (Exception e) {
            return generateManualLottoCount(money);
        }
    }

    private static Money generateMoney() {
        try {
            String userInput = InputView.askMoney();
            return new Money(userInput);
        } catch (Exception e) {
            System.out.println("잘못된 구입 금액입니다");
            return generateMoney();
        }
    }

    public static List<Integer> getManualLottoNumbers() {
        return InputView.askManualLottoNumbers();
    }

}

package lotto;

import lotto.domain.LottoMoney;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        try {
            int inputMoney = InputView.receiveLottoMoney();
            LottoMoney lottoMoney = new LottoMoney(inputMoney);

            List<String> manualLottos = InputView.receiveManualLotto();

            Lottos totalLottos = new Lottos(manualLottos, lottoMoney.getCountOfTicket());
            OutputView.printLottos(totalLottos, manualLottos.size());

            String winningLottoInput = InputView.receiveWinning();
            int bonusBall = InputView.receiveBonus();
            WinningLotto winningLotto = new WinningLotto(winningLottoInput, bonusBall);


            LottoResult lottoResult = new LottoResult(totalLottos, winningLotto);
            OutputView.printLottoResult(lottoResult);
        } catch (NumberFormatException e1) {
            System.out.println("숫자만 입력해주세요.");
        } catch (IllegalArgumentException e2) {
            System.out.println(e2.getMessage());
        }

    }
}

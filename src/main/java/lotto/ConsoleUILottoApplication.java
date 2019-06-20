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
            long inputMoney = InputView.receiveLottoMoney();
            LottoMoney lottoMoney = new LottoMoney(inputMoney);
            OutputView.printRemainMoney(lottoMoney.getRemainMoney());

            List<String> manualLottos = InputView.receiveManualLotto();

            Lottos totalLottos = new Lottos(manualLottos, lottoMoney.getCountOfTicket());
            OutputView.printLottos(totalLottos, manualLottos.size());

            String winningLottoInput = InputView.receiveWinning();
            int bonusBall = InputView.receiveBonus();
            WinningLotto winningLotto = new WinningLotto(winningLottoInput, bonusBall);

            LottoResult lottoResult = new LottoResult(totalLottos, winningLotto);
            OutputView.printLottoResult(lottoResult);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}

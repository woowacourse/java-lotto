package lotto;

import lotto.domain.*;
import lotto.utils.LottoFactory;
import lotto.utils.NumberGenerator;
import lotto.utils.UserInputNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        NumberGenerator numberGenerator = new UserInputNumberGenerator();
        Payment payment = new Payment(InputView.getPayment());
        OutputView.printLottoCount(payment);

        Lottos lottos = new Lottos(LottoFactory.createLottoList(payment));

        List<Lotto> lottoList = lottos.getLottos();
        for (int i = 0; i < lottoList.size(); i++) {
            System.out.println(lottoList.get(i).getLottoNumbers().toString());
        }

        WinningLotto winningLotto = new WinningLotto(numberGenerator.generateNumbers(InputView.getWinningLottoNumber()), new LottoNumber(InputView.getBonusNumber()));

        OutputView.printResults(new Results(lottoList, winningLotto));
    }
}

package lotto;

import lotto.domain.*;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        Payment payment = new Payment(5000);
        int countOfManualLotto = 2;

        LottoRepository lottoRepository = new LottoRepository();
        // 수동 로또 생성
        for (int i = 0; i < countOfManualLotto; i++) {
            List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 7);
            lottoRepository.register(new ManualLottoGeneratingStrategy(input));
        }

        // random lotto 생성
        for (int i = 0; i < 5 - countOfManualLotto; i++) {
            lottoRepository.register(new RandomLottoGeneratingStrategy());
        }

        LottoTickets lottoTickets = new LottoTickets(lottoRepository);

        // winning Lotto 입력
        WinningLotto winningLotto = new WinningLotto(new Lotto(Arrays.asList(
                LottoNumber.getNumber(1),
                LottoNumber.getNumber(2),
                LottoNumber.getNumber(3),
                LottoNumber.getNumber(4),
                LottoNumber.getNumber(5),
                LottoNumber.getNumber(6))),
                new BonusNumber(7));

        OutputView.printLottoTickets(lottoTickets);

        Result result = winningLotto.produceResult(lottoTickets);

        OutputView.printResult(result, payment);
    }
}

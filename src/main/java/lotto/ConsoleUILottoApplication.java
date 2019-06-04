package lotto;

import lotto.domain.*;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        Payment payment = inputPayment();
        CountOfLotto countOfLotto = inputCountOfManualLotto(payment);

        LottoRepository lottoRepository = new LottoRepository();
        for (int i = 0; i < countOfLotto.getCountOfManualLotto(); i++) {
            inputLottoNumber(lottoRepository);
        }

        for (int i = 0; i < countOfLotto.getCountOfRandomLotto(); i++) {
            lottoRepository.register(new RandomLottoGeneratingStrategy());
        }

        LottoTickets lottoTickets = new LottoTickets(lottoRepository);
        OutputView.printLotto(countOfLotto, lottoTickets);

        WinningLotto winningLotto = inputWinningLotto();

        Result result = winningLotto.produceResult(lottoTickets);
        OutputView.printResult(result, payment);
    }

    public static Payment inputPayment() {
        try {
            String input = InputView.inputPayment();
            return new Payment(Integer.parseInt(input));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return inputPayment();
        }
    }

    public static CountOfLotto inputCountOfManualLotto(Payment payment) {
        try {
            String input = InputView.inputCountOfManualLotto();
            return new CountOfLotto(payment, Integer.parseInt(input));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return inputCountOfManualLotto(payment);
        }
    }

    public static void inputLottoNumber(LottoRepository lottoRepository) {
        try {
            String input = InputView.inputLottoNumber();
            List<Integer> list = Arrays.stream(input.split(", "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            lottoRepository.register(new ManualLottoGeneratingStrategy(list));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            inputLottoNumber(lottoRepository);
        }
    }

    public static WinningLotto inputWinningLotto() {
        try {
            String inputLotto = InputView.inputWinningLottoNumber();
            List<Integer> list = Arrays.stream(inputLotto.split(", "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(list));

            String inputBonusBall = InputView.inputBonusBall();
            return new WinningLotto(lotto, new BonusNumber(Integer.parseInt(inputBonusBall)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return inputWinningLotto();
        }

    }
}

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

        LottoRepository lottoRepository = purchaseLotto(countOfLotto);
        LottoTickets lottoTickets = new LottoTickets(lottoRepository);
        OutputView.printLotto(countOfLotto, lottoTickets);

        WinningLotto winningLotto = inputWinningLotto();

        Result result = new Result(winningLotto, lottoTickets);
        OutputView.printResult(result, payment);
    }

    private static Payment inputPayment() {
        try {
            String input = InputView.inputPayment();
            return new Payment(Integer.parseInt(input));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return inputPayment();
        }
    }

    private static CountOfLotto inputCountOfManualLotto(Payment payment) {
        try {
            String input = InputView.inputCountOfManualLotto();
            return new CountOfLotto(payment, Integer.parseInt(input));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return inputCountOfManualLotto(payment);
        }
    }

    private static LottoRepository purchaseLotto(CountOfLotto countOfLotto) {
        LottoRepository lottoRepository = new LottoRepository();
        for (int i = 0; i < countOfLotto.getCountOfManualLotto(); i++) {
            inputLottoNumber(lottoRepository);
        }

        for (int i = 0; i < countOfLotto.getCountOfRandomLotto(); i++) {
            lottoRepository.register(new RandomLottoGeneratingStrategy());
        }
        return lottoRepository;
    }

    private static void inputLottoNumber(LottoRepository lottoRepository) {
        try {
            String input = InputView.inputLottoNumber();
            List<Integer> list = splitInputLottoNumbers(input);
            lottoRepository.register(new ManualLottoGeneratingStrategy(list));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            inputLottoNumber(lottoRepository);
        }
    }

    private static WinningLotto inputWinningLotto() {
        try {
            String inputLotto = InputView.inputWinningLottoNumber();
            List<Integer> list = splitInputLottoNumbers(inputLotto);
            Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(list));

            String inputBonusBall = InputView.inputBonusBall();
            return new WinningLotto(lotto, LottoNumber.getNumber(Integer.parseInt(inputBonusBall)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return inputWinningLotto();
        }
    }

    private static List<Integer> splitInputLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}

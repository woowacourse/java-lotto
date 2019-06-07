package lotto;

import lotto.domain.*;
import lotto.domain.lotto.*;
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

        LottoTickets lottoTickets = lottoRepository.createLottoTickets();
        OutputView.printLotto(countOfLotto, lottoTickets);

        WinningLotto winningLotto = inputWinningLotto();
        Result result = lottoTickets.match(winningLotto);
        OutputView.printResult(result, payment);
    }

    private static Payment inputPayment() {
        String input = InputView.inputPayment();

        try {
            return new Payment(input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return inputPayment();
        }
    }

    private static CountOfLotto inputCountOfManualLotto(Payment payment) {
        String input = InputView.inputCountOfManualLotto();

        try {
            return new CountOfLotto(payment, input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return inputCountOfManualLotto(payment);
        }
    }

    private static void inputLottoNumber(LottoRepository lottoRepository) {
        String input = InputView.inputLottoNumber();
        List<Integer> list = splitInputLottoNumbers(input);

        try {
            lottoRepository.register(new ManualLottoGeneratingStrategy(list));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            inputLottoNumber(lottoRepository);
        }
    }

    private static WinningLotto inputWinningLotto() {
        String inputLotto = InputView.inputWinningLottoNumber();
        List<Integer> lottoNumbers = splitInputLottoNumbers(inputLotto);
        String inputBonusBall = InputView.inputBonusBall();

        try {
            Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(lottoNumbers));
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

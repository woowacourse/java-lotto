package lotto;

import lotto.domain.lotto.*;
import lotto.domain.lottogenerator.LottoGenerator;
import lotto.domain.lottogenerator.ManualLottoGeneratingStrategy;
import lotto.domain.lottogenerator.RandomLottoGeneratingStrategy;
import lotto.domain.paymentinfo.CountOfLotto;
import lotto.domain.paymentinfo.Payment;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConsoleUILottoApplication {
    public static void main(String[] args) {
        Payment payment = inputPayment();
        CountOfLotto countOfLotto = inputCountOfManualLotto(payment);

        LottoRepository lottoRepository = purchaseLotto(countOfLotto);
        LottoTickets lottoTickets = new LottoTickets(lottoRepository);
        OutputView.printLotto(lottoTickets);

        WinningLotto winningLotto = inputWinningLotto();

        Result result = winningLotto.match(lottoTickets);
        OutputView.printResult(result, result.calculateEarningsRate(payment));
    }

    private static Payment inputPayment() {
        Payment payment;
        do {
            payment = createPayment();
        } while (Objects.isNull(payment));
        return payment;
    }

    private static Payment createPayment() {
        Payment payment;
        try {
            int input = Integer.parseInt(InputView.inputPayment());
            payment = new Payment(input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            payment = null;
        }
        return payment;
    }

    private static CountOfLotto inputCountOfManualLotto(Payment payment) {
        CountOfLotto countOfLotto;
        do {
            countOfLotto = createCountOfLotto(payment);
        } while (Objects.isNull(countOfLotto));
        return countOfLotto;
    }

    private static CountOfLotto createCountOfLotto(Payment payment) {
        CountOfLotto countOfLotto;
        try {
            int input = Integer.parseInt(InputView.inputCountOfManualLotto());
            countOfLotto = new CountOfLotto(payment, input);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            countOfLotto = null;
        }
        return countOfLotto;
    }

    private static LottoRepository purchaseLotto(CountOfLotto countOfLotto) {
        LottoRepository lottoRepository = new LottoRepository();
        for (int i = 0; i < countOfLotto.getCountOfManualLotto(); i++) {
            lottoRepository.addManualLotto(inputLottoNumber());
        }

        for (int i = 0; i < countOfLotto.getCountOfRandomLotto(); i++) {
            lottoRepository.addAutoLottos(LottoGenerator.create(new RandomLottoGeneratingStrategy()));
        }
        return lottoRepository;
    }

    private static Lotto inputLottoNumber() {
        Lotto lotto;
        do {
            lotto = createLotto();
        } while (Objects.isNull(lotto));
        return lotto;
    }

    private static Lotto createLotto() {
        Lotto lotto;
        try {
            String input = InputView.inputLottoNumber();
            List<Integer> list = splitInputLottoNumbers(input);
            lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(list));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            lotto = null;
        }
        return lotto;
    }

    private static List<Integer> splitInputLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static WinningLotto inputWinningLotto() {
        WinningLotto winningLotto;
        do {
            winningLotto = createWinningLotto();
        } while (Objects.isNull(winningLotto));
        return winningLotto;
    }

    private static WinningLotto createWinningLotto() {
        WinningLotto winningLotto;
        try {
            String inputLotto = InputView.inputWinningLottoNumber();
            List<Integer> list = splitInputLottoNumbers(inputLotto);
            Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(list));

            String inputBonusBall = InputView.inputBonusBall();
            winningLotto = new WinningLotto(lotto, Integer.parseInt(inputBonusBall));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            winningLotto = null;
        }
        return winningLotto;
    }
}

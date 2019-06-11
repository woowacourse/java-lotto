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
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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
        Optional<Payment> payment;
        do {
            payment = createPayment();
        } while (isAbsent(payment));

        return payment.get();
    }

    private static boolean isAbsent(Optional<?> optional) {
        return !optional.isPresent();
    }

    private static Optional<Payment> createPayment() {
        Optional<Payment> payment;
        try {
            int input = Integer.parseInt(InputView.inputPayment());
            payment = Optional.of(new Payment(input));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            payment = Optional.empty();
        }

        return payment;
    }

    private static CountOfLotto inputCountOfManualLotto(Payment payment) {
        Optional<CountOfLotto> countOfLotto;
        do {
            countOfLotto = createCountOfLotto(payment);
        } while (isAbsent(countOfLotto));

        return countOfLotto.get();
    }

    private static Optional<CountOfLotto> createCountOfLotto(Payment payment) {
        Optional<CountOfLotto> countOfLotto;
        try {
            int input = Integer.parseInt(InputView.inputCountOfManualLotto());
            countOfLotto = Optional.of(new CountOfLotto(payment, input));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            countOfLotto = Optional.empty();
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
        Optional<Lotto> lotto;
        do {
            lotto = createLotto();
        } while (isAbsent(lotto));

        return lotto.get();
    }

    private static Optional<Lotto> createLotto() {
        Optional<Lotto> lotto;
        try {
            String input = InputView.inputLottoNumber();
            List<Integer> list = splitInputLottoNumbers(input);
            lotto = Optional.of(LottoGenerator.create(new ManualLottoGeneratingStrategy(list)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            lotto = Optional.empty();
        }

        return lotto;
    }

    private static List<Integer> splitInputLottoNumbers(String input) {
        return Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(toList());
    }

    private static WinningLotto inputWinningLotto() {
        Optional<WinningLotto> winningLotto;
        do {
            winningLotto = createWinningLotto();
        } while (isAbsent(winningLotto));

        return winningLotto.get();
    }

    private static Optional<WinningLotto> createWinningLotto() {
        Optional<WinningLotto> winningLotto;
        try {
            String inputLotto = InputView.inputWinningLottoNumber();
            List<Integer> list = splitInputLottoNumbers(inputLotto);
            Lotto lotto = LottoGenerator.create(new ManualLottoGeneratingStrategy(list));

            String inputBonusBall = InputView.inputBonusBall();
            winningLotto = Optional.of(new WinningLotto(lotto, Integer.parseInt(inputBonusBall)));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            winningLotto = Optional.empty();
        }

        return winningLotto;
    }
}

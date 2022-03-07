package view;

import domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class InputConvertor {

    private InputConvertor() {
    }

    public static Payment createPayment() {
        try {
            return new Payment(Integer.parseInt(InputView.insertPayment()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createPayment();
        }
    }

    public static LottoOrder createManualTicketCount(Payment payment) {
        try {
            int entireLottoTicket = payment.calculateLottoCount();
            int manualTicketCount = Integer.parseInt(InputView.insertManualTicketCount());
            return new LottoOrder(payment, manualTicketCount, entireLottoTicket - manualTicketCount);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createManualTicketCount(payment);
        }
    }

    public static List<Lotto> createManualLottos(int manualLottoCount) {
        try {
            return InputView.insertManualLotto(manualLottoCount).stream()
                    .map(InputConvertor::transformToLotto)
                    .collect(Collectors.toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createManualLottos(manualLottoCount);
        }
    }

    public static WinningLotto createWinningLotto() {
        try {
            return new WinningLotto(createLotto(), createBonusNumber());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createWinningLotto();
        }
    }

    public static Lotto createLotto() {
        return transformToLotto(InputView.insertLotto());
    }

    private static Lotto transformToLotto(List<String> lottoNumbers) {
        return new Lotto(lottoNumbers.stream()
                .map(Integer::parseInt)
                .map(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableSet()));
    }

    private static LottoNumber createBonusNumber() {
        return LottoNumber.valueOf(Integer.parseInt(InputView.insertBonus()));
    }
}

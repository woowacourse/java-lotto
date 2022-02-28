package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.Payment;
import domain.WinningLotto;

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

    public static WinningLotto createWinningLotto() {
        try {
            return new WinningLotto(createLotto(), createBonusNumber());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return createWinningLotto();
        }
    }

    private static Lotto createLotto() {
        return new Lotto(InputView.insertLotto().stream()
                .map(Integer::parseInt)
                .map(LottoNumber::valueOf)
                .collect(Collectors.toUnmodifiableSet()));
    }

    private static LottoNumber createBonusNumber() {
        return LottoNumber.valueOf(Integer.parseInt(InputView.insertBonus()));
    }
}

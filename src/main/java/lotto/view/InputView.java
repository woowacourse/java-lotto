package lotto.view;

import static lotto.view.InputTemplate.repeatablyExecute;
import static lotto.view.InputTemplate.repeatablyInput;

import lotto.model.Lotto;
import lotto.model.Money;
import lotto.model.Number;
import lotto.model.WinnerLotto;

public class InputView {

    private static final StringFormatValidator MONEY_VALIDATOR = StringFormatValidator
        .moneyValidator();
    private static final StringFormatValidator LOTTO_VALIDATOR = StringFormatValidator
        .lottoValidator();
    private static final StringFormatValidator NUMBER_VALIDATOR = StringFormatValidator
        .numberValidator();
    private static final Parser<Lotto> LOTTO_CONVERTOR = new LottoParser();
    private static final Parser<Money> MONEY_CONVERTOR = Parser.moneyParser();
    private static final Parser<Number> NUMBER_CONVERTOR = Parser.numberParser();

    private InputView() {
    }

    public static Money inputMoney() {
        return repeatablyExecute(InputView::createMoney, OutputView::printErrorMessage);
    }

    private static Money createMoney() {
        return MONEY_CONVERTOR.parse(inputMoneyText());
    }

    private static String inputMoneyText() {
        return repeatablyInput("구입금액을 입력해 주세요.", MONEY_VALIDATOR::validate,
            OutputView::printErrorMessage);
    }

    public static WinnerLotto inputWinnerLotto() {
        return repeatablyExecute(InputView::createWinnerLotto, OutputView::printErrorMessage);
    }

    private static WinnerLotto createWinnerLotto() {
        return new WinnerLotto(createLotto(), createBonus());
    }

    private static Lotto createLotto() {
        return LOTTO_CONVERTOR.parse(inputLottoText());
    }

    private static String inputLottoText() {
        return repeatablyInput("지난 주 당첨 번호를 입력해 주세요.", LOTTO_VALIDATOR::validate,
                OutputView::printErrorMessage);
    }

    private static Number createBonus() {
        return NUMBER_CONVERTOR.parse(inputBonusText());
    }

    private static String inputBonusText() {
        String value = repeatablyInput("보너스 볼을 입력해 주세요.", NUMBER_VALIDATOR::validate,
            OutputView::printErrorMessage);
        return value;
    }

}

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

    public static Money createMoney() {
        return repeatablyExecute(InputView::inputMoney, OutputView::printErrorMessage);
    }

    private static Money inputMoney() {
        String value = repeatablyInput("구입금액을 입력해 주세요.", MONEY_VALIDATOR::validate,
            OutputView::printErrorMessage);
        return MONEY_CONVERTOR.parse(value);
    }

    public static WinnerLotto createWinnerLotto() {
        return repeatablyExecute(() -> new WinnerLotto(inputWinnerLotto(), inputBonus()),
            OutputView::printErrorMessage);
    }

    private static Lotto inputWinnerLotto() {
        String value = repeatablyInput("지난 주 당첨 번호를 입력해 주세요.", LOTTO_VALIDATOR::validate,
            OutputView::printErrorMessage);
        return LOTTO_CONVERTOR.parse(value);
    }

    private static Number inputBonus() {
        String value = repeatablyInput("보너스 볼을 입력해 주세요.", NUMBER_VALIDATOR::validate,
            OutputView::printErrorMessage);
        return NUMBER_CONVERTOR.parse(value);
    }

}

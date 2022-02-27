package lotto.view;

import lotto.model.Lotto;
import lotto.model.Money;
import lotto.model.LottoNumber;
import lotto.model.WinnerLotto;

public class InputView {

    private static final StringFormatValidator MONEY_VALIDATOR = StringFormatValidator
        .moneyValidator();
    private static final StringFormatValidator LOTTO_VALIDATOR = StringFormatValidator
        .lottoValidator();
    private static final StringFormatValidator NUMBER_VALIDATOR = StringFormatValidator
        .numberValidator();
    private static final Parser<Lotto> LOTTO_PARSER = new LottoParser();
    private static final Parser<Money> MONEY_PARSER = Parser.moneyParser();
    private static final Parser<LottoNumber> NUMBER_PARSER = Parser.numberParser();
    private static final InputTemplate inputTemplate = new InputTemplate(System.in, System.out);

    private InputView() {
    }

    public static Money inputMoney() {
        return inputTemplate.repeatablyExecute(InputView::createMoney, OutputView::printErrorMessage);
    }

    private static Money createMoney() {
        return MONEY_PARSER.parse(inputMoneyText());
    }

    private static String inputMoneyText() {
        return inputTemplate.repeatablyInput("구입금액을 입력해 주세요.", MONEY_VALIDATOR::validate,
            OutputView::printErrorMessage);
    }

    public static WinnerLotto inputWinnerLotto() {
        return inputTemplate.repeatablyExecute(InputView::createWinnerLotto, OutputView::printErrorMessage);
    }

    private static WinnerLotto createWinnerLotto() {
        return new WinnerLotto(createLotto(), createBonus());
    }

    private static Lotto createLotto() {
        return LOTTO_PARSER.parse(inputLottoText());
    }

    private static String inputLottoText() {
        return inputTemplate.repeatablyInput("지난 주 당첨 번호를 입력해 주세요.", LOTTO_VALIDATOR::validate,
                OutputView::printErrorMessage);
    }

    private static LottoNumber createBonus() {
        return NUMBER_PARSER.parse(inputBonusText());
    }

    private static String inputBonusText() {
        return inputTemplate.repeatablyInput("보너스 볼을 입력해 주세요.", NUMBER_VALIDATOR::validate,
            OutputView::printErrorMessage);
    }

}

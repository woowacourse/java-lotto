
import static view.InputView.inputSelectiveRepeatably;
import static view.OutputView.printIssuedLottoNumbers;
import static view.OutputView.printResult;

import java.util.List;
import model.LottoMachine;
import model.LottoNumber;
import model.LottoNumbers;
import model.LottoResult;
import model.Money;
import model.RandomLottoNumbersGenerator;
import model.WinningLottoNumbers;
import view.BonusNumberParser;
import view.LottoNumbersParser;
import view.MoneyParser;
import view.OutputView;
import view.Parser;

public class Application {

    public static final Parser<Money> MONEY_PARSER = new MoneyParser();
    public static final Parser<LottoNumbers> LOTTO_NUMBERS_PARSER = new LottoNumbersParser();
    public static final Parser<LottoNumber> BONUS_NUMBER_PARSER = new BonusNumberParser();

    public static void main(String[] args) {
        Money inputMoney = inputSelectiveRepeatably("구입금액을 입력해 주세요.", MONEY_PARSER::parse, OutputView::printErrorMessage);
        List<LottoNumbers> issuedLottoNumbers = issueLottoNumbers(inputMoney);
        printIssuedLottoNumbers(issuedLottoNumbers);
        WinningLottoNumbers winningLottoNumbers = createWinningLottoNumbers();
        LottoResult result = winningLottoNumbers.summarize(issuedLottoNumbers, inputMoney);
        printResult(result);
    }

    private static List<LottoNumbers> issueLottoNumbers(Money inputMoney) {
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumbersGenerator(1, 45));
        return lottoMachine.issueLotto(inputMoney);
    }

    private static WinningLottoNumbers createWinningLottoNumbers() {
        LottoNumbers lottoNumbers = inputSelectiveRepeatably("지난 주 당첨 번호를 입력해 주세요.",
                LOTTO_NUMBERS_PARSER::parse, OutputView::printErrorMessage);
        LottoNumber bonusNumber = inputSelectiveRepeatably("보너스 볼을 입력해 주세요.", BONUS_NUMBER_PARSER::parse,
                OutputView::printErrorMessage);
        return new WinningLottoNumbers(lottoNumbers, bonusNumber);
    }
}

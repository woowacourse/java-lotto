import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.LottoMachine;
import model.LottoNumber;
import model.LottoNumbers;
import model.LottoRank;
import model.LottoResult;
import model.Money;
import model.RandomLottoNumbersGenerator;
import model.WinningLottoNumbers;
import view.BonusNumberParser;
import view.LottoNumbersParser;
import view.MoneyParser;
import view.Parser;

public class Application {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Parser<Money> MONEY_PARSER = new MoneyParser();
    public static final Parser<LottoNumbers> LOTTO_NUMBERS_PARSER = new LottoNumbersParser();
    public static final Parser<LottoNumber> BONUS_NUMBER_PARSER = new BonusNumberParser();

    public static void main(String[] args) {
        Money inputMoney = inputWithMessage("구입금액을 입력해 주세요.", MONEY_PARSER::parse);
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumbersGenerator(1, 45));
        lottoMachine.issueLotto(inputMoney);
        printIssuedLottoNumbers(lottoMachine.getIssuedLottoNumbers());
        LottoNumbers winningLottoNumbers = inputWithMessage("지난 주 당첨 번호를 입력해 주세요.", LOTTO_NUMBERS_PARSER::parse);
        LottoNumber bonusNumber = inputWithMessage("보너스 볼을 입력해 주세요.", BONUS_NUMBER_PARSER::parse);
        lottoMachine.setWinningLottoNumbers(new WinningLottoNumbers(winningLottoNumbers, bonusNumber));
        LottoResult result = lottoMachine.summarize();

        System.out.println("당첨 통계");
        System.out.println("---------");
        printResult(result, inputMoney);
    }

    private static <T> T inputWithMessage(String message, Function<String, T> function) {
        System.out.println(message);
        return function.apply(SCANNER.nextLine());
    }

    private static void printIssuedLottoNumbers(List<LottoNumbers> lottoNumbersList) {
        System.out.println(lottoNumbersList.size() + "개를 구매했습니다.");
        for (LottoNumbers numbers : lottoNumbersList) {
            printEachLottoNumbers(numbers);
        }
    }

    private static void printEachLottoNumbers(LottoNumbers numbers) {
        String lottoNumbersText = numbers.getIntValues().stream().map(String::valueOf)
            .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(lottoNumbersText);
    }

    private static void printResult(LottoResult result, Money inputMoney) {
        System.out.println("3개 일치 (5000원)- "+result.getCountByRank(LottoRank.FIFTH)+"개");
        System.out.println("4개 일치 (50000원)- "+result.getCountByRank(LottoRank.FOURTH)+"개");
        System.out.println("5개 일치 (1500000원)- "+result.getCountByRank(LottoRank.THIRD)+"개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원)- "+result.getCountByRank(LottoRank.SECOND)+"개");
        System.out.println("6개 일치 (2000000000원)- "+result.getCountByRank(LottoRank.FIRST)+"개");
        System.out.println("총 수익률은 "+result.getTotalPrize().divide(inputMoney)+"입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
    }
}

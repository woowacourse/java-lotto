import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import model.LottoMachine;
import model.LottoNumbers;
import model.Money;
import model.RandomLottoNumbersGenerator;
import view.MoneyParser;
import view.Parser;

public class Application {

    public static final Scanner SCANNER = new Scanner(System.in);
    public static final Parser<Money> MONEY_PARSER = new MoneyParser();

    public static void main(String[] args) {
        Money inputMoney = inputWithMessage("구입금액을 입력해 주세요.", MONEY_PARSER::parse);
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumbersGenerator(1, 45));
        lottoMachine.issueLotto(inputMoney);
        printIssuedLottoNumbers(lottoMachine.getIssuedLottoNumbers());
//        inputWithMessage("지난 주 당첨 번호를 입력해 주세요.", )
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
}

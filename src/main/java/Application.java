import java.math.BigInteger;
import java.util.Scanner;
import model.LottoMachine;
import model.Money;
import model.RandomLottoNumbersGenerator;
import view.MoneyParser;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        MoneyParser moneyParser = new MoneyParser();
        Money inputMoney = moneyParser.parse(scanner.nextLine());
        LottoMachine lottoMachine = new LottoMachine(new RandomLottoNumbersGenerator(1, 45));
        lottoMachine.issueLotto(inputMoney);
        System.out.println(lottoMachine.getIssuedLottoNumbers().size() + "개를 구매했습니다.");
    }
}

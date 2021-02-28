package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.Money;
import domain.WinningLotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Money inputPurchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int money = Integer.parseInt(SCANNER.nextLine());
        return Money.createPurchasingLottoMoney(money);
    }

    public static int inputPurchasingPassiveLottoNumber() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<Lotto> inputPurchasingPassiveLottos(int numberOfLotto) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<Lotto> lottos = Stream.generate(InputView::inputLotto)
                .limit(numberOfLotto)
                .collect(Collectors.toList());
        return lottos;
    }

    public static WinningLotto inputWinningLotto() {
        System.out.println("지난 주 당첨 로또를 입력해주세요.");
        Lotto lastWinningLotto = inputLotto();

        return new WinningLotto(lastWinningLotto, inputBonusBall());
    }

    private static Lotto inputLotto() {
        String input = SCANNER.nextLine();
        List<LottoNumber> lottoNumbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }

    private static LottoNumber inputBonusBall() {
        System.out.println("보너스 볼을 입력해주세요.");
        return new LottoNumber(SCANNER.nextInt());
    }
}

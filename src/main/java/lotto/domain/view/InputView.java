package lotto.domain.view;

import lotto.domain.model.*;
import lotto.domain.model.Number;
import lotto.domain.utils.Validator;

import java.util.*;

public class InputView {
    private static final String NEW_LINE ="\n";
    private static final String MANUAL_LOTTO_SIZE_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String PURCHASE_MONEY_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBER_MESSAGE = "수동으로 구매할 번호를 입력해주세요.";
    private static final String LAST_WEEK_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final Scanner sc = new Scanner(System.in);

    /**
     *  구입 금액 입력
     */
    public static int inputMoney() {
        int money;
        try {
            System.out.println(PURCHASE_MONEY_MESSAGE);
            money = Validator.stringToInt(sc.nextLine());
            Validator.checkInputMoney(money);
        } catch (IllegalArgumentException e) {
            return inputMoney();
        }
        return money;
    }

    /**
     *  수동 로또 구입 매수 입력
     */
    public static int inputManualLottoSize(Money money) {
        int manualLottoSize;
        try {
            System.out.println(NEW_LINE + MANUAL_LOTTO_SIZE_MESSAGE);
            manualLottoSize = Validator.stringToInt(sc.nextLine());
            Validator.checkManualLottoSize(manualLottoSize, money);
        } catch (IllegalArgumentException e) {
            System.out.println(money.availablePurchseTicketCount() + "장 이상 구입하실 수 없습니다.");
            return inputManualLottoSize(money);
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputManualLottoSize(money);
        }
        return manualLottoSize;
    }

    public static void printInputManualLottoMessage( ) {
        System.out.println(NEW_LINE + MANUAL_LOTTO_NUMBER_MESSAGE);
    }

    /**
     *  로또 번호 입력
     *  LottoNumber 리스트에 값이 없을 경우 수동 구매 로또 번호
     *  LottoNumber 리스트에 값이 있을 경우 지난 주 당첨 번호
     */
    public static List<Number> inputLottoNumber() {
        List<Number> lottoNumber = new ArrayList<>();
        try {
            lottoNumber = makeLottoNumber(sc.nextLine().replace(" ", ""), lottoNumber);
            Validator.checkDuplication(lottoNumber);
        } catch (IllegalArgumentException e) {
            return inputLottoNumber();
        }
        return lottoNumber;
    }

    private static  List<Number> makeLottoNumber(String numbers, List<Number> lottoNumber) {
        for (String number: numbers.split(",")) {
            lottoNumber.add(NumberSet.of(Integer.parseInt(number)));
        }
        return lottoNumber;
    }

    public static void printInputWinningLottoMessage( ) {
        System.out.println(NEW_LINE + LAST_WEEK_WINNING_NUMBER_MESSAGE);
    }

    /**
     *  보너스 볼 입력
     */
    public static Number inputBonusNumber(Lotto winningLotto) {
        int bonusNumber;
        try {
            System.out.println(BONUS_NUMBER_MESSAGE);
            bonusNumber = Validator.stringToInt(sc.nextLine());
            Validator.checkBonusNumberDuplication(winningLotto, bonusNumber);
            return NumberSet.of(bonusNumber);
        } catch (IllegalArgumentException e) {
            return inputBonusNumber(winningLotto);
        }
    }
}

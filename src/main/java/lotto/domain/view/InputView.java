package lotto.domain.view;

import lotto.domain.model.Lotto;
import lotto.domain.model.Money;
import lotto.domain.model.Number;
import lotto.domain.model.NumberSet;

import java.util.*;

public class InputView {
    private static final String NEW_LINE = "\n";
    private static final String PURCHASE_MONEY_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String MONEY_LIMIT_EXCEPTION_MESSAGE = "1,000원 이상 100,000원 미만의 금액을 입력해주세요.";
    private static final String MONEY_EXCHANGE_EXCEPTION_MESSAGE = "1,000원 단위로 입력해주세요.";
    private static final String MANUAL_LOTTO_SIZE_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBER_MESSAGE = "수동으로 구매할 번호를 입력해주세요.";
    private static final String LAST_WEEK_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String DUPLICATED_NUMBER_EXCEPTION_MESSAGE = "지난 주 당첨번호와 중복되지 않는 번호를 입력해주세요.";
    private static final String DUPLICATION_EXCEPTION_MESSAGE = "중복된 번호가 존재합니다.";
    private static final String AVAILABLE_PURCHASE_SIZE_EXCEPTION_MESSAGE = "구입 가능 매수를 초과하였습니다.";
    private static final int MAX_PURCHASE_PRICE = 100000;
    private static final int MIN_PURCHASE_PRICE = 1000;
    private static final int LOTTO_PRICE = 1000;
    private static final int MAX_LOTTO_SIZE = 6;
    private static final Scanner sc = new Scanner(System.in);

    /**
     * 구입 금액 입력
     */
    public static int inputMoney() {
        int money;
        try {
            System.out.println(PURCHASE_MONEY_MESSAGE);
            money = stringToInt(sc.nextLine());
            checkInputMoney(money);
        } catch (IllegalArgumentException e) {
            return inputMoney();
        }
        return money;
    }

    private static void checkInputMoney(int inputMoney) {
        checkExchange(inputMoney);
        checkMoneyLimitRange(inputMoney);
    }

    private static void checkExchange(int inputMoney) {
        if (inputMoney % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(MONEY_EXCHANGE_EXCEPTION_MESSAGE);
        }
    }

    private static void checkMoneyLimitRange(int inputMoney) {
        if (inputMoney < MIN_PURCHASE_PRICE || inputMoney > MAX_PURCHASE_PRICE) {
            throw new IllegalArgumentException(MONEY_LIMIT_EXCEPTION_MESSAGE);
        }
    }

    /**
     * 수동 로또 구입 매수 입력
     */
    public static int inputManualLottoSize(Money money) {
        int manualLottoSize;
        try {
            System.out.println(NEW_LINE + MANUAL_LOTTO_SIZE_MESSAGE);
            manualLottoSize = stringToInt(sc.nextLine());
            checkManualLottoSize(manualLottoSize, money);
        } catch (IllegalArgumentException e) {
            System.out.println(money.availablePurchseTicketCount() + "장 이상 구입하실 수 없습니다.");
            return inputManualLottoSize(money);
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해주세요.");
            return inputManualLottoSize(money);
        }
        return manualLottoSize;
    }

    private static void checkManualLottoSize(int manualLottoSize, Money money) {
        if (manualLottoSize > money.availablePurchseTicketCount()) {
            throw new IllegalArgumentException(AVAILABLE_PURCHASE_SIZE_EXCEPTION_MESSAGE);
        }
    }

    public static void printInputManualLottoMessage() {
        System.out.println(NEW_LINE + MANUAL_LOTTO_NUMBER_MESSAGE);
    }

    /**
     * 로또 번호 입력
     * LottoNumber 리스트에 값이 없을 경우 수동 구매 로또 번호
     * LottoNumber 리스트에 값이 있을 경우 지난 주 당첨 번호
     */
    public static List<Number> inputLottoNumber() {
        List<Number> lottoNumber = new ArrayList<>();
        try {
            lottoNumber = makeLottoNumber(sc.nextLine().replace(" ", ""), lottoNumber);
            checkDuplication(lottoNumber);
        } catch (IllegalArgumentException e) {
            return inputLottoNumber();
        }
        return lottoNumber;
    }

    private static void checkDuplication(List<Number> lottoNumber) {
        Set<Number> lottoSetforCheckingDuplication = new HashSet<>(lottoNumber);
        if (lottoSetforCheckingDuplication.size() != MAX_LOTTO_SIZE) {
            throw new IllegalArgumentException(DUPLICATION_EXCEPTION_MESSAGE);
        }
    }

    private static List<Number> makeLottoNumber(String numbers, List<Number> lottoNumber) {
        for (String number : numbers.split(",")) {
            lottoNumber.add(NumberSet.of(Integer.parseInt(number)));
        }
        return lottoNumber;
    }

    public static void printInputWinningLottoMessage() {
        System.out.println(NEW_LINE + LAST_WEEK_WINNING_NUMBER_MESSAGE);
    }

    /**
     * 보너스 볼 입력
     */
    public static Number inputBonusNumber(Lotto winningLotto) {
        int bonusNumber;
        try {
            System.out.println(BONUS_NUMBER_MESSAGE);
            bonusNumber = stringToInt(sc.nextLine());
            checkBonusNumberDuplication(winningLotto, bonusNumber);
            return NumberSet.of(bonusNumber);
        } catch (IllegalArgumentException e) {
            return inputBonusNumber(winningLotto);
        }
    }

    private static void checkBonusNumberDuplication(Lotto winningLotto, int bonusNumber) {
        if (winningLotto.isContained(NumberSet.of(bonusNumber))) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private static int stringToInt(String input) throws InputMismatchException {
        return Integer.parseInt(input.replace(" ", ""));
    }
}

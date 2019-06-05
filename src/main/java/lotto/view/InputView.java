package lotto.view;

import lotto.domain.lottomoney.Cash;
import lotto.domain.lottomoney.MoneyForLotto;
import lotto.domain.lottonumber.InvalidLottoNumberException;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberPool;
import lotto.domain.lottoticket.LottoTicket;
import lotto.domain.lottoticket.LottoTickets;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String LOTTO_NUMBER_DELIMITER = ",";
    private static final String INPUT_PURCHASE_PRICE_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String ERROR_NOT_INTEGER_MESSAGE = "정수로 입력해 주세요.";
    private static final String INPUT_NUM_OF_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_LOTTO_TICKET_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String ERROR_OUT_OF_SCOPE_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 로또 개수는 입력한 구입 금액의 한도 내에서 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String ERROR_INVALID_BONUS_BALL_MESSAGE = "보너스 볼은 지난 주 당첨 번호와 중복될 수 없습니다.";
    private static Scanner scanner = new Scanner(System.in);

    public static MoneyForLotto makeMoneyForLotto() {
        System.out.println(INPUT_PURCHASE_PRICE_MESSAGE);
        return makeMoneyForLotto(scanner.nextLine().trim());
    }

    public static MoneyForLotto makeMoneyForLotto(String input) {
        try {
            Cash purchasePrice = new Cash(Long.parseLong(input));
            return new MoneyForLotto(purchasePrice);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_NOT_INTEGER_MESSAGE);
            return makeMoneyForLotto();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeMoneyForLotto();
        }
    }

    public static long makeNumOfManualTickets(long numOfPurchasedTickets) {
        System.out.println(INPUT_NUM_OF_MANUAL_LOTTO_MESSAGE);
        return makeNumOfManualTickets(scanner.nextLine().trim(), numOfPurchasedTickets);
    }

    public static long makeNumOfManualTickets(String input, long numOfPurchasedTickets) {
        try {
            long numOfManualTickets = Long.parseLong(input);
            if (isOutOfScope(numOfManualTickets, numOfPurchasedTickets)) {
                throw new IllegalArgumentException(ERROR_OUT_OF_SCOPE_MANUAL_LOTTO_MESSAGE);
            }
            return numOfManualTickets;
        } catch (NumberFormatException e) {
            System.out.println(ERROR_NOT_INTEGER_MESSAGE);
            return makeNumOfManualTickets(numOfPurchasedTickets);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeNumOfManualTickets(numOfPurchasedTickets);
        }
    }

    private static boolean isOutOfScope(long numOfManualTickets, long numOfPurchasedTickets) {
        return numOfManualTickets < 0 || numOfManualTickets > numOfPurchasedTickets;
    }

    public static LottoTicket makeWinningTicket() {
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
        return makeLottoTicket(scanner.nextLine());
    }

    public static LottoTicket makeLottoTicket(String input) {
        try {
            String[] inputs = input.split(LOTTO_NUMBER_DELIMITER);
            return generateLottoTicketFrom(inputs);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_NOT_INTEGER_MESSAGE);
            return makeLottoTicket(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeLottoTicket(scanner.nextLine());
        }
    }

    public static LottoTickets makeManualTickets(long numOfManualTickets) {
        LottoTickets manualTickets = new LottoTickets();
        if (numOfManualTickets == 0) {
            return manualTickets;
        }

        System.out.println(INPUT_MANUAL_LOTTO_TICKET_MESSAGE);

        for (int i = 0; i < numOfManualTickets; i++) {
            manualTickets.add(makeLottoTicket(scanner.nextLine()));
        }
        return manualTickets;
    }

    private static LottoTicket generateLottoTicketFrom(String[] inputs) {
        List<LottoNumber> lottoNumbers = Arrays.stream(inputs)
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumberPool::valueOf)
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumbers);
    }

    public static LottoNumber makeBonusBall(LottoTicket winningTicket) {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return makeBonusBall(scanner.nextLine().trim(), winningTicket);
    }

    public static LottoNumber makeBonusBall(String input, LottoTicket winningTicket) {
        try {
            LottoNumber bonusBall = LottoNumberPool.valueOf(Integer.parseInt(input));
            checkDuplicationOf(bonusBall, winningTicket);
            return bonusBall;
        } catch (NumberFormatException e) {
            System.out.println(ERROR_NOT_INTEGER_MESSAGE);
            return makeBonusBall(winningTicket);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeBonusBall(winningTicket);
        }
    }

    private static void checkDuplicationOf(LottoNumber bonusBall, LottoTicket winningTicket) {
        if (winningTicket.match(bonusBall)) {
            throw new InvalidLottoNumberException(ERROR_INVALID_BONUS_BALL_MESSAGE);
        }
    }
}

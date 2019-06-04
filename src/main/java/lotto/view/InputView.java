package lotto.view;

import lotto.domain.lottonumber.InvalidLottoNumberException;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottoseller.Cash;
import lotto.domain.lottoseller.LottoSeller;
import lotto.domain.lottoticket.LottoTicket;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String INPUT_PURCHASE_PRICE_MESSAGE = "구입 금액을 입력해 주세요.";
    private static final String INPUT_WINNING_LOTTO_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "정수로 입력해 주세요.";
    private static final String LOTTO_NUMBER_DELIMITER = ",";
    private static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String INVALID_BONUS_BALL_ERROR_MESSAGE = "보너스 볼은 지난 주 당첨 번호와 중복될 수 없습니다.";
    public static final String INPUT_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String OUT_OF_SCOPE_MANUAL_LOTTO_ERROR_MESSAGE = "수동으로 구매할 로또 개수는 입력한 구입 금액의 한도 내에서 입력해 주세요.";
    private static Scanner scanner = new Scanner(System.in);

    public static LottoSeller makeLottoSeller() {
        System.out.println(INPUT_PURCHASE_PRICE_MESSAGE);
        return makeLottoSeller(scanner.nextLine().trim());
    }

    public static LottoSeller makeLottoSeller(String input) {
        try {
            Cash purchasePrice = new Cash(Long.parseLong(input));
            return new LottoSeller(purchasePrice);
        } catch (NumberFormatException e) {
            System.out.println(NOT_INTEGER_ERROR_MESSAGE);
            return makeLottoSeller();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeLottoSeller();
        }
    }

    public static long makeNumOfManualTickets(long numOfPurchasedTickets) {
        System.out.println(INPUT_MANUAL_LOTTO_MESSAGE);
        return makeNumOfManualTickets(scanner.nextLine().trim(), numOfPurchasedTickets);
    }

    public static long makeNumOfManualTickets(String input, long numOfPurchasedTickets) {
        try {
            long numOfManualTickets = Long.parseLong(input);
            if (isOutOfScope(numOfManualTickets, numOfPurchasedTickets)) {
                throw new IllegalArgumentException(OUT_OF_SCOPE_MANUAL_LOTTO_ERROR_MESSAGE);
            }
            return numOfManualTickets;
        } catch (NumberFormatException e) {
            System.out.println(NOT_INTEGER_ERROR_MESSAGE);
            return makeNumOfManualTickets(numOfPurchasedTickets);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeNumOfManualTickets(numOfPurchasedTickets);
        }
    }

    private static boolean isOutOfScope(long numOfManualTickets, long numOfPurchasedTickets) {
        return numOfManualTickets < 0 || numOfManualTickets > numOfPurchasedTickets;
    }

    public static LottoTicket makeWinningLotto() {
        System.out.println(INPUT_WINNING_LOTTO_MESSAGE);
        return makeWinningLotto(scanner.nextLine());
    }

    public static LottoTicket makeWinningLotto(String input) {
        try {
            String[] inputs = input.split(LOTTO_NUMBER_DELIMITER);
            return generateLottoTicketFrom(inputs);
        } catch (NumberFormatException e) {
            System.out.println(NOT_INTEGER_ERROR_MESSAGE);
            return makeWinningLotto();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeWinningLotto();
        }
    }

    private static LottoTicket generateLottoTicketFrom(String[] inputs) {
        List<LottoNumber> lottoNumbers = Arrays.stream(inputs)
                .map(String::trim)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new LottoTicket(lottoNumbers);
    }

    public static LottoNumber makeBonusBall(LottoTicket winningLotto) {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
        return makeBonusBall(scanner.nextLine().trim(), winningLotto);
    }

    public static LottoNumber makeBonusBall(String input, LottoTicket winningLotto) {
        try {
            LottoNumber bonusBall = new LottoNumber(Integer.parseInt(input));
            checkDuplicationOf(bonusBall, winningLotto);
            return bonusBall;
        } catch (NumberFormatException e) {
            System.out.println(NOT_INTEGER_ERROR_MESSAGE);
            return makeBonusBall(winningLotto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return makeBonusBall(winningLotto);
        }
    }

    private static void checkDuplicationOf(LottoNumber bonusBall, LottoTicket winningLotto) {
        if (winningLotto.match(bonusBall)) {
            throw new InvalidLottoNumberException(INVALID_BONUS_BALL_ERROR_MESSAGE);
        }
    }
}

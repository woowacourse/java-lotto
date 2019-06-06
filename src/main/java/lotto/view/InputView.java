package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class InputView {
    private static final String INPUT_PRICE = "구입금액을 입력해 주세요.";
    private static final String INPUT_MANUAL = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String EX_USER_PRICE_FORMAT_MESSAGE = "1000 이상의 숫자만 입력해주세요.";
    private static final String EX_MANUAL_COUNT_RANGE_MESSAGE = "0 이상의 구입 금액을 초과하지 않는 숫자를 입력해주세요(로또는 한장에 1000원입니다.)";
    private static final String EX_LOTTO_FORMAT_RANGE_MESSAGE = "1~45 사이의 숫자를 입력해주세요";
    private static final String EX_LOTTO_RE_INPUT_MESSAGE = " 다시 입력해주세요";

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String REGEX_BLANK = " ";
    private static final String REPLACEMENT_EMPTY = "";
    private static final String REGEX_COMMA = ",";

    public static Money generateInvalidUserPrice() {
        try {
            System.out.println(INPUT_PRICE);
            long price = Long.parseLong(inputByUser());
            return new Money(price);
        } catch (Exception e) {
            System.out.println(EX_USER_PRICE_FORMAT_MESSAGE);
            return generateInvalidUserPrice();
        }
    }

    private static String inputByUser() {
        return SCANNER.nextLine().trim();
    }

    public static long generateInvalidManualCount(Money money) {
        try {
            System.out.println(INPUT_MANUAL);
            long manualCount = Long.parseLong(inputByUser());
            money.checkManualCount(manualCount);
            return manualCount;
        } catch (Exception e) {
            System.out.println(EX_MANUAL_COUNT_RANGE_MESSAGE);
            return generateInvalidManualCount(money);
        }
    }

    public static Lotteries generateInvalidLotto(Lotteries lotteries, CustomLotto customLotto) {
        try {
            List<Integer> lottoNumbers = generateNoFormatLottoNumbers(inputByUser());
            lotteries.addCustomLotto(lottoNumbers, customLotto);
            return lotteries;
        } catch (NumberFormatException e) {
            System.out.println(EX_LOTTO_FORMAT_RANGE_MESSAGE + EX_LOTTO_RE_INPUT_MESSAGE);
            return generateInvalidLotto(lotteries, customLotto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + EX_LOTTO_RE_INPUT_MESSAGE);
            return generateInvalidLotto(lotteries, customLotto);
        }
    }

    private static List<Integer> generateNoFormatLottoNumbers(String inputByUser) {
        List<Integer> lottoNumbers = new ArrayList<>();
        List<String> lotteries = Arrays.asList(inputByUser.replaceAll(REGEX_BLANK, REPLACEMENT_EMPTY).split(REGEX_COMMA));
        for (String lotto : lotteries) {
            lottoNumbers.add(Integer.parseInt(lotto));
        }
        return lottoNumbers;
    }

    public static Winner generateWinner(CustomLotto customLotto) {
        Lotto lotto = generateInvalidWinLotto(customLotto);
        return generateInvalidWinBonus(lotto);
    }

    public static Lotto generateInvalidWinLotto(CustomLotto customLotto) {
        try {
            System.out.println("지난 주 당첨 번호를 입력해 주세요.");
            return Lotto.customLotto(generateNoFormatLottoNumbers(inputByUser()), customLotto);
        } catch (NumberFormatException e) {
            System.out.println(EX_LOTTO_FORMAT_RANGE_MESSAGE + EX_LOTTO_RE_INPUT_MESSAGE);
            return generateInvalidWinLotto(customLotto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateInvalidWinLotto(customLotto);
        }
    }

    public static Winner generateInvalidWinBonus(Lotto lotto) {
        try {
            System.out.println("보너스 번호를 입력해 주세요.");
            LottoNumber lottoNumber = new LottoNumber(Integer.parseInt(inputByUser()));
            return new Winner(lotto, lottoNumber);
        } catch (NumberFormatException e) {
            System.out.println(EX_LOTTO_FORMAT_RANGE_MESSAGE + EX_LOTTO_RE_INPUT_MESSAGE);
            return generateInvalidWinBonus(lotto);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return generateInvalidWinBonus(lotto);
        }
    }
}

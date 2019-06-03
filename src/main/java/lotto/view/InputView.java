package lotto.view;

import lotto.domain.lottoseller.Cash;
import lotto.domain.lottonumber.LottoNumber;
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
}

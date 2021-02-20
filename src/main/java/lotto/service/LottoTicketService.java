package lotto.service;

import lotto.domain.lottos.LottoBoughtTicket;
import lotto.domain.lottos.LottoNumber;
import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.generator.RandomNumberGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketService {

    private static final String DELIMITER = ",";
    private static final String COUNT_ERROR_MESSAGE = "당첨 숫자는 6개 넣어야 합니다.";
    private static final String NUMBER_FORMAT_ERROR_MESSAGE = "로또 당첨번호는 6개 모두 숫자여야합니다.";
    private static final int LOTTO_NUMBER_SIZE = 6;

    private LottoTicketService() {
    }

    public static LottoBoughtTicket createLottoTicket() {
        return new LottoBoughtTicket(new RandomNumberGenerator().generateNumbers());
    }

    public static LottoTicket createLottoWinnerTicket(final String input) {
        List<LottoNumber> lottoWinnerNumbers =
                Arrays.stream(input.split(DELIMITER))
                        .map(String::trim)
                        .map(LottoTicketService::parseInt)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList());
        validateLottoSize(lottoWinnerNumbers.size());
        return new LottoTicket(lottoWinnerNumbers);
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new NumberFormatException(NUMBER_FORMAT_ERROR_MESSAGE);
        }
    }

    private static void validateLottoSize(final int size) {
        if (size != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(COUNT_ERROR_MESSAGE);
        }
    }

}

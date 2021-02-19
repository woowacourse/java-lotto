package lotto.service;

import lotto.domain.LottoBoughtTicket;
import lotto.domain.LottoNumber;
import lotto.domain.LottoWinnerTicket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketService {

    private static final String DELIMITER = ",";
    private static final String COUNT_ERROR_MESSAGE = "당첨 숫자는 6개 넣어야 합니다.";
    private static final int VALID_NUMBER_COUNT = 6;

    private LottoTicketService() {
    }

    public static LottoBoughtTicket createLottoTicket() {
        List<LottoNumber> lottoNumbers = LottoNumber.getCache();
        Collections.shuffle(lottoNumbers);
        return lottoNumbers
                        .stream()
                        .limit(VALID_NUMBER_COUNT)
                        .sorted()
                        .collect(Collectors.collectingAndThen(Collectors.toList(), LottoBoughtTicket::new));
    }

    public static LottoWinnerTicket createLottoWinnerTicket(String input) {
        List<LottoNumber> lottoWinnerNumbers =
                Arrays.stream(input.split(DELIMITER))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList());
        if (lottoWinnerNumbers.size() != VALID_NUMBER_COUNT) {
            throw new IllegalArgumentException(COUNT_ERROR_MESSAGE);
        }
        return new LottoWinnerTicket(lottoWinnerNumbers);
    }

}

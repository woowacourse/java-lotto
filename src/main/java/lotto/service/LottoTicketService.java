package lotto.service;

import lotto.domain.lottos.LottoBoughtTicket;
import lotto.domain.lottos.LottoNumber;
import lotto.domain.lottos.generator.RandomNumberGenerator;
import lotto.domain.lottos.winnerlotto.LottoWinnerTicket;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketService {

    private static final String DELIMITER = ",";
    private static final String COUNT_ERROR_MESSAGE = "당첨 숫자는 6개 넣어야 합니다.";
    private static final int LOTTO_NUMBER_SIZE = 6;

    private LottoTicketService() {
    }

    public static LottoBoughtTicket createLottoTicket() {
        return new LottoBoughtTicket(new RandomNumberGenerator().generateNumbers());
    }

    public static LottoWinnerTicket createLottoWinnerTicket(String input) {
        List<LottoNumber> lottoWinnerNumbers =
                Arrays.stream(input.split(DELIMITER))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList());
        if (lottoWinnerNumbers.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException(COUNT_ERROR_MESSAGE);
        }
        return new LottoWinnerTicket(lottoWinnerNumbers);
    }

}

package lotto.service;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.LottoTicket.LOTTO_NUMBER_COUNT;

public class LottoTicketService {

    private static final String DELIMITER = ",";
    private static final String COUNT_ERROR_MESSAGE = "당첨 숫자는 " + LOTTO_NUMBER_COUNT + "개 넣어야 합니다.";

    private LottoTicketService() {
    }

    public static LottoTicket createTicket(List<LottoNumber> lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTicket createManualTicket(String input) {
        List<LottoNumber> lottoWinnerNumbers =
                Arrays.stream(input.split(DELIMITER))
                        .map(String::trim)
                        .map(Integer::parseInt)
                        .map(LottoNumber::new)
                        .collect(Collectors.toList());
        if (lottoWinnerNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(COUNT_ERROR_MESSAGE);
        }
        return createTicket(lottoWinnerNumbers);
    }
}

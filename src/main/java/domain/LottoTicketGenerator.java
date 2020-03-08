package domain;

import spark.utils.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicketGenerator {
    private static final int LOTTO_TICKET_SIZE = 6;

    private LottoTicketGenerator() {
    }

    public static LottoTicket generateAutoLottoTicket() {
        LottoNumbers.shuffleLottoNumbers();
        List<LottoNumber> lottoTicket = LottoNumbers.getInstance().stream()
                .limit(LOTTO_TICKET_SIZE)
                .collect(Collectors.toList());
        Collections.sort(lottoTicket);
        return new LottoTicket(lottoTicket);
    }

    public static LottoTicket generateManualLottoTicket(String input) {
        validateBlank(input);
        List<LottoNumber> lottoNumbers = LottoNumberSplit.initializeLottoNumbers(input);
        Collections.sort(lottoNumbers);
        return new LottoTicket(lottoNumbers);
    }

    private static void validateBlank(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
    }
}

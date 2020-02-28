package domain;

import spark.utils.StringUtils;

import java.util.Collections;
import java.util.List;

public class ManualLottoTicketGenerator {
    private ManualLottoTicketGenerator() {
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

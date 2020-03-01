package lotto.domain;

import lotto.utils.StringUtils;
import lotto.utils.ValidationUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {
    protected final List<LottoBall> lottoTicket;

    //자동생성
    public LottoTicket(List<LottoBall> lottoTicket) {
        ValidationUtils.validateLottoTicketSize(lottoTicket.size());
        this.lottoTicket = Collections.unmodifiableList(lottoTicket);
    }

    //수동생성
    public LottoTicket(String inputTicketNumber) {
        String[] ticketNumber = StringUtils.parseString(inputTicketNumber);
        ValidationUtils.validateIntegerNumberFormat(ticketNumber);
        ValidationUtils.validatePositiveNumber(ticketNumber);
        ValidationUtils.validateDuplicateNumber(ticketNumber);
        ValidationUtils.validateLottoTicketSize(ticketNumber.length);

        this.lottoTicket = Collections.unmodifiableList(
                Arrays.stream(ticketNumber)
                        .map(ball -> LottoBalls.findLottoBall(Integer.parseInt(ball)))
                        .collect(Collectors.toList())
        );
    }

    public List<LottoBall> getLottoTicket() {
        return lottoTicket;
    }

}

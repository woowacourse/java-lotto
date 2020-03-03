package lotto.domain;

import lotto.utils.StringUtils;
import lotto.utils.ValidationUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTicket {

    protected final List<LottoBall> lottoTicket;

    protected LottoTicket(List<LottoBall> lottoTicket) {
        validateAutoTicket(lottoTicket.size());
        this.lottoTicket = Collections.unmodifiableList(lottoTicket);
    }

    private void validateAutoTicket(int size) {
        ValidationUtils.validateLottoTicketSize(size);
    }

    protected LottoTicket(String inputTicketNumber) {
        String[] ticketNumber = StringUtils.parseString(inputTicketNumber);
        validateManualLottoTicket(ticketNumber);

        this.lottoTicket = Collections.unmodifiableList(
                Arrays.stream(ticketNumber)
                        .map(ball -> LottoBalls.findLottoBall(StringUtils.stringToInt(ball)))
                        .collect(Collectors.toList())
        );
    }

    public static LottoTicket of(List<LottoBall> lottoTicket){
        return new LottoTicket(lottoTicket);
    }

    public static LottoTicket of(String lottoTicket){
        return new LottoTicket(lottoTicket);
    }

    private void validateManualLottoTicket(String[] ticketNumber) {
        ValidationUtils.validateIntegerNumberFormat(ticketNumber);
        ValidationUtils.validatePositiveNumber(ticketNumber);
        ValidationUtils.validateDuplicateNumber(ticketNumber);
        ValidationUtils.validateLottoTicketSize(ticketNumber.length);
    }

    public List<LottoBall> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

}

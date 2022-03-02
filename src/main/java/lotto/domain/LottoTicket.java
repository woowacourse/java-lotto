package lotto.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.generator.LottoTicketGenerator;
import lotto.domain.vo.LottoNumber;

public class LottoTicket {

    private static final int LOTTO_TICKET_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        validateLottoTicket(lottoNumbers);

        Collections.sort(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    public LottoTicket(LottoTicketGenerator lottoTicketGenerator) {
        List<LottoNumber> lottoNumbers = lottoTicketGenerator.generate();
        validateLottoTicket(lottoNumbers);
        this.lottoNumbers = lottoTicketGenerator.generate();
    }

    private void validateLottoTicket(List<LottoNumber> lottoNumbers) {
        validateLottoNumberSize(lottoNumbers);
        validateDuplication(lottoNumbers);
    }

    private void validateLottoNumberSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_TICKET_SIZE) {
            throw new IllegalArgumentException("로또 티켓의 개수가 6개가 아닙니다.");
        }
    }

    private void validateDuplication(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> lottoNumberSet = new HashSet<>(lottoNumbers);
        if (lottoNumberSet.size() < lottoNumbers.size()) {
            throw new IllegalArgumentException("중복된 숫자가 존재할 수 없습니다.");
        }
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public boolean isSame(WinningNumber winningNumber) {
        return lottoNumbers.contains(winningNumber.getWinningNumber());
    }
}

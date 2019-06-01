package lotto.model.lotto;

import lotto.model.lotto.exception.InvalidLottoTicketException;
import lotto.model.winninglotto.WinningLotto;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class LottoTicket {
    private static final int COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET = 6;

    private final Set<LottoNumber> lottoTicket;

    private LottoTicket(Set<LottoNumber> lottoNumbers) {
        checkValidLottoNumbers(lottoNumbers);
        this.lottoTicket = new TreeSet<>(lottoNumbers);
    }

    public static LottoTicket from(Set<LottoNumber> lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }

    public LottoRank convertToLottoRank(WinningLotto winningLotto) {
        return LottoRank.findRank(winningLotto.matchCount(this), winningLotto.hasBonusNumber(this));
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoTicket.contains(lottoNumber);
    }

    private void checkValidLottoNumbers(Set<LottoNumber> lottoNumbers) {
        if (!isLottoNumberSize(lottoNumbers)) {
            throw new InvalidLottoTicketException("번호의 개수가 6개가 아닙니다.");
        }
    }

    private boolean isLottoNumberSize(Set<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() == COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET;
    }

    public Stream<LottoNumber> stream() {
        return lottoTicket.stream();
    }

    @Override
    public String toString() {
        return lottoTicket.toString();
    }
}
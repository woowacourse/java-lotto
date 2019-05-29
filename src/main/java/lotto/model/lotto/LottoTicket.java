package lotto.model.lotto;

import lotto.model.lotto.exception.InvalidLottoTicketException;
import lotto.model.lottorank.LottoRank;
import lotto.model.winninglotto.WinningLotto;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class LottoTicket {
    private static final int COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET = 6;
    private final List<LottoNumber> lottoTicket;

    public LottoTicket(List<LottoNumber> lottoNumbers) {
        checkValidLottoNumbers(lottoNumbers);
        this.lottoTicket = lottoNumbers;
    }

    public LottoRank convertToLottoRank(WinningLotto winningLotto) {
        return LottoRank.findRank(winningLotto.matchCount(this), winningLotto.hasBonusNumber(this));
    }

    public boolean contains(LottoNumber lottoNumber) {
        return lottoTicket.contains(lottoNumber);
    }

    private void checkValidLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (isDuplicatedNumbers(lottoNumbers)) {
            throw new InvalidLottoTicketException("중복된 번호가 있습니다.");
        }
        if (!isLottoNumberSize(lottoNumbers)) {
            throw new InvalidLottoTicketException("번호의 개수가 6개가 아닙니다.");
        }
    }

    private boolean isLottoNumberSize(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() == COUNT_OF_LOTTO_NUMBERS_IN_ONE_TICKET;
    }

    private boolean isDuplicatedNumbers(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() != new HashSet<>(lottoNumbers).size();
    }

    public Stream<LottoNumber> stream() {
        return lottoTicket.stream();
    }

    @Override
    public String toString() {
        return lottoTicket.toString();
    }
}
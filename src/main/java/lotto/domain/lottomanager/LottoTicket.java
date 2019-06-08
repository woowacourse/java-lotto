package lotto.domain.lottomanager;

import lotto.domain.winning.BonusBall;
import lotto.utils.NullCheckUtil;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LottoTicket {
    private static final String ERROR_OVERLAPPED = "중복된 로또 번호가 존재합니다.";
    private static final String ERROR_LOTTO_NUMBER_COUNT = "번호가 6개가 아닙니다.";
    private static final int LOTTO_NUM_SIZE = 6;

    private List<LottoNumber> lottoTicket;

    private LottoTicket(List<LottoNumber> lottoNumbers) {
        checkValidLottoNumbers(lottoNumbers);
        this.lottoTicket = lottoNumbers;
    }

    private void checkValidLottoNumbers(List<LottoNumber> lottoNumbers) {
        checkOverlappedLottoNumbers(lottoNumbers);
        checkLottoNumberCount(lottoNumbers);
    }

    private void checkOverlappedLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (isOverlappedLottoTicket(lottoNumbers)) {
            throw new IllegalArgumentException(ERROR_OVERLAPPED);
        }
    }

    private boolean isOverlappedLottoTicket(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .distinct()
                .count() != lottoNumbers.size();
    }

    private void checkLottoNumberCount(List<LottoNumber> lottoNumbers) {
        if (isInsufficientNumberCount(lottoNumbers)) {
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_COUNT);
        }
    }

    private boolean isInsufficientNumberCount(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() != LOTTO_NUM_SIZE;
    }

    public static LottoTicket createLottoTicket(List<LottoNumber> lottoNumbers) {
        NullCheckUtil.checkNullLottoNumbers(lottoNumbers);
        return new LottoTicket(lottoNumbers);
    }

    public List<LottoNumber> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

    public Integer getMatchedNumbersCount(LottoTicket userTicket) {
        NullCheckUtil.checkNullLottoTicket(userTicket);
        return (int) this.lottoTicket.stream()
                .filter(userTicket.lottoTicket::contains)
                .count();
    }

    public Boolean isContainedNumbers(LottoNumber lottoNumber) {
        NullCheckUtil.checkNullLottoNumber(lottoNumber);
        return this.lottoTicket.contains(lottoNumber);
    }

    public Boolean isContainedNumbers(BonusBall bonusBall) {
        NullCheckUtil.checkNullBonusBall(bonusBall);
        return bonusBall.isContainNumbers(Collections.unmodifiableList(lottoTicket));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoTicket that = (LottoTicket) o;
        return Objects.equals(lottoTicket, that.lottoTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTicket);
    }
}

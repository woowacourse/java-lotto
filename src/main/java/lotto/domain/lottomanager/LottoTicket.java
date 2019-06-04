package lotto.domain.lottomanager;

import lotto.utils.NullCheckUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoTicket {
    private static final String ERROR_OVERLAPPED = "중복된 로또 번호가 존재합니다.";
    private static final String ERROR_LOTTO_NUMBER_COUNT = "번호가 6개가 아닙니다.";

    private List<LottoNumber> lottoTicket;

    private LottoTicket(List<LottoNumber> lottoNumbers) {
        checkValidLottoNumbers(lottoNumbers);
        this.lottoTicket = lottoNumbers;
    }

    private void checkValidLottoNumbers(List<LottoNumber> lottoNumbers) {
        NullCheckUtil.checkNullLottoNumbers(lottoNumbers);
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
        return lottoNumbers.size() != LottoConstant.LOTTO_NUM_SIZE;
    }

    public static LottoTicket createLottoTicket(List<LottoNumber> lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }

    public List<LottoNumber> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

    public Integer getMatchedNumbersCount(LottoTicket userTicket) {
        List<LottoNumber> joinedTicketNumbers
                = Stream.concat(this.lottoTicket.stream(), userTicket.lottoTicket.stream())
                .collect(Collectors.toList());

        Set<LottoNumber> uniqueJoinedTicketNumbers = new HashSet<>(joinedTicketNumbers);

        return joinedTicketNumbers.size() - uniqueJoinedTicketNumbers.size();
    }

    public Boolean isContainedNumbers(LottoNumber lottoNumber) {
        return this.lottoTicket.contains(lottoNumber);
    }

    public Boolean isContainedNumbers(BonusBall bonusBall) {
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

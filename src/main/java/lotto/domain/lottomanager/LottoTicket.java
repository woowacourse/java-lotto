package lotto.domain.lottomanager;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LottoTicket {
    private static final String ERROR_OVERLAPPED = "중복된 로또 번호가 존재합니다.";
    private static final String ERROR_LOTTO_NUMBER_COUNT = "번호가 6개가 아닙니다.";
    private static final int LOTTO_NUM_SIZE = 6;
    private static final String ERROR_NULL_USER_TICKET = "getMatchedNumbersCount() has Null";
    private static final String ERROR_NULL_CONTAIN_BONUS = "isContainBonus() has Null";
    private static final String ERROR_NULL_AUTO_CREATE = "createAutoTicket() has Null";

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

    static LottoTicket createAutoTicket(List<LottoNumber> lottoNumbers) {
        lottoNumbers.forEach(LottoTicket::checkNullLottoNumber);
        return new LottoTicket(lottoNumbers);
    }

    private static void checkNullLottoNumber(LottoNumber number) {
        if (number == null) {
            throw new NullPointerException(ERROR_NULL_AUTO_CREATE);
        }
    }

    static LottoTicket createManualTicket(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new LottoTicket(lottoNumbers);
    }

    public List<LottoNumber> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

    public int getMatchedNumbersCount(LottoTicket userTicket) {
        if (userTicket == null) {
            throw new NullPointerException(ERROR_NULL_USER_TICKET);
        }

        return (int) this.lottoTicket.stream()
                .filter(userTicket.lottoTicket::contains)
                .count();
    }

    public boolean isContainBonus(LottoNumber bonusBall) {
        if (bonusBall == null) {
            throw new IllegalArgumentException(ERROR_NULL_CONTAIN_BONUS);
        }

        return lottoTicket.contains(bonusBall);
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

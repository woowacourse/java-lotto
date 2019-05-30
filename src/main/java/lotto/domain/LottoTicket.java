package lotto.domain;

import lotto.utils.NullCheckUtil;

import java.util.List;

public class LottoTicket {
    private static final String ERROR_DUPLICATED = "중복된 로또 티켓이 존재합니다.";
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final String ERROR_LOTTO_NUMBER_COUNT = "번호가 6개가 아닙니다.";

    private List<LottoNumber> lottoTicket;

    private LottoTicket(List<LottoNumber> lottoNumbers) {
        NullCheckUtil.checkNull(lottoNumbers);
        checkDuplicatedLottoTicket(lottoNumbers);
        checkLottoNumberCount(lottoNumbers);
        this.lottoTicket = lottoNumbers;
    }

    public static LottoTicket createLottoTicket(List<LottoNumber> lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }

    private void checkDuplicatedLottoTicket(List<LottoNumber> lottoNumbers) {
        if (isDuplicatedLottoTicket(lottoNumbers)) {
            throw new IllegalArgumentException(ERROR_DUPLICATED);
        }
    }

    private boolean isDuplicatedLottoTicket(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.stream()
                .distinct()
                .count() != lottoNumbers.size();
    }

    private void checkLottoNumberCount(List<LottoNumber> lottoNumbers) {
        if(isInsufficientNumberCount(lottoNumbers)){
            throw new IllegalArgumentException(ERROR_LOTTO_NUMBER_COUNT);
        }
    }

    private boolean isInsufficientNumberCount(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.size() != LOTTO_NUMBER_COUNT;
    }
}

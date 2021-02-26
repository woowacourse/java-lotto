package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lotto.domain.rank.Rank;

public class LottoLine {

    public static final String LOTTO_LINE_NUMBER_COUNT_ERROR = "[Error] 로또 번호는 6개 입니다.";
    public static final String LOTTO_LINE_NUMBER_DUPLICATE_ERROR = "[Error] 로또 번호는 중복될 수 없습니다.";
    public static final int LOTTO_NUMBER_COUNT = 6;
    private final List<LottoNumber> lottoNumbers;
    private final boolean isManualLotto;

    public LottoLine(List<LottoNumber> lottoNumbers, boolean isManualLotto) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers = new ArrayList<>(lottoNumbers);
        this.isManualLotto = isManualLotto;
    }

    public Rank checkLottoLine(WinningLotto winningLotto) {
        int lottoNumberMatchCount = winningLotto.getLottoNumberMatchCount(lottoNumbers);
        boolean hasBonusNumber = winningLotto.isContainBonusLottoNumber(lottoNumbers);
        return Rank.of(lottoNumberMatchCount, hasBonusNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }

    public boolean hasLottoNumber(LottoNumber lottoNumber) {
        return this.lottoNumbers.contains(lottoNumber);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException(LOTTO_LINE_NUMBER_COUNT_ERROR);
        }
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> set = new HashSet<>(lottoNumbers);
        if (set.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException(LOTTO_LINE_NUMBER_DUPLICATE_ERROR);
        }
    }

    public boolean isManualLotto() {
        return isManualLotto;
    }

}
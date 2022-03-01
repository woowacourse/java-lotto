package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import constant.LottoConstant;

public class Lotto {

    private final List<LottoNumber> lottoNumbers = new ArrayList<>();

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateSize(lottoNumbers);
        validateDuplicate(lottoNumbers);
        this.lottoNumbers.addAll(lottoNumbers);
    }

    private void validateSize(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LottoConstant.LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 숫자 갯수는 " + LottoConstant.LOTTO_NUMBER_SIZE + "개이여야 합니다.");
        }
    }

    private void validateDuplicate(List<LottoNumber> lottoNumbers) {
        List<LottoNumber> deDuplicatedNumbers = lottoNumbers.stream()
            .distinct()
            .collect(Collectors.toList());
        if (deDuplicatedNumbers.size() != LottoConstant.LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException("로또 숫자는 중복일 수 없습니다.");
        }
    }

    public int calculateSameNumber(Lotto otherLotto) {
        List<LottoNumber> copiedNumbers = new ArrayList<>(List.copyOf(lottoNumbers));
        copiedNumbers.retainAll(otherLotto.lottoNumbers);

        return copiedNumbers.size();
    }

    public boolean containsNumber(LottoNumber lottoNumber) {
        return lottoNumbers.contains(lottoNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(lottoNumbers);
    }
}

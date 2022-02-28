package dto;

import domain.Lotto;
import domain.LottoNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoDto {
    private final List<Integer> lottoNumbers;

    public LottoDto(Lotto lotto) {
        lottoNumbers = sortLottoNumbers(getLottoNumbersByLotto(lotto));
    }

    private List<Integer> getLottoNumbersByLotto(Lotto lotto) {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (LottoNumber lottoNumber : lotto.getLottoNumbers()) {
            lottoNumbers.add(lottoNumber.getNumber());
        }

        return lottoNumbers;
    }

    private List<Integer> sortLottoNumbers(List<Integer> lottoNumbers) {
        List<Integer> copiedLottoNumbers = new ArrayList<>(lottoNumbers);
        Collections.sort(copiedLottoNumbers);

        return copiedLottoNumbers;
    }

    public List<Integer> getLottoNumbers() {
        return List.copyOf(lottoNumbers);
    }

    @Override
    public String toString() {
        return "LottoDto{" +
                "lottoNumbers=" + lottoNumbers +
                '}';
    }
}

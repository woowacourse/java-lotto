package lotto.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lotto {
    private final List<LottoNumber> lottoNumbers;

    public Lotto(String lottoNumber){
        this.lottoNumbers = new ArrayList<>();
        String[] lottos = lottoNumber.split(", ");
        for (int i = 0; i < lottos.length; i++) {
            lottoNumbers.add(new LottoNumber(Integer.parseInt(lottos[i])));
        }
    }

    public Lotto(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != 6) {
            throw new IllegalArgumentException("로또는 6개의 번호가 있어야합니다.");
        }
    }

    public boolean isBonusMatch(int bonusNumber) {
        return lottoNumbers.contains(new LottoNumber(bonusNumber));
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lotto)) return false;

        Lotto lotto = (Lotto) o;

        return Objects.equals(lottoNumbers, lotto.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return lottoNumbers != null ? lottoNumbers.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Lotto{" +
                "lottoNumbers=" + lottoNumbers +
                '}';
    }
}

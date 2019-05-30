package lotto.model.object;

import java.util.List;

public class Lotto {

        private final List<LottoNumber> lottoNumbers;

        public Lotto(List<LottoNumber> lottoNumbers) {
                this.lottoNumbers = lottoNumbers;
        }

        public int getMatchNumber(Lotto winningLotto) {
                return winningLotto.compareLottoNumbers(this.lottoNumbers);
        }

        private int compareLottoNumbers(List<LottoNumber> lottoNumbers) {
                int count = 0;
                for (LottoNumber lottoNumber : lottoNumbers) {
                        count = this.lottoNumbers.contains(lottoNumber) ? count + 1 : count;
                }
                return count;
        }

        public List<LottoNumber> getLottoNumbers() {
                return lottoNumbers;
        }

        public boolean hasBonusBall(BonusBall bonusBall) {
                if (lottoNumbers.contains(bonusBall.getLottoNumber())) {
                        return true;
                }
                return false;
        }
}

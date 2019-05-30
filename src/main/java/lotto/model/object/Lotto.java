package lotto.model.object;

import java.util.List;

public class Lotto {

        private final List<LottoNumber> lottoNumbers;

        public Lotto(final List<LottoNumber> lottoNumbers) {
                this.lottoNumbers = lottoNumbers;
        }

        public List<LottoNumber> getLottoNumbers() {
                return lottoNumbers;
        }

        public int getMatchNumber(final Lotto winningLotto) {
                return winningLotto.compareLottoNumbers(this.lottoNumbers);
        }

        private int compareLottoNumbers(final List<LottoNumber> lottoNumbers) {
                int count = 0;
                for (LottoNumber lottoNumber : lottoNumbers) {
                        count = this.lottoNumbers.contains(lottoNumber) ? count + 1 : count;
                }
                return count;
        }

        public boolean hasBonusBall(final BonusBall bonusBall) {
                boolean has = false;
                for(LottoNumber lottoNumber : lottoNumbers){
                        has = bonusBall.compareToLottoNumber(lottoNumber) ? true : has;
                }
                return has;
        }
}

package lotto.model.object;

import lotto.model.exception.LottoNumberDuplicationException;

import java.util.List;

public class Lotto {
        private static final int NUMBER_OF_LOTTO_NUMBERS = 6;

        private final List<LottoNumber> lottoNumbers;

        public Lotto(List<LottoNumber> lottoNumbers) {
                checkLottoNumbersDuplication(lottoNumbers);
                this.lottoNumbers = lottoNumbers;
        }

        private void checkLottoNumbersDuplication(List<LottoNumber> lottoNumbers) {
                for (int index = 0; index < NUMBER_OF_LOTTO_NUMBERS; index++) {
                        checkEachIndexLottoNumberDuplication(lottoNumbers, index);
                }
        }

        private void checkEachIndexLottoNumberDuplication(List<LottoNumber> lottoNumbers, int index) {
                List<LottoNumber> remainder = lottoNumbers.subList(index + 1, NUMBER_OF_LOTTO_NUMBERS-1);
                if(remainder.contains(lottoNumbers.get(index))){
                        throw new LottoNumberDuplicationException("로또 숫자가 중복되었습니다.");
                }
        }

        public int getMatchNumber(Lotto winnerLotto) {
                return winnerLotto.compareLottoNumbers(this.lottoNumbers);
        }

        private int compareLottoNumbers(List<LottoNumber> lottoNumbers) {
                int count = 0;
                for (LottoNumber lottoNumber : lottoNumbers) {
                        count = this.lottoNumbers.contains(lottoNumber) ? count + 1 : count;
                }
                return count;
        }
}

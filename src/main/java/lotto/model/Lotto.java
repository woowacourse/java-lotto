package lotto.model;

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
                for (int index = 0 ; index < NUMBER_OF_LOTTO_NUMBERS ; index++ ) {
                        CheckOneLottoNumberDuplication(lottoNumbers, index);
                }
        }

        private void CheckOneLottoNumberDuplication(List<LottoNumber> lottoNumbers, int index) {
                for (int i = index + 1 ; i < NUMBER_OF_LOTTO_NUMBERS ; i++){
                        checkContainLottoNumber(lottoNumbers, index, i);
                }
        }

        private void checkContainLottoNumber(List<LottoNumber> lottoNumbers, int index, int i) {
                if(lottoNumbers.get(index).equals(lottoNumbers.get(i))){
                        throw new LottoNumberDuplicationException("로또 숫자가 중복되었습니다.");
                }
        }

        public int getMatchNumber(Lotto winnerLotto) {
                return winnerLotto.compareLottoNumbers(this.lottoNumbers);
        }

        private int compareLottoNumbers(List<LottoNumber> lottoNumbers) {
                int count = 0;
                for(LottoNumber lottoNumber : lottoNumbers){
                        count = this.lottoNumbers.contains(lottoNumber) ? count + 1 : count;
                }
                return count;
        }
}

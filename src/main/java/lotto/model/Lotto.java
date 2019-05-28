package lotto.model;

import java.util.List;

public class Lotto {
        private final List<LottoNumber> lottoNumbers;

        public Lotto(List<LottoNumber> lottoNumbers) {
                checkLottoNumbersDuplication(lottoNumbers);
                this.lottoNumbers = lottoNumbers;
        }

        private void checkLottoNumbersDuplication(List<LottoNumber> lottoNumbers) {
                for (LottoNumber lottoNumber : lottoNumbers) {
                        CheckContainLottoNumber(lottoNumbers, lottoNumber);
                }
        }

        private void CheckContainLottoNumber(List<LottoNumber> lottoNumbers, LottoNumber lottoNumber) {
                if (lottoNumbers.contains(lottoNumber)) {
                        throw new LottoNumberDuplicationException("로또 숫자가 중복되었습니다.");
                }
        }
}

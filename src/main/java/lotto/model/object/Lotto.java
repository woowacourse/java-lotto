package lotto.model.object;

import lotto.model.exception.LottoNumberDuplicationException;

import java.util.ArrayList;
import java.util.List;

public class Lotto {
        public static final int NUMBER_OF_LOTTO_NUMBERS = 6;
        private final List<LottoNumber> lottoNumbers;

        public Lotto(final List<LottoNumber> lottoNumbers) {
                checkLottoNumbersDuplication(lottoNumbers);
                this.lottoNumbers = lottoNumbers;
        }

        public Lotto(final String[] lottoNumbersInput) {
                List<LottoNumber> lottoNumbers = new ArrayList<>();
                for(String lottoNumberInput : lottoNumbersInput){
                        lottoNumbers.add(LottoNumber.getInstance(Integer.parseInt(lottoNumberInput)));
                }
                checkLottoNumbersDuplication(lottoNumbers);
                this.lottoNumbers = lottoNumbers;
        }

        private static void checkLottoNumbersDuplication(final List<LottoNumber> lottoNumbers) {
                for (int index = 0; index < NUMBER_OF_LOTTO_NUMBERS - 1; index++) {
                        checkEachIndexLottoNumberDuplication(lottoNumbers, index);
                }
        }

        private static void checkEachIndexLottoNumberDuplication(final List<LottoNumber> lottoNumbers, final int index) {
                List<LottoNumber> remainder = lottoNumbers.subList(index + 1, NUMBER_OF_LOTTO_NUMBERS);
                if (remainder.contains(lottoNumbers.get(index))) {
                        throw new LottoNumberDuplicationException("로또 숫자가 중복되었습니다.");
                }
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
                        has = bonusBall.IsSame(lottoNumber) || has;
                }
                return has;
        }
}

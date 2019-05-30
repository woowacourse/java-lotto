package lotto.model.object;

public class BonusBall {
        private final LottoNumber lottoNumber;

        public BonusBall(final LottoNumber lottoNumber) {
                this.lottoNumber = lottoNumber;
        }

        public boolean compareToLottoNumber(final LottoNumber lottoNumber){
                return this.lottoNumber == lottoNumber;
        }
}

package lotto.model.object;

public class BonusBall {
        private final LottoNumber lottoNumber;

        public BonusBall(final int number) {
                this.lottoNumber = LottoNumber.getInstance(number);
        }

        public boolean IsSame(final LottoNumber lottoNumber){
                return this.lottoNumber == lottoNumber;
        }
}

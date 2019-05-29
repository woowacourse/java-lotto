package lotto.model.object;

public class BonusBall {
        private LottoNumber lottoNumber;

        public BonusBall(LottoNumber lottoNumber) {
                this.lottoNumber = lottoNumber;
        }

        public LottoNumber getLottoNumber() {
                return lottoNumber;
        }
}

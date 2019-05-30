package lotto.domain;

class AutoLottoGenerator {
    static Lotto makeLotto() {
        return new Lotto(LottoNumber.getRandomLottoNumbers());
    }
}
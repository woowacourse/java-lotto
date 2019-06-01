package lotto.domain;

public class AutoLottoMachine implements LottoMachine {

    @Override
    public Lotto generateLotto() {
        return new Lotto(RandomNumberGenerator.generate());
    }
}

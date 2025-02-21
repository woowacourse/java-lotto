package factory;

import java.util.List;
import model.Lotto;
import util.RandomGenerator;

public class AutoLottoFactory implements LottoFactory {
    @Override
    public Lotto createLotto(List<Integer> numbers) {
        throw new UnsupportedOperationException("AutoLottoFactory에서는 번호를 직접 지정할 수 없습니다.");
    }

    @Override
    public Lotto createLotto() {
        List<Integer> randomNumbers = RandomGenerator.generateNumbers(1, 45, 6);
        return new Lotto(randomNumbers);
    }
}

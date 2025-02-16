package factory;

import java.util.List;
import model.Lotto;

public class ManualLottoFactory implements LottoFactory{
    @Override
    public Lotto createLotto() {
        throw new UnsupportedOperationException("ManualLottoFactory에서는 자동 번호 생성을 지원하지 않습니다.");
    }

    @Override
    public Lotto createLotto(List<Integer> numbers) {
        return new Lotto(numbers);
    }
}

package lotto.model.winning_lotto.vo;

import java.util.List;
import java.util.Set;

public class WinningLottoNumbers {

    private final List<WinningLottoNumber> values;

    private WinningLottoNumbers(List<Integer> values) {
        validate(values);
        this.values = values.stream().map(WinningLottoNumber::from).toList();
    }

    public static WinningLottoNumbers from(List<Integer> values) {
        return new WinningLottoNumbers(values);
    }

    private void validate(List<Integer> values) {
        if (Set.copyOf(values).size() != values.size()) {
            throw new IllegalArgumentException("당첨 로또 번호는 중복될 수 없습니다.");
        }
    }

    public boolean contains(int value) {
        return values.contains(WinningLottoNumber.from(value));
    }

    @Override
    public String toString() {
        return "WinningLottoNumbers{" +
                "values=" + values +
                '}';
    }
}

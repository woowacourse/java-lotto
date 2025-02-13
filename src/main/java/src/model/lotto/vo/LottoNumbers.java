package src.model.lotto.vo;

import java.util.List;
import java.util.Objects;
import src.model.lotto.generator.NumberGenerator;

public class LottoNumbers {

    private final List<LottoNumber> values;

    private LottoNumbers(List<Integer> values) {
        this.values = values.stream().map(LottoNumber::from).toList();
    }

    public static LottoNumbers generateFrom(NumberGenerator numberGenerator, int size) {
        return new LottoNumbers(
                numberGenerator.generate(LottoNumber.MIN_NUMBER_RANGE, LottoNumber.MAX_NUMBER_RANGE, size));
    }

    public List<Integer> getValues() {
        return values.stream().map(LottoNumber::getValue).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(values);
    }

    @Override
    public String toString() {
        return "LottoNumbers{" +
                "values=" + values +
                '}';
    }
}

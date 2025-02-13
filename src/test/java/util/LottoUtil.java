package util;

import java.util.Set;
import java.util.TreeSet;
import model.Lotto;
import model.Number;

public class LottoUtil {

    public static Lotto generateTestLotto(int ... values){
        Set<Number> numbers = new TreeSet<>();
        for (int value : values){
            numbers.add(new Number(value));
        }
        return new Lotto(numbers);
    }
}

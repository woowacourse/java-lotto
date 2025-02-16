package testUtil;

import domain.Numbers;
import java.util.List;
import util.NumberPicker;

public class StaticNumberPicker implements NumberPicker {

    private final List<List<Integer>> staticNumbers;

    private int index;

    public StaticNumberPicker(List<List<Integer>> staticNumbers) {
        this.staticNumbers = staticNumbers;
    }

    @Override
    public Numbers pickUnique(int start, int end, int count) {
        return Numbers.from(staticNumbers.get(index++));
    }
}

package testUtil;

import util.NumberPicker;

import java.util.List;

public class StaticNumberPicker implements NumberPicker {
    
    private final List<List<Integer>> staticNumbers;
    
    private int index;
    
    public StaticNumberPicker(List<List<Integer>> staticNumbers) {
        this.staticNumbers = staticNumbers;
    }
    
    @Override
    public List<Integer> pickUnique(int start, int end, int count) {
        return staticNumbers.get(index++);
    }
}

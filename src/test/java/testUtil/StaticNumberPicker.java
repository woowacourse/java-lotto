package testUtil;

import util.NumberPicker;

import java.util.List;

public class StaticNumberPicker implements NumberPicker {
    
    private final List<Integer> staticNumbers;
    
    public StaticNumberPicker(List<Integer> staticNumbers) {
        this.staticNumbers = staticNumbers;
    }
    
    @Override
    public List<Integer> pickUnique(int start, int end, int count) {
        return staticNumbers;
    }
}

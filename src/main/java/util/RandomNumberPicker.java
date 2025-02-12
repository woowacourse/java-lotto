package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumberPicker implements NumberPicker {
    
    private final Random random;
    
    public RandomNumberPicker(Random random) {
        this.random = random;
    }
    
    @Override
    public List<Integer> pickUnique(int start, int end, int count) {
        List<Integer> result = new ArrayList<>();
        
        while (result.size() < count) {
            int random = this.random.nextInt(end) + start;
            if (!result.contains(random)) {
                result.add(random);
            }
        }
        
        return Collections.unmodifiableList(result);
    }
}

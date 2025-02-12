package domain.numbergenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generateNumber() {
        Set<Integer> selectedNumber = new HashSet<>();
        while (selectedNumber.size() != 6) {
            int pickedNumber = (int) (Math.random() * 45) + 1;
            selectedNumber.add(pickedNumber);
        }

        return selectedNumber.stream().toList();
    }
}

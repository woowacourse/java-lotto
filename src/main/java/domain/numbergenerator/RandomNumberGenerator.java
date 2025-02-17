package domain.numbergenerator;

import domain.enums.LottoNumber;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generateNumber() {
        Set<Integer> selectedNumber = new HashSet<>();
        while (selectedNumber.size() != LottoNumber.QUANTITY.getNumber()) {
            int pickedNumber = (int) (Math.random() * LottoNumber.MAX_RANGE.getNumber()) + 1;
            selectedNumber.add(pickedNumber);
        }

        return selectedNumber.stream().toList();
    }
}

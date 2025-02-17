package domain.numbergenerator;

import constants.LottoConstants;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generateNumber() {
        Set<Integer> selectedNumber = new HashSet<>();
        while (selectedNumber.size() != LottoConstants.LOTTO_NUMBERS_SIZE_THRESHOLD) {
            int pickedNumber = (int) (Math.random() * LottoConstants.LOTTO_NUMBER_MAX_THRESHOLD) + 1;
            selectedNumber.add(pickedNumber);
        }

        return selectedNumber.stream().toList();
    }
}

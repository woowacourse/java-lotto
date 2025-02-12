import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers = new ArrayList<>();

    public Lotto() {
        Random random = new Random();
        Set<Integer> set = new HashSet<>();
        while(true) {
            set.add(random.nextInt(45) + 1);
            if(set.size() == 6) {
                break;
            }
        }
        for(int number : set) {
            numbers.add(number);
        }
    }

    public List<Integer> getNumbers() {
        return Collections.unmodifiableList(numbers);
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {


    public List<Integer> generateNumber() {

        List<Integer> numbers = IntStream.range(1, 46)
                .boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);

        return numbers.subList(0, 6);

    }

}


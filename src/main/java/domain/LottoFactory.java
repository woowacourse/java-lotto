package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoFactory {
    public Lotto from(){
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        while(true) {
            int randomNumber = random.nextInt(45) + 1;

            // todo : 중복검사
            numbers.add(randomNumber);
            if(numbers.size()==6){break;}
        }

        Collections.sort(numbers);
        return new Lotto(numbers);
    }
}

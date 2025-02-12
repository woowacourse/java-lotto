package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LottoFactory {
    public Lotto from(){
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        while(true) {
            int randomNumber = random.nextInt(45) + 1;
            System.out.println("randomNumber = " + randomNumber);

            // todo : 중복검사
            numbers.add(randomNumber);
            if(numbers.size()==6){break;}
        }

        return new Lotto(numbers);
    }
}

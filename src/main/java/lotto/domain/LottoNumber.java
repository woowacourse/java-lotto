package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumber {

    public LottoNumber(){
    }

    public List<Integer> make() {
        List<Integer> numbers = new ArrayList<>();
        for(int i=1;i<=45;i++){
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        List<Integer> lottoNumber = new ArrayList<>();

        for(int i=0;i<6;i++){
            lottoNumber.add(numbers.get(i));
        }
        Collections.sort(lottoNumber);

        return lottoNumber;
    }

}

package view;

import java.util.List;

public class OutputView {

    public void printLottoQuantity(int quantity) {
        System.out.println(quantity + "개를 구매했습니다.");
    }

    public void printLottos(List<String> lottoNumbers) {
        lottoNumbers.forEach(System.out::println);
    }
}

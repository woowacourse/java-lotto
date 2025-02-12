package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public void lottoQuantityPrint(int lottoQuantity) {
        System.out.println(lottoQuantity + "개를 구매했습니다.");
    }

    public void lottoStatusPrint(List<Integer> lottoNumbers) {

        System.out.print("[");
        System.out.print(lottoNumbers.stream().map(String::valueOf).collect(Collectors.joining(",")));
        System.out.print("]");

        System.out.println();
    }
}

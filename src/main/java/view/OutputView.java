package view;

import dto.IssuedLottoDto;
import dto.IssuedLottosDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    public static void printLottoResult(IssuedLottosDto lottosDto) {
        List<IssuedLottoDto> lottos = lottosDto.lottos();
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(lotto ->
                printLotto(lotto.numbers())
        );

    }
    private static void printLotto(List<Integer> lotto){
        System.out.println(
                lotto.stream().map(String::valueOf).collect(Collectors.joining(", ", "[", "]"))
        );
    }
}

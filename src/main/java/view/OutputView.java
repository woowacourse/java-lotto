package view;

import dto.LottoDto;
import dto.LottosDto;

public class OutputView {
    public static void printPurchasedLotto(LottosDto lottosDto){
        System.out.println(String.format("%s개를 구매했습니다.",lottosDto.getQuantity()));
        for (LottoDto lottoDto : lottosDto.getLottoDtos()) {
            printLottoNumbers(lottoDto);
        }
    }
    
    public static void printLottoNumbers(LottoDto lottoDto){
        System.out.println(lottoDto.getLottoNumber());
    }
}

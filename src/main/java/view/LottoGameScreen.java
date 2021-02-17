package view;

import util.OutputUtil;
import view.dto.LottoCountResponseDto;

public class LottoGameScreen {
    public static final String BUY_STATUS = "%d개를 구매했습니다.";

    public void showLottoCount(final LottoCountResponseDto lottoCountResponseDto) {
        OutputUtil.printMessage(String.format(BUY_STATUS, lottoCountResponseDto.getLottoCount()));
    }
}

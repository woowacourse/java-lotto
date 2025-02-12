package view;

import view.dto.LottosDTO;
import view.dto.LottosDTO.LottoDTO;

public class OutputView {

    public void printLottos(LottosDTO lottosDTO) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%d개를 구매했습니다.", lottosDTO.lottoDTOs().size()));
        stringBuilder.append('\n');
        lottosDTO.lottoDTOs().forEach(lottoDTO -> appendStringBuilder(lottoDTO, stringBuilder));
        System.out.println(stringBuilder);
    }

    public void appendStringBuilder(LottoDTO lottoDTO, StringBuilder stringBuilder) {
        String format = "[%d, %d, %d, %d, %d, %d]";
        stringBuilder.append(String.format(format, lottoDTO.numbers().toArray())).append('\n');
    }


}

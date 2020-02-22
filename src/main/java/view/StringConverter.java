package view;

import domain.lottonumber.LottoNumbers;
import domain.result.LottoRank;
import domain.result.LottoResult;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringConverter {
    private static final int START_INDEX_EXCEPT_NO_WIN = 1;

    public static String convertLottoNumbers(LottoNumbers lottoNumbers) {
        return lottoNumbers.getValue().stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getValue()))
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public static String convertLottoResults(LottoResult lottoResult) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("당첨 통계\n-------------\n");
        List<LottoRank> results = Arrays.asList(LottoRank.values()).subList(START_INDEX_EXCEPT_NO_WIN, LottoRank.values().length);

        for (LottoRank rank : results) {
            stringBuilder.append(convertLottoRank(rank));
            stringBuilder.append(convertNullToZero(lottoResult.findNumberOf(rank)));
            stringBuilder.append("개\n");
        }

        return stringBuilder.toString();
    }

    private static int convertNullToZero(Integer number) {
        if (number == null) return 0;
        return number;
    }

    private static String convertLottoRank(LottoRank lottoRank) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(lottoRank.getMatchingNumbers());
        stringBuilder.append("개 일치 ");
        if (lottoRank == LottoRank.SECOND) {
            stringBuilder.append(", 보너스 볼 일치 ");
        }
        stringBuilder.append("(");
        stringBuilder.append(lottoRank.getPrize());
        stringBuilder.append("원) - ");

        return stringBuilder.toString();
    }
}

package dto;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public record IssuedLottoDto(
        List<Integer> numbers
) {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IssuedLottoDto)) {
            return false;
        }
        IssuedLottoDto issuedLottoDto = (IssuedLottoDto) obj;
        return new HashSet<>(this.numbers).containsAll(issuedLottoDto.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(new TreeSet<>(numbers));
    }
}

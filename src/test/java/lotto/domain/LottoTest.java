package lotto.domain;

import java.util.Arrays;
import lotto.domain.vo.Lotto;
import lotto.exception.InvalidLottoException;
import lotto.exception.LottoError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5,6", "6,5,4,3,2,1"})
    void 로또_번호는_오름차순으로_정렬된다(String value) {
        List<Integer> numbers = Arrays.stream(value.split(","))
                .map(Integer::parseInt)
                .toList();

        Lotto lotto = Lotto.from(numbers);

        assertThat(lotto.getNumbers()).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> Lotto.from(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5,-6",
            "-1,2,3,4,5,6",
    })
    void 로또_번호가_음수인_경우_예외가_발생한다(String value) {
        List<Integer> numbers = Arrays.stream(value.split(","))
                .map(Integer::parseInt)
                .toList();

        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(InvalidLottoException.class)
                .hasMessage(LottoError.INVALID_LOTTO_NUMBER_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5,46",
            "0,2,3,4,5,45",
    })
    void 로또_번호가_1과_45사이가_아닌_경우_예외가_발생한다(String value) {
        List<Integer> numbers = Arrays.stream(value.split(","))
                .map(Integer::parseInt)
                .toList();

        assertThatThrownBy(() -> Lotto.from(numbers))
                .isInstanceOf(InvalidLottoException.class)
                .hasMessage(LottoError.INVALID_LOTTO_NUMBER_RANGE.getMessage());
    }
}

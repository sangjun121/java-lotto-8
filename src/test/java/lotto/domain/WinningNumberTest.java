package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.WinningNumber;
import lotto.exception.InvalidWinningNumberException;
import lotto.exception.LottoError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WinningNumberTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5,6,7",
            "1,2,3,4,5",
    })
    void 당첨_번호의_개수가_6개가_아닌_경우_예외가_발생한다(String winningNumber) {
        List<Integer> numbers = Arrays.stream(winningNumber.split(","))
                .map(Integer::parseInt)
                .toList();

        assertThatThrownBy(() -> WinningNumber.from(numbers))
                .isInstanceOf(InvalidWinningNumberException.class)
                .hasMessage(LottoError.INVALID_WINNGING_NUMBER_COUNT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5,-6",
            "-1,2,3,4,5,6",
    })
    void 당첨_번호가_음수인_경우_예외가_발생한다(String winningNumber) {
        List<Integer> numbers = Arrays.stream(winningNumber.split(","))
                .map(Integer::parseInt)
                .toList();

        assertThatThrownBy(() -> WinningNumber.from(numbers))
                .isInstanceOf(InvalidWinningNumberException.class)
                .hasMessage(LottoError.INVALID_WINNING_NUMBER_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,2,3,4,5,46",
            "0,2,3,4,5,45",
    })
    void 당첨_번호가_1과_45사이가_아닌_경우_예외가_발생한다(String winningNumber) {
        List<Integer> numbers = Arrays.stream(winningNumber.split(","))
                .map(Integer::parseInt)
                .toList();

        assertThatThrownBy(() -> WinningNumber.from(numbers))
                .isInstanceOf(InvalidWinningNumberException.class)
                .hasMessage(LottoError.INVALID_WINNING_NUMBER_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "1,1,3,4,5,6",
            "1,1,1,1,1,1",
    })
    void 당첨_번호가_중복되는_경우_예외가_발생한다(String winningNumber) {
        List<Integer> numbers = Arrays.stream(winningNumber.split(","))
                .map(Integer::parseInt)
                .toList();

        assertThatThrownBy(() -> WinningNumber.from(numbers))
                .isInstanceOf(InvalidWinningNumberException.class)
                .hasMessage(LottoError.WINNING_NUMBER_DUPLICATED.getMessage());
    }

    @Test
    void 로또_번호가_당첨_번호와_3개_일치하는_경우_일치_개수는_3이_반환된다() {
        WinningNumber winningNumber = WinningNumber.from(List.of(1, 2, 3, 4, 5, 6));
        Lotto lotto = Lotto.from(List.of(1, 2, 3, 10, 11, 12));

        int result = winningNumber.countMatchesWith(lotto);

        assertThat(result).isEqualTo(3);
    }
}

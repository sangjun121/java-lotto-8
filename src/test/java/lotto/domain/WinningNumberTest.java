package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import lotto.exception.InvalidWinningNumberException;
import lotto.exception.LottoError;
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

        assertThatThrownBy(() -> new WinningNumber(numbers))
                .isInstanceOf(InvalidWinningNumberException.class)
                .hasMessage(LottoError.INVALID_WINNGING_NUMBER_COUNT.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0,1,2,3,4,5",
            "-1,2,3,4,5,6",
    })
    void 당첨_번호가_자연수가_아닌_경우_예외가_발생한다(String winningNumber) {
        List<Integer> numbers = Arrays.stream(winningNumber.split(","))
                .map(Integer::parseInt)
                .toList();

        assertThatThrownBy(() -> new WinningNumber(numbers))
                .isInstanceOf(InvalidWinningNumberException.class)
                .hasMessage(LottoError.WINNING_NUMBER_NOT_POSITIVE.getMessage());
    }
}

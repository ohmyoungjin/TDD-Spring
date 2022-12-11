package hello.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

//TDD공부
//테스트 -> 코딩 -> 리팩토링 순서로 한다.
//암호 검사기
//조건 => 1. 길이가 8글자 이상 2. 0부터 9사이의 숫자를 포함 3. 대문자 포함
//위의 3개의 조건을 만족하면 매우 강함(STRONG)
//2개의 규칙을 충족하면 보통(NORMAL)
//1개 이하의 규칙을 충족하면 약함
public class passwordMeterTest {

    private PasswordMeter passwordMeter = new PasswordMeter();

    @DisplayName("null 입력이면 익셉션 발생")
    @Test
    void nullInput() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> passwordMeter.meter(null));
    }

    @DisplayName("빈 값 입력이면 익셉션 발생")
    @Test
    void emptyInput() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> passwordMeter.meter(""));
    }

    @DisplayName("모든 조건을 충족하면 강함")
    @Test
    void meetAllRules() {
        assertPasswordStrength("abcABC123", PasswordStrength.STRONG);
        assertPasswordStrength("123ABCabc", PasswordStrength.STRONG);
    }

    private void assertPasswordStrength(String password, PasswordStrength expected) {
        PasswordStrength result = passwordMeter.meter(password);
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("길이가 8미만, 다른 조건 충족")
    @Test
    void digitAndUppercase() {
        assertPasswordStrength("abcC123", PasswordStrength.NORMAL);
        assertPasswordStrength("123abcC", PasswordStrength.NORMAL);
        //일반화가 떠오르면 로직을 만들어서 해당하는 부분을 빼주면 되지만
        //기억이 나지 않으면 점진적으로 구현을 하는 방법을 "삼각측량"이라 한다.
        assertPasswordStrength("Cabc12", PasswordStrength.NORMAL);
    }

    @DisplayName("대문자 없음, 다른 조건 충족")
    @Test
    void digitAndLength() {
        assertPasswordStrength("abcd1234", PasswordStrength.NORMAL);
        assertPasswordStrength("1234abcdwefw", PasswordStrength.NORMAL);
    }

    @DisplayName("숫자 없음, 다른 조건 충족")
    @Test
    void uppercaseAndLength() {
        assertPasswordStrength("ABCDabcde", PasswordStrength.NORMAL);
        assertPasswordStrength("abcdeABCDEF", PasswordStrength.NORMAL);
    }

    @DisplayName("길이만 충족")
    @Test
    void length() {
        assertPasswordStrength("abcdhiohw", PasswordStrength.WEAK);
    }

    @DisplayName("대문자만 충족")
    @Test
    void uppercase() {
        assertPasswordStrength("abcABC", PasswordStrength.WEAK);
    }

    @DisplayName("숫자만 충족")
    @Test
    void digit() {
        assertPasswordStrength("abc123", PasswordStrength.WEAK);
        assertPasswordStrength("1b2a3a", PasswordStrength.WEAK);
    }

    @DisplayName("아무것도 충족하지 않음")
    @Test
    void noting() {
        assertPasswordStrength("abc", PasswordStrength.WEAK);

    }
}

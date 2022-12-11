package hello.study;

public class PasswordMeter {
    public PasswordStrength meter(String pw) {
        if (pw == null || pw.isEmpty())
            throw new IllegalArgumentException();
        //일반화 로직처리로 인해 기존에 있던 코드 주석 ! (리팩토링 한 부분)
//        if ("abcC123".equals(pw) || "123abcC".equals(pw)) {
//            return PasswordStrength.NORMAL;
//        }
        //한 번씩만 쓰이는 변수들은 필요가 없어서 인라인으로 합쳐준다 ctrl + alt + n
        /*
        boolean lengthRule = pw.length() >= 8;
        boolean containsUp = containsUppercase(pw);TDD
        boolean containsDi = containsDigit(pw);
        if (lengthRule) metCount++;
        if (containsUp) metCount++;
        if (containsDi) metCount++;
        */
        /*
        calculateMetCount 함수로 빼줬음
        int metCount = 0;
        if (meetLength(pw)) metCount++;
        if (containsUppercase(pw)) metCount++;
        if (containsDigit(pw)) metCount++;
        */
        int metCount = calculateMetCount(pw);
        if (metCount == 0 || metCount == 1) {
            return PasswordStrength.WEAK;
        }
        if (metCount == 2) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
        /*
        위의 if (metCount == 1) 로 대체 되었다
        //길이만 충족
        if (lengthRule && !containsUp && !containsDi) {
            return PasswordStrength.WEAK;
        }
        //대문자만 충족
        if (!lengthRule && containsUp && !containsDi) {
            return PasswordStrength.WEAK;
        }
        */
        /*
        위의 if (metCount == 2) 로 대체 되었다
        if (!lengthRule) {
            return PasswordStrength.NORMAL;
        }
        if (!containsUp) {
            return PasswordStrength.NORMAL;
        }
        if (!containsDi) {
            return PasswordStrength.NORMAL;
        }
        */
    }

    private int calculateMetCount(String pw) {
        int metCount = 0;
        if (meetLength(pw)) metCount++;
        if (containsUppercase(pw)) metCount++;
        if (containsDigit(pw)) metCount++;
        return metCount;
    }

    private static boolean meetLength(String pw) {
        return pw.length() >= 8;
    }

    //대문자 포함 확인 함수

    private static boolean containsUppercase(String pw) {

        //변수 foundUppercase 를 return true , false 변경 후 없애 주었다.
//        boolean foundUppercase = false;
        for (char ch : pw.toCharArray()) {
            if (ch >= 'A' && ch <= 'Z') {
                /*
                foundUppercase = true;
                break;
                 = > */
                return true;

            }
        }
        //return foundUppercase; =>
        return false;
    }

    private boolean containsDigit(String pw) {
        for (char ch : pw.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}

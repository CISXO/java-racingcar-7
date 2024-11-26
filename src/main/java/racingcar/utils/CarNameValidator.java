package racingcar.utils;

import java.util.ArrayList;

public class CarNameValidator implements Validator {
    private static final int RESTRICTED_CAR_LENGTH = 4;
    private static final String RESTRICTED_CAR_NAME_LENGTH_ERROR = ERROR_PREFIX + "이름은 " + RESTRICTED_CAR_LENGTH + "자리 까지만 허용합니다.";
    private static final String EMPTY_CAR_NAME_ERROR = ERROR_PREFIX + "이름은 빈 문자열이나 공백은 포함할 수 없습니다.";
    private static final String INPUT_DUPLICATE_VALIDATE_MESSAGE = ERROR_PREFIX + "중복된 이름이 존재합니다.";

    @Override
    public void validation(String carName) {
        checkRestrictedLength(carName);
        checkEmptyCarName(carName);
    }

    private void checkRestrictedLength(String carName) {
        if (carName.length() > RESTRICTED_CAR_LENGTH) {
            throw new IllegalArgumentException(RESTRICTED_CAR_NAME_LENGTH_ERROR);
        }
    }

    private void checkEmptyCarName(String carName) {
        if (carName == null || carName.contains(" ") || carName.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CAR_NAME_ERROR);
        }
    }

    public void checkDuplicateAndAddCarName(ArrayList<String> inputDataList, String carName) {
        if (inputDataList.contains(carName)) {
            throw new IllegalArgumentException(INPUT_DUPLICATE_VALIDATE_MESSAGE);
        }
        inputDataList.add(carName);
    }

}

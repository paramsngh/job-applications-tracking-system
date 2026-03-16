package utils;

import java.util.Random;

public class CompanyIdGenerator {

    private static final Random random = new Random();

    public static int generateCompanyId() {
        return 1000 + random.nextInt(9000);
    }

}

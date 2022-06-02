package utils;

import java.util.Random;

/**
 * @author : Hau Nguyen
 * @created : 6/1/22
 **/

public class Util {
    public static boolean isLuckyByPercent(int percent) {
        if (percent < 0 || percent > 100) {
            throw new IndexOutOfBoundsException("Percent in range 0 to 100");
        }
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        return randomNumber <= percent;
    }
}

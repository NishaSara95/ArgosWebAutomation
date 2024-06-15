package com.bdd.utilities;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

/**
 * Contains static utility methods
 *
 */
@Log4j2
public class CommonUtils {

    // Sleep time
    public static final long ONE_SECOND = 1000;
    public static final long TWO_SECONDS = 2000;
    public static final long THREE_SECONDS = 3000;

    /**
     * Returns the random number between the min and max number
     *
     * @param min minimum range 0
     * @param max maximum int range
     * @return random value
     */
    public static String getRandomNumber(int min, int max) {
        Random random = new Random();
        int number = random.nextInt((max - min) + 1) + min;
        return String.valueOf(number);
    }

 

    public static String getProperty(String prop){
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
       return properties.getProperty(prop);
    }
}


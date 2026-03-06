package com.example.demoinvoicingservice.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.security.SecureRandom;

@UtilityClass
public class StringModifiedUtils {
    
    private SecureRandom random = new SecureRandom();
    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String NUMBERS = "0123456789";
    private final String FULL_CHARACTERS = CHARACTERS + NUMBERS;

    public static String removeStringWithLength(String text, int length) {
        if (text.length() <= length) {
            return text;
        } else {
            return text.substring(0, length);
        }
    }

    public String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(FULL_CHARACTERS.length());
            char randomChar = FULL_CHARACTERS.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public String buildMerchantName(String text) {
        String[] accountNameSplit = text.split(" ");
        String accountName = accountNameSplit[0];
        if (accountNameSplit.length > 1) {
            StringBuilder accountNameBuilder = new StringBuilder();
            for (int i = 1; i < accountNameSplit.length; i++) {
                accountNameBuilder.append(accountNameSplit[i]).append(" ");
            }
            accountNameBuilder.append(accountNameSplit[0]);
            accountName = accountNameBuilder.toString();
        }
        return cleanUpSpecialCharacters(accountName);
    }

    public String cleanUpSpecialCharacters(String text) {
        return text;
    }

    public String getShortHash(String hashId) {
        if (StringUtils.isBlank(hashId))
            return "N/A";
        if (StringUtils.isBlank(hashId) || hashId.length() <= 8)
            return hashId;
        return hashId.substring(0 , 8);
    }

    public String defaultNA(String text) {
        if (StringUtils.isNotBlank(text))
            return text;
        return "N/A";
    }

    public String parseBigDecimal(String text) {
        try {
            return (new BigDecimal(text)).toPlainString();
        } catch (Exception e) {
            return text.replaceAll("[^0-9\\.]", "");
        }
    }
    
}

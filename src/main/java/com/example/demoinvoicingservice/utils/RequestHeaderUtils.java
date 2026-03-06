package com.example.demoinvoicingservice.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public final class RequestHeaderUtils {

    private static final Logger log = LoggerFactory.getLogger(RequestHeaderUtils.class);
    private static final String DEFAULT_USER_ID = "invoicing";

    /**
     * Core method to get header
     * */
    private static String getHeader(String name) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            return null;
        }
        HttpServletRequest request = attrs.getRequest();
        return request.getHeader(name);
    }

    // Get Merchant ID header
    public static String getMerchantId() {
        String merchantId = getHeader("merchant-id");
        if (merchantId == null || merchantId.isEmpty()) {
            throw new RuntimeException("Header [merchant-id] cannot be null");
        }
        return merchantId;
    }

    // Get Client Secret header
    public static String getClientSecret() {
        String clientSecret = getHeader("client-secret");
        if (clientSecret == null || clientSecret.isEmpty()) {
            throw new RuntimeException("Header [client-secret] cannot be null");
        }
        return clientSecret;
    }

    // Get Wing Platform header
    public static String getWingPlatform() {
        String platform = getHeader("wing-platform");
        if (platform == null || platform.isEmpty()) {
            throw new RuntimeException("Header [wing-platform] cannot be null");
        }
        return platform;
    }

    // Get User ID header with default
    public static String getUserId() {
        String userId = getHeader("user-id");
        return (userId == null || userId.isEmpty()) ? DEFAULT_USER_ID : userId;
    }

    // Get optional Boolean headers with default
    public static Boolean getHeaderRequireTransaction() {
        String value = getHeader("invoicing-require-transaction");
        if (value == null || value.isEmpty()) {
            log.warn("Header [invoicing-require-transaction] is missing, defaulting to true");
            return true;
        }
        return Boolean.valueOf(value);
    }

    public static Boolean getHeaderRequireCrc() {
        String value = getHeader("invoicing-require-crc");
        if (value == null || value.isEmpty()) {
            log.warn("Header [invoicing-require-crc] is missing, defaulting to true");
            return true;
        }
        return Boolean.valueOf(value);
    }

    public static Boolean getHeaderAllowDoubleTransaction() {
        String value = getHeader("invoicing-allow-double-transaction");
        if (value == null || value.isEmpty()) {
            log.warn("Header [invoicing-allow-double-transaction] is missing, defaulting to false");
            return false;
        }
        return Boolean.valueOf(value);
    }

    public static Boolean getIsInternalService() {
        String value = getHeader("is-internal-service");
        if (value == null || value.isEmpty()) {
            log.warn("Header [is-internal-service] is missing, defaulting to false");
            return false;
        }
        return Boolean.valueOf(value);
    }

}


package com.getinthere.ncs._core.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UtilTest {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Test
    public void password_encode_test(TestReporter reporter) {
        String password = "1234";
        String encPassword = bCryptPasswordEncoder.encode(password);
        reporter.publishEntry("Encoded Password", encPassword);
    }

}

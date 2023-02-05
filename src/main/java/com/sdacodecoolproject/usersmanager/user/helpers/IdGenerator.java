package com.sdacodecoolproject.usersmanager.user.helpers;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

    public String generateUserId() {
        return RandomStringUtils.randomNumeric(15);
    }
}

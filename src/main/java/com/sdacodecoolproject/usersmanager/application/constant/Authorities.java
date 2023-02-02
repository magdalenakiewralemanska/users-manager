package com.sdacodecoolproject.usersmanager.application.constant;

import java.util.List;

public class Authorities {
    public static final List<String> USER_AUTHORITIES = List.of("READ");
    public static final List<String> MODERATOR_AUTHORITIES = List.of("READ", "UPDATE", "CREATE");
    public static final List<String> ADMIN_AUTHORITIES = List.of("READ", "UPDATE", "CREATE", "DELETE");
}

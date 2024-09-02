package com.per.adoption.support.users.dto;

import java.io.Serializable;
import java.util.List;

public record Error(
        String code, String message, List<String> detailedMessages) implements Serializable {
}

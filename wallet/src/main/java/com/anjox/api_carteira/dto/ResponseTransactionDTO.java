package com.anjox.api_carteira.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseTransactionDTO(
        UUID id,
        BigDecimal value,
        String message,
        LocalDateTime date
) {
}

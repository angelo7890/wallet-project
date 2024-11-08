package com.anjox.api_carteira.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record RequestTransactionDTO(
        @NotNull Long userid,

        @NotNull BigDecimal value,

        @NotNull Integer type,

        String Message
) {
}

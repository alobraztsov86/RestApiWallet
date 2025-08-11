package itk.academy.wallet_service.dto;

import itk.academy.wallet_service.entity.OperationType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class TransactionDto {

    @NotNull
    private UUID walletId;
    @NotNull
    private OperationType operationType;
    @Positive
    @Max(1000000000)
    private double amount;
}

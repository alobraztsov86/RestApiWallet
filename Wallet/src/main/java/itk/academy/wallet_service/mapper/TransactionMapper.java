package itk.academy.wallet_service.mapper;

import itk.academy.wallet_service.dto.TransactionDto;
import itk.academy.wallet_service.entity.OperationStatus;
import itk.academy.wallet_service.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.ERROR, imports = OperationStatus.class)
public interface TransactionMapper {
    @Mapping(target="wallet.id", source = "walletId")
    @Mapping(target="operationType", source = "operationType")
    @Mapping(target="amount", source = "amount")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status",  expression = "java(OperationStatus.PENDING)")
    @Mapping(target = "updated_at", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    Transaction toEntity(TransactionDto transactionDto);
}

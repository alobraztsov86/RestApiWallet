package itk.academy.wallet_service.mapper;

import itk.academy.wallet_service.dto.WalletDto;
import itk.academy.wallet_service.entity.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface WalletMapper {
    WalletDto toDto(Wallet wallet);
}

package storeManagementTool.Mappers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

//    @Mapping(target = "finalPrice", source = ".", qualifiedByName = "setFinalPrice")
//    CartEntityToDTO entityToDto(Cart cartEntity);
//
//    @Named("setFinalPrice")
//    default Double setTotalPrice(Cart cartEntity) {
//        return cartEntity.getProducts().stream().mapToDouble(product -> product.getPrice() * product.getQuantity())
//                .sum();
//    }
}

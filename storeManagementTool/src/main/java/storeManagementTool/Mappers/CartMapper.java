package storeManagementTool.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storeManagementTool.Dtos.CartEntityToDTO;
import storeManagementTool.Entities.Cart;

@Component
public class CartMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CartEntityToDTO entityToDTO(Cart cart){
        return modelMapper.map(cart, CartEntityToDTO.class);
    }

    public Cart convertToEntity(CartEntityToDTO cartEntityToDTO){
        return modelMapper.map(cartEntityToDTO, Cart.class);
    }
}

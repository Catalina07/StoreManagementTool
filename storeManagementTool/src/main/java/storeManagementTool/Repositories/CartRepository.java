package storeManagementTool.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import storeManagementTool.Entities.Cart;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

@Override
    <S extends Cart> S save(S cart);

@Override
    List<Cart> findAll();

@Override
Optional<Cart> findById(Long id);

}

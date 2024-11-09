package storeManagementTool.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import storeManagementTool.Entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
@Override
List<Product> findAll();

@Override
    Optional<Product> findById(Long id);

@Override
    <S extends Product>S save(S prodcut);

@Override
void deleteById(Long id);
}

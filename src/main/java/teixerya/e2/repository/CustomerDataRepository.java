package teixerya.e2.repository;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDataRepository extends JpaRepository<CustomerEntity, Integer> {
}

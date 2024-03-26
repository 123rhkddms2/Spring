package kr.co.ch07.repository.shop;

import kr.co.ch07.entity.shop.Customer;
import kr.co.ch07.repository.shop.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository                                                                  // CustomerRepositoryCustom 인터페이스 추가
public interface CustomerRepository extends JpaRepository<Customer, String>, CustomerRepositoryCustom {
}
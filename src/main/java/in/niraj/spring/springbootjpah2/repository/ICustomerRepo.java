package in.niraj.spring.springbootjpah2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.niraj.spring.springbootjpah2.entity.Customers;

@Repository
public interface ICustomerRepo extends JpaRepository<Customers, Long> {

}
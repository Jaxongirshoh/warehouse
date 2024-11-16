package dev.wisespirit.warehouse.repository;

import dev.wisespirit.warehouse.dto.ProductInterfaceProjection;
import dev.wisespirit.warehouse.entity.Product;
import dev.wisespirit.warehouse.dto.ProductClassProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select t from Product t where lower(t.name) = lower(?1)")
    List<Book> findAllByName(String author);
    @Modifying
    @Transactional
    @Query("delete from Product t where t.id = ?1")
    void delete(Long id);
    @Query(value = "insert into products(name,description,price,quantity) values(?1,?2,?3,?4)",
            nativeQuery = true)
    void insertProduct(String title, String author);

    @Query(value = "select new dev.wisespirit.warehouse.dto.ProductClassProjection(id,name) from Product ")
    List<ProductClassProjection> findAllClassProjection();


    @Query("select t.id as id,t.name as title from Product t")
    List<ProductInterfaceProjection> findAllInterfaceProjection();
}

package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    // JpaRepository는 2개의 제네릭 타입을 사용한다. JpaRepository<엔티티 타입 클래스, 기본키 타입>
    // Repository 에 Predicate 를 파라미터로 전달하기 위해서 QuerydslPredicateExecutor 인터페이스 상속
    // Predicate 란 조건이 맞다고 판단하는 근거를 함수로 제공하는 것

    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
    // 파라미터의 순서를 이용해 변수를 JPQL에 전달하는 경우, ':itemDetail' 대신 첫 번째 파라미터를 전달하겠다는 '?1'이라는 표현 사용
    // 순서를 이용할 경우 파라미터의 순서가 달라지면 쿼리문이 제대로 동작하지 않을 수 있기 때문에 @Param 어노테이션 사용 권장

    @Query(value="select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
    // 기존 데이터베이스에서 사용하던 쿼리를 그대로 사용해야 하는 경우, @Query의 nativeQuery 속성 사용
}

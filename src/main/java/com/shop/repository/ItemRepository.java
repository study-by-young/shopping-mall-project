package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // JpaRepository는 2개의 제네릭 타입을 사용한다. JpaRepository<엔티티 타입 클래스, 기본키 타입>
}

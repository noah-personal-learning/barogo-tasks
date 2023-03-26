package com.barogo.api.domain.delivery.repository;

import com.barogo.api.domain.delivery.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>, DeliveryQueryRepository {
}

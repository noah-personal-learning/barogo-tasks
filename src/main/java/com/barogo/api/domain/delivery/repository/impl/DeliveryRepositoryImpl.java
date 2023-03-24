package com.barogo.api.domain.delivery.repository.impl;

import com.barogo.api.domain.delivery.dto.response.DeliveryHistoryResponseDto;
import com.barogo.api.domain.delivery.repository.DeliveryQueryRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.barogo.api.domain.delivery.entity.QDelivery.delivery;

@RequiredArgsConstructor
@Repository
public class DeliveryRepositoryImpl implements DeliveryQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<DeliveryHistoryResponseDto> getNdayDeliveryHistory(Integer date) {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = LocalDateTime.of(now.toLocalDate().minusDays(date), LocalTime.MIDNIGHT);

        List<DeliveryHistoryResponseDto> requestDtos = queryFactory.select(
                Projections.constructor(DeliveryHistoryResponseDto.class,
                        delivery.id
                )
        ).from(delivery)
        .where(delivery.regDate.between(start, now))
        .fetch();

        return requestDtos;
    }

}

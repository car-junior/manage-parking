package com.carjunior.manageparking.domain.spec;

import com.carjunior.manageparking.domain.entity.Parking;
import com.carjunior.manageparking.domain.spec.search.ParkingSearch;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static com.carjunior.manageparking.domain.utils.CriteriaUtils.formatQueryToLike;
import static com.carjunior.manageparking.domain.utils.CriteriaUtils.lowerAndUnaccented;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public final class ParkingSpecification {
    private ParkingSpecification() {
    }

    public static Specification<Parking> getAll(ParkingSearch parkingSearch) {
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (isNotEmpty(parkingSearch.query())) {
                predicates.add(
                        builder.or(
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("name")),
                                        formatQueryToLike(parkingSearch.query())
                                ),
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("cnpj")),
                                        formatQueryToLike(parkingSearch.query())
                                ),
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("address")),
                                        formatQueryToLike(parkingSearch.query())
                                ),
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("phoneNumber")),
                                        formatQueryToLike(parkingSearch.query())
                                )
                        )
                );
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

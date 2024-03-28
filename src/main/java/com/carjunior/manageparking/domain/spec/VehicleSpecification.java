package com.carjunior.manageparking.domain.spec;

import com.carjunior.manageparking.domain.entity.Vehicle;
import com.carjunior.manageparking.domain.spec.search.VehicleSearch;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static com.carjunior.manageparking.domain.utils.CriteriaUtils.formatQueryToLike;
import static com.carjunior.manageparking.domain.utils.CriteriaUtils.lowerAndUnaccented;
import static com.carjunior.manageparking.domain.utils.Utility.isPresent;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public final class VehicleSpecification {
    private VehicleSpecification() {
    }

    public static Specification<Vehicle> getAll(VehicleSearch vehicleSearch) {
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (isNotEmpty(vehicleSearch.query())) {
                predicates.add(
                        builder.or(
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("mark")),
                                        formatQueryToLike(vehicleSearch.query())
                                ),
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("model")),
                                        formatQueryToLike(vehicleSearch.query())
                                ),
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("plate")),
                                        formatQueryToLike(vehicleSearch.query())
                                )
                        )
                );
            }

            if (isPresent(vehicleSearch.type()))
                predicates.add(builder.equal(root.get("type"), vehicleSearch.type()));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

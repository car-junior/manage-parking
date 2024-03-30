package com.carjunior.manageparking.domain.spec;

import com.carjunior.manageparking.domain.entity.ParkingControl;
import com.carjunior.manageparking.domain.spec.search.ParkingControlSearch;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static com.carjunior.manageparking.domain.utils.Utility.isEmpty;
import static com.carjunior.manageparking.domain.utils.Utility.isPresent;

public final class ParkingControlSpecification {
    private ParkingControlSpecification() {
    }

    public static Specification<ParkingControl> getAllParkingControlEntrance(ParkingControlSearch parkingControlSearch) {
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (isEmpty(parkingControlSearch.startAt()) && isEmpty(parkingControlSearch.endAt()))
                predicates.add(root.get("entryDateTime").isNotNull());
            else {
                if (isPresent(parkingControlSearch.startAt()))
                    predicates.add(builder.greaterThanOrEqualTo(root.get("entryDateTime"), parkingControlSearch.startAt()));

                if (isPresent(parkingControlSearch.endAt()))
                    predicates.add(builder.lessThanOrEqualTo(root.get("entryDateTime"), parkingControlSearch.endAt()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<ParkingControl> getAllParkingControlExit(ParkingControlSearch parkingControlSearch) {
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (isEmpty(parkingControlSearch.startAt()) && isEmpty(parkingControlSearch.endAt()))
                predicates.add(root.get("exitDateTime").isNotNull());
            else {
                if (isPresent(parkingControlSearch.startAt()))
                    predicates.add(builder.greaterThanOrEqualTo(root.get("exitDateTime"), parkingControlSearch.startAt()));

                if (isPresent(parkingControlSearch.endAt()))
                    predicates.add(builder.lessThanOrEqualTo(root.get("exitDateTime"), parkingControlSearch.endAt()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

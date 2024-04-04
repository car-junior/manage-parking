package com.carjunior.manageparking.domain.spec;

import com.carjunior.manageparking.domain.entity.Role;
import com.carjunior.manageparking.domain.spec.search.RoleSearch;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static com.carjunior.manageparking.domain.utils.CriteriaUtils.formatQueryToLike;
import static com.carjunior.manageparking.domain.utils.CriteriaUtils.lowerAndUnaccented;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public final class RoleSpecification {
    private RoleSpecification() {
    }

    public static Specification<Role> getAll(RoleSearch roleSearch) {
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (isNotEmpty(roleSearch.query())) {
                predicates.add(
                        builder.or(
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("name")),
                                        formatQueryToLike(roleSearch.query())
                                ),
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("type")),
                                        formatQueryToLike(roleSearch.query())
                                )
                        )
                );
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

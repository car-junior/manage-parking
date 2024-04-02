package com.carjunior.manageparking.domain.spec;

import com.carjunior.manageparking.domain.entity.User;
import com.carjunior.manageparking.domain.spec.search.UserSearch;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static com.carjunior.manageparking.domain.utils.CriteriaUtils.formatQueryToLike;
import static com.carjunior.manageparking.domain.utils.CriteriaUtils.lowerAndUnaccented;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public final class UserSpecification {
    private UserSpecification() {
    }

    public static Specification<User> getAll(UserSearch userSearch) {
        return (root, query, builder) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (isNotEmpty(userSearch.query())) {
                predicates.add(
                        builder.or(
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("name")),
                                        formatQueryToLike(userSearch.query())
                                ),
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("surname")),
                                        formatQueryToLike(userSearch.query())
                                ),
                                builder.like(
                                        lowerAndUnaccented(builder, root.get("email")),
                                        formatQueryToLike(userSearch.query())
                                )
                        )
                );
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

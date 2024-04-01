package com.carjunior.manageparking.domain.repository.projections.parkingcontrol;

public interface SummaryPerHour {
    String getHour();

    SummaryType getType();

    int getQuantity();
    enum SummaryType {
        ENTRANCE,
        EXIT
    }
}

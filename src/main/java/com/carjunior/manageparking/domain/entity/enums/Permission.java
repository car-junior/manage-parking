package com.carjunior.manageparking.domain.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority {

    PARKING_CREATE,
    PARKING_UPDATE,
    PARKING_LIST,
    PARKING_DETAIL,

    VEHICLE_CREATE,
    VEHICLE_UPDATE,
    VEHICLE_LIST,
    VEHICLE_DETAIL,

    USER_CREATE,
    USER_UPDATE,
    USER_LIST,
    USER_DETAIL,
    USER_CHANGE_STATUS,

    PARKING_CONTROL_VEHICLE_ENTRANCE,
    PARKING_CONTROL_FINISH_VEHICLE_ENTRANCE,
    PARKING_CONTROL_SUMMARY,
    PARKING_CONTROL_SUMMARY_PER_HOUR;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
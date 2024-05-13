package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.PageResult;
import com.carjunior.manageparking.domain.dto.user.UserCreateUpdateDto;
import com.carjunior.manageparking.domain.dto.user.UserDetailDto;
import com.carjunior.manageparking.domain.entity.User;
import com.carjunior.manageparking.domain.entity.enums.UserStatus;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import com.carjunior.manageparking.domain.service.UserService;
import com.carjunior.manageparking.domain.spec.search.UserSearch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.carjunior.manageparking.domain.utils.Utility.createPagination;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER_CREATE')")
    public ResponseEntity<UserDetailDto> create(@Valid @RequestBody UserCreateUpdateDto userCreate) {
        var user = userService.saveUser(modelMapperService.toObject(User.class, userCreate));
        return ResponseEntity.ok(modelMapperService.toObject(UserDetailDto.class, user));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('USER_UPDATE')")
    public ResponseEntity<UserDetailDto> update(@PathVariable(name = "userId") long userId,
                                                @Valid @RequestBody UserCreateUpdateDto userUpdate) {
        var user = modelMapperService.toObject(User.class, userUpdate)
                .toBuilder()
                .id(userId)
                .build();
        return ResponseEntity
                .ok(modelMapperService.toObject(UserDetailDto.class, userService.updateUser(user)));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('USER_DETAIL')")
    public ResponseEntity<UserDetailDto> getById(@PathVariable(name = "userId") long userId) {
        return ResponseEntity
                .ok(modelMapperService.toObject(UserDetailDto.class, userService.getUserById(userId)));
    }

    @PatchMapping("/{userId}/change-status")
    public ResponseEntity<Void> changeStatus(@PathVariable(name = "userId") long userId,
                                             @RequestParam(name = "status") UserStatus status) {
        userService.changeStatus(userId, status);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER_LIST')")
    public ResponseEntity<PageResult<UserDetailDto>> getAll(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int itemsPerPage,
            @RequestParam(required = false, defaultValue = "ASC") String sort,
            @RequestParam(required = false, defaultValue = "id") String sortName) {
        var userSearch = UserSearch.builder()
                .query(query)
                .build();
        var pagination = createPagination(page, itemsPerPage, sort, sortName);
        var result = userService.getAllUser(userSearch, pagination);
        return ResponseEntity.ok(modelMapperService.toPage(UserDetailDto.class, result));
    }


}

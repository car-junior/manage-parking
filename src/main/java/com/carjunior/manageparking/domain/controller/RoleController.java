package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.role.RoleDetailDto;
import com.carjunior.manageparking.domain.dto.role.createupdate.RoleCreateUpdateDto;
import com.carjunior.manageparking.domain.entity.Role;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import com.carjunior.manageparking.domain.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
    private final ModelMapperService modelMapperService;

    @PostMapping
    public ResponseEntity<RoleDetailDto> create(@Valid @RequestBody RoleCreateUpdateDto roleCreate) {
        var role = roleService.saveRole(modelMapperService.toObject(Role.class, roleCreate));
        return ResponseEntity.ok(modelMapperService.toObject(RoleDetailDto.class, role));
    }

    //TODO: No token terá id do estacionamento com isso toda operação que for feita para o mesmo
    // pegarei o id presente no token e buscarei no banco de dados
    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDetailDto> getById(@PathVariable(name = "roleId") long roleId) {
        return ResponseEntity
                .ok(modelMapperService.toObject(RoleDetailDto.class, roleService.getRoleById(roleId)));
    }

//    @PutMapping("/{userId}")
//    public ResponseEntity<UserDetailDto> update(@PathVariable(name = "userId") Long userId,
//                                                @Valid @RequestBody UserCreateUpdateDto userUpdate) {
//        var user = modelMapperService.toObject(User.class, userUpdate)
//                .toBuilder()
//                .id(userId)
//                .build();
//        return ResponseEntity
//                .ok(modelMapperService.toObject(UserDetailDto.class, userService.updateUser(user)));
//    }
//

//
//    @PatchMapping("/{userId}/change-status")
//    public ResponseEntity<Void> changeStatus(@PathVariable(name = "userId") Long userId,
//                                             @RequestParam(name = "status") UserStatus status) {
//        userService.changeStatus(userId, status);
//        return ResponseEntity.noContent().build();
//    }
//
//    // Adicionar depois o ID do estacionamento como TENNET
//    @GetMapping
//    public ResponseEntity<PageResult<UserDetailDto>> getAll(
//            @RequestParam(name = "q", required = false) String query,
//            @RequestParam(required = false, defaultValue = "0") int page,
//            @RequestParam(required = false, defaultValue = "10") int itemsPerPage,
//            @RequestParam(required = false, defaultValue = "ASC") String sort,
//            @RequestParam(required = false, defaultValue = "id") String sortName) {
//        var userSearch = UserSearch.builder()
//                .query(query)
//                .build();
//        var pagination = createPagination(page, itemsPerPage, sort, sortName);
//        var result = userService.getAllUser(userSearch, pagination);
//        return ResponseEntity.ok(modelMapperService.toPage(UserDetailDto.class, result));
//    }


}

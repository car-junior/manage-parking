package com.carjunior.manageparking.domain.controller;

import com.carjunior.manageparking.domain.dto.PageResult;
import com.carjunior.manageparking.domain.dto.role.RoleDetailDto;
import com.carjunior.manageparking.domain.dto.role.createupdate.RoleCreateUpdateDto;
import com.carjunior.manageparking.domain.entity.Role;
import com.carjunior.manageparking.domain.service.ModelMapperService;
import com.carjunior.manageparking.domain.service.RoleService;
import com.carjunior.manageparking.domain.spec.search.RoleSearch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.carjunior.manageparking.domain.utils.Utility.createPagination;

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

        @PutMapping("/{roleId}")
    public ResponseEntity<RoleDetailDto> update(@PathVariable(name = "roleId") long roleId,
                                                @Valid @RequestBody RoleCreateUpdateDto roleUpdate) {
        var role = modelMapperService.toObject(Role.class, roleUpdate)
                .toBuilder()
                .id(roleId)
                .build();
        return ResponseEntity
                .ok(modelMapperService.toObject(RoleDetailDto.class, roleService.updateRole(role)));
    }

    //TODO: No token terá id do estacionamento com isso toda operação que for feita para o mesmo
    // pegarei o id presente no token e buscarei no banco de dados
    @GetMapping("/{roleId}")
    public ResponseEntity<RoleDetailDto> getById(@PathVariable(name = "roleId") long roleId) {
        return ResponseEntity
                .ok(modelMapperService.toObject(RoleDetailDto.class, roleService.getRoleById(roleId)));
    }

    // Adicionar depois o ID do estacionamento como TENNET
    @GetMapping
    public ResponseEntity<PageResult<RoleDetailDto>> getAll(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int itemsPerPage,
            @RequestParam(required = false, defaultValue = "ASC") String sort,
            @RequestParam(required = false, defaultValue = "id") String sortName) {
        var roleSearch = RoleSearch.builder()
                .query(query)
                .build();
        var pagination = createPagination(page, itemsPerPage, sort, sortName);
        var result = roleService.getAllRole(roleSearch, pagination);
        return ResponseEntity.ok(modelMapperService.toPage(RoleDetailDto.class, result));
    }

}

package com.home.employeemanagement.controllers.dtoController.modelMapper;

import com.home.employeemanagement.dto.UserMMDto;
import com.home.employeemanagement.model.UserAccount;
import com.home.employeemanagement.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/mm")
public class UserModelMapperController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserModelMapperController(UserService userService,
                                     ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public UserMMDto getUserById(@PathVariable Long id) {
        UserAccount user = userService.getUserById(id);
        //Model Mapper Conversion
        UserMMDto userMMDto = modelMapper.map(user, UserMMDto.class);
        return userMMDto;
    }
}

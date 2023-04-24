package com.dts.intechweb.commands;

import com.dts.intechweb.validation.UniqueUsername;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
public class UserCommand {
    @NotBlank
    @Size(min = 3, max = 50)
    @UniqueUsername
    private String username;
    @NotBlank
    @Size(min = 3, max = 50)
    private String password;

}

package minho.springserver.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PatchPasswordForm {
    @NotBlank
    private String currentPassword;

    @NotBlank
    private String newPassword;
}

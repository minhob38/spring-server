package minho.springserver.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Data
public class PatchPasswordForm {
    @NotBlank(message = "current password is required")
    @Length(max = 10)
    private String currentPassword;

    @NotBlank
    @Length(max = 10)
    private String newPassword;
}

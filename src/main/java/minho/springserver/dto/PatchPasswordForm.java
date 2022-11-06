package minho.springserver.dto;

import lombok.Data;

@Data
public class PatchPasswordForm {
    private String currentPassword;
    private String newPassword;
}

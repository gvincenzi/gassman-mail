package org.gassman.mail.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String mail;
    private Boolean active = Boolean.TRUE;

    public String toHTTPQuery(String prefix) {
        return prefix + ".id=" + id;
    }
}

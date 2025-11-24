package com.gestfood.gestfood.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO extends AbstractUserDTO {
    private String phoneNumber;
    private String address;
}

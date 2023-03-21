package com.example.jpa.data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeProductNameDto {
    private Long number;
    private String name;
}

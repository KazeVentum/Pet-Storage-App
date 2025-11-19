package com.uniminuto.biblioteca.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RazaInventarioDTO {
    private String nombreRaza;
    private Long totalStock;
}

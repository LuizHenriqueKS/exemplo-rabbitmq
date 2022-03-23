package br.com.exemplorabbitmq.lib.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrecoDto {

    private String codigoProduto;
    private double preco;

}

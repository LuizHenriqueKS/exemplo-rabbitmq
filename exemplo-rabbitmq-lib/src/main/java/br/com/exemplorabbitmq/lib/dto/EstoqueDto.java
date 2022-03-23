package br.com.exemplorabbitmq.lib.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueDto {

    private String codigoProduto;
    private int quantidade;

}

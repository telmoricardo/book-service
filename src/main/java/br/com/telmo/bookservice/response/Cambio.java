package br.com.telmo.bookservice.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class Cambio {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionFactor;
    private Double converterdValue;
    private String enviroment;
}

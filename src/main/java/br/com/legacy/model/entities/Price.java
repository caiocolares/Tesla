package br.com.legacy.model.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Entity
@Table(name = "vw_price")
@Data
@Builder
public class Price {

	@EmbeddedId
    private PriceId id ;
    
    @Column(name = "nr_price")
    private BigDecimal price;
    
    @Tolerate
    private Price(){};
}

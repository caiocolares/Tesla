package br.com.legacy.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Builder
@Data
@EqualsAndHashCode(of="id")

@Entity
@Table(name = "vw_shop")
public class Shop {
    
	@Id
    @Column(name = "ci_shop")
    private Integer id;
    
    @Column(name="cd_enterprise")
    private Integer enterprise;
    
    @Column(name = "ds_legal_name")
    private String legalName;
    
    @Column(name = "ds_fantasy")
    private String fantasy;
    
    @Column(name="nr_cnpj")
    private String cnpj;     

}

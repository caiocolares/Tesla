package br.com.legacy.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access=AccessLevel.PRIVATE)
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@Data
@Builder
@Entity
@Table(name = "vw_customer")
public class Customer {

	public enum CustomerType{
        PADRAO,REPRESENTANTES,ABERTO;
    }

    @Id
    @Column(name="ci_customer")
    private Integer id;    
    @Column(name="nm_customer")
    private String name;
    @Column(name="nr_cpf")
    private String cpf;
    @Column(name = "fl_type")
    @Enumerated(EnumType.STRING)
    private CustomerType type = CustomerType.PADRAO;
    
    @ManyToOne
    @JoinColumn(name = "cd_broker",referencedColumnName = "ci_broker")
    private Broker broker ;
    
}

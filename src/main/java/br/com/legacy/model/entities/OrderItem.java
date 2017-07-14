package br.com.legacy.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="tb_order_item")
public class OrderItem{
	
	@Id
    @Column(name="ci_order_item")
    private Integer id;
   
    @ManyToOne
    @JoinColumn(name = "cd_tag",referencedColumnName = "ci_tag")
    private RfidTag tag ;            

    @ManyToOne
    @JoinColumn(name = "cd_order",referencedColumnName = "ci_order")
    private Order order;
    
}

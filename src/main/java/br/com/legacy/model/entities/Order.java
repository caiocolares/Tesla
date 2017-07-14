package br.com.legacy.model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "tb_order")
public class Order {
 
	@Id
    @Column(name="ci_order")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cd_seller",referencedColumnName = "ci_seller")
    private Seller seller;
    @ManyToOne
    @JoinColumn(name = "cd_customer",referencedColumnName = "ci_customer")
    private Customer customer ;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items ;
    @ManyToOne
    @JoinColumn(name = "cd_price_table",referencedColumnName = "ci_price_table")
    private PriceTable priceTable ;
    @Temporal(TemporalType.DATE)
    @Column(name = "dt_order")
    private Date dateOrder;
    
}

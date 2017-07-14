package br.com.legacy.model.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

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
@Table(name="tb_budget")
public class Budget {
    
	public enum BudgetStatus{
        A_RECEBER,RECEBIDO,CANCELADO;
    }
        
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ci_budget")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="cd_customer",referencedColumnName = "ci_customer")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "ci_shop",name = "cd_shop")
    private Shop shop ;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "ci_price_table",name = "cd_price_table")
    private PriceTable priceTable;
    
    @ManyToOne
    @JoinColumn(referencedColumnName = "ci_seller",name = "cd_seller")
    private Seller seller;

    @ManyToOne
    @JoinColumn(referencedColumnName = "ci_broker",name = "cd_broker")
    private Broker broker ;
    
    @OneToMany(mappedBy = "budget",cascade = CascadeType.PERSIST)
    private List<BudgetItem> items = new ArrayList<>();
    
    @Column(name = "dt_budget")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateBudget = new Date();
    
    @Column(name="nr_value")
    private BigDecimal value = BigDecimal.ZERO;
    
    @Column(name = "nr_code")
    private Integer code;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "fl_status")
    private BudgetStatus budgetStatus = BudgetStatus.A_RECEBER;

    @Enumerated(EnumType.STRING)
    @Column(name="fl_type")
    private ActionType type = ActionType.VENDA;
    
    @Column(name = "cd_consignment")
    private Integer consignment;
}

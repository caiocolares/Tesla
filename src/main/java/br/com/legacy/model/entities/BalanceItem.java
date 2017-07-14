package br.com.legacy.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "tb_balance_item")
public class BalanceItem {
    
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ci_balance_item")
    private Integer id;    
    @ManyToOne
    @JoinColumn(name = "cd_balance_lot",referencedColumnName = "ci_balance_lot")
    private BalanceLot lot;    
    @OneToOne
    @JoinColumn(name = "cd_tag",referencedColumnName = "ci_tag")
    private RfidTag tag;
}

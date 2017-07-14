package br.com.legacy.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "tb_balance_lot")
public class BalanceLot{
    
	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ci_balance_lot")
    private Integer id;
    @Column(name = "nr_lot")
    private Integer sequential;
    @Column(name = "ds_location")
    private String location;    
    @OneToMany(mappedBy = "lot",cascade = CascadeType.PERSIST)
    private List<BalanceItem> items;
    
    @ManyToOne
    @JoinColumn(name = "cd_balance",referencedColumnName = "ci_balance")
    private Balance balance;
}
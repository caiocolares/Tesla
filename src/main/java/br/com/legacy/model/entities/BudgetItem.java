package br.com.legacy.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tb_budget_item")
public class BudgetItem {

	public enum BudgetItemType{
        SAIDA,ENTRADA;
    }
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ci_budget_item")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "cd_budget", referencedColumnName = "ci_budget")
    private Budget budget ;
    
    @ManyToOne
    @JoinColumn(name = "cd_shop",referencedColumnName = "ci_shop")
    private Shop shop;
    
    @ManyToOne
    @JoinColumn(name = "cd_tag",referencedColumnName = "ci_tag")
    private RfidTag tag ;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "fl_type")
    private BudgetItemType type;

}

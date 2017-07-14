package br.com.legacy.model.entities;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
@Table(name = "tb_balance")
public class Balance {
    
   	public enum BalanceStatus{
        ABERTO,FECHADO;
    }
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ci_balance")
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "dt_balance")
    private Date date ;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "fl_status")
    private BalanceStatus status = BalanceStatus.ABERTO;
    
    @OneToMany(mappedBy = "balance",cascade = CascadeType.PERSIST)
    private List<BalanceLot> lots;
    
    @Column(name = "nr_balance")
    private Integer code ;
    
    @Transient
    private List<RfidTag> tags;
   
}

package br.com.legacy.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
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
@Table(name = "tb_tag")
@NamedQuery(name="User.findById",
			query="select t from RfidTag t join fetch t.product "
					+ "                    join fetch t.color "
					+ "                    join fetch t.product.sizes "
			    // "                        join fetch t.product.colors "
				+ " where t.id = :id")
public class RfidTag {
    

	@Id
    @Column(name="ci_tag")
    private String id; 
    
    @ManyToOne
    @JoinColumn(name = "cd_product",referencedColumnName = "ci_product")
    private Product product;
    
    @ManyToOne
    @JoinColumn(name = "cd_color",referencedColumnName = "ci_color")
    private Color color ;
        
    @Column(name="cd_size")
    private String size;
    
    @Column(name = "fl_status")
    @Enumerated(EnumType.STRING)
    private TagStatus status = TagStatus.PENDENTE;
        
    @Column(name="nr_launch")
    private Long dateLaunch ;
    
    @Column(name = "nr_consignment")
    private Integer consignment;
}

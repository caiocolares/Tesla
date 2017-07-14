package br.com.legacy.model.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OrderColumn;
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
@Table(name = "vw_product")
@NamedQuery(name="Produc.findById",query="select p from Product p join fetch p.colors join fetch p.sizes where p.id = :id")
public class Product{

	
	@Id
    @Column(name = "ci_product")
    private Integer id;

    @Column(name="ds_product")
    private String name;
    @Column(name="ds_image")
    private String image;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vw_product_color",
            inverseJoinColumns = {@JoinColumn(name = "cd_color",referencedColumnName = "ci_color")},
            joinColumns = @JoinColumn(name = "cd_product",referencedColumnName = "ci_product"))
    private Set<Color> colors = new HashSet<>();
    
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "vw_product_size",
                     joinColumns ={ @JoinColumn(name = "cd_product",referencedColumnName = "ci_product")})
    @OrderColumn(name = "nr_sequencial")
    @Column(name = "cd_size")
    private Set<String> sizes = new HashSet<>();
    
}
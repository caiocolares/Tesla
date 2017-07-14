package br.com.legacy.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PriceId implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "cd_price_table")
    private Integer priceTableId;
    
    @Column(name="cd_product")
    private Integer productId;

    public Integer getPriceTableId() {
        return priceTableId;
    }

    public void setPriceTableId(Integer priceTableId) {
        this.priceTableId = priceTableId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((priceTableId == null) ? 0 : priceTableId.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceId other = (PriceId) obj;
		if (priceTableId == null) {
			if (other.priceTableId != null)
				return false;
		} else if (!priceTableId.equals(other.priceTableId))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

    

}

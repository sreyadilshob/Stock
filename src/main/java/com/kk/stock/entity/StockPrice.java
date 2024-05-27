package com.kk.stock.entity;

import java.time.LocalDate;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class StockPrice {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate date;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Double volume;
	
	@JsonIgnore
	@ManyToOne
	private Stock stock; 
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Double getOpen() {
		return open;
	}
	public void setOpen(Double open) {
		this.open = open;
	}
	public Double getHigh() {
		return high;
	}
	public void setHigh(Double high) {
		this.high = high;
	}
	public Double getLow() {
		return low;
	}
	public void setLow(Double low) {
		this.low = low;
	}
	public Double getClose() {
		return close;
	}
	public void setClose(Double close) {
		this.close = close;
	}
	public Double getVolume() {
		return volume;
	}
	public void setVolume(Double volume) {
		this.volume = volume;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
	    return new HashCodeBuilder()
	            .append(date)
	            .append(open)
	            .append(high)
	            .append(low)
	            .append(close)
	            .append(volume)
	            .toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof StockPrice) {
	        final StockPrice spobj = (StockPrice) obj;

	        return new EqualsBuilder()
	                .append(date, spobj.date)
	                .append(open, spobj.open)
	                .append(low, spobj.low)
	                .append(high, spobj.high)
	                .append(close, spobj.close)
	                .append(volume, spobj.volume)
	                .isEquals();
	    } else {
	        return false;
	    }
	}
}

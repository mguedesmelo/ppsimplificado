package br.com.pp.simplificado.model.data;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8381167557140269677L;

	@ManyToOne
	@JoinColumn(name = "payer_id", nullable = false)
	private User payer;

	@ManyToOne
	@JoinColumn(name = "payee_id", nullable = false)
	private User payee;

	@Column(name = "ammount")
	private BigDecimal ammount;
}

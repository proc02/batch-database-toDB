package com.tfa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "BANK")
@Builder
public class TransactionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "USERNUM", nullable = false)
	private String numeroPersonne;
	@Column(name = "CARDNUM", nullable = false)
	private String numeroCarte;
	@Column(name = "COMPTENUM", nullable = false)
	private String numeroCompte;
	@Column(name = "CODESTATUS", nullable = false)
	private String codeStatus;
	@Column(name = "SOLDE", nullable = false)
	private double solde;
}

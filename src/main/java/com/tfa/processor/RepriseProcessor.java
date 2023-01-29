package com.tfa.processor;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.tfa.dao.RepriseRepository;
import com.tfa.entity.RepriseEntity;
import com.tfa.entity.TransactionEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class RepriseProcessor implements ItemProcessor<RepriseEntity, TransactionEntity> {

	private final RepriseRepository repository;

	@Override
	@Nullable
	public TransactionEntity process(@NonNull RepriseEntity reprise) throws Exception {
		log.info("Données reçues de la base de données {}", reprise);
		float nouveauSolde = RandomUtils.nextFloat(0, 1000);
		if (nouveauSolde < 100) {
			reprise.setSolde(nouveauSolde);
			reprise.setNbrReprise(reprise.getNbrReprise() + 1);
			repository.save(reprise);
			return null;
		}
		// repository.delete(reprise);
		return TransactionEntity.builder()
				.codeStatus(reprise.getCodeStatus())
				.numeroCarte(reprise.getNumeroCarte())
				.numeroCompte(reprise.getNumeroCompte())
				.numeroPersonne(reprise.getNumeroPersonne())
				.solde(nouveauSolde)
				.build();
	}

}

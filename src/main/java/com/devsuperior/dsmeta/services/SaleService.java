package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleSummaryDTO> getSalesSummary(LocalDate minDate, LocalDate maxDate) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate result = today.minusYears(1L);

		if (minDate == null) {
			minDate = result;
		}
		if (maxDate == null) {
			maxDate = today;
		}
		return repository.getSalesSummary(minDate, maxDate);
	}

	public Page<SaleReportDTO> getSaleReport(LocalDate minDate, LocalDate maxDate, Pageable pageable) {
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate result = today.minusYears(1L); // Data de 12 meses atrás

		if (minDate == null) {
			minDate = result;
		}
		if (maxDate == null) {
			maxDate = today;
		}

		return repository.getSaleReport( minDate,maxDate, pageable);
	}




}


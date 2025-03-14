package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleSummaryDTO (obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY obj.seller.name ")
    List<SaleSummaryDTO> getSalesSummary(@Param("minDate") LocalDate minDate, @Param("maxDate")LocalDate maxDate);


   /* @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO(obj.seller.name, SUM(obj.amount)) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "AND (:sellerName = '' OR obj.seller.name = :sellerName) " +
            "GROUP BY obj.seller.name ")
    Page<SaleReportDTO> getSaleReport(@Param("minDate") LocalDate minDate, @Param("maxDate")LocalDate maxDate, @Param("sellerName") String sellerName, Pageable pageable);
*/

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleReportDTO (obj.id, obj.date, obj.amount, obj.seller.name) " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " )
    Page<SaleReportDTO>getSaleReport(@Param("minDate") LocalDate minDate, @Param("maxDate")LocalDate maxDate, Pageable pageable);







}

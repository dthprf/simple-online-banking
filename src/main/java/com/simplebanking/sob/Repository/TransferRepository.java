package com.simplebanking.sob.Repository;

import com.simplebanking.sob.Model.TransferImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferRepository extends JpaRepository<TransferImpl, Long> {

    @Query(value = "SELECT DISTINCT * FROM transfer tra " +
            "WHERE tra.target_account_id = :accountId OR tra.source_account_id = :accountId",
            nativeQuery = true)
    List<TransferImpl> findTransactions(@Param("accountId") Long accountId);
}

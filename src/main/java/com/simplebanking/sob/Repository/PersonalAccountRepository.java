package com.simplebanking.sob.Repository;

import com.simplebanking.sob.Model.PersonalAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalAccountRepository extends JpaRepository<PersonalAccount, Long> {

    PersonalAccount getPersonalAccountByAccountId(Long accountId);
}

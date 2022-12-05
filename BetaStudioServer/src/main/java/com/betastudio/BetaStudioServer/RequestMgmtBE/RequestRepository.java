package com.betastudio.BetaStudioServer.RequestMgmtBE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //indica che questa interfaccia è responsabile dell'accesso ai dati nel DB (Data Layer),
//vogliamo usarla nel nostro Service Layer
public interface RequestRepository extends JpaRepository<Request, Long> {

}
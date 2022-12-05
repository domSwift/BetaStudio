package com.betastudio.BetaStudioServer.UserMgmtBE.UserMgmtBEdoctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //indica che questa interfaccia è responsabile dell'accesso ai dati nel DB (Data Layer),
            //vogliamo usarla nel nostro Service Layer
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}

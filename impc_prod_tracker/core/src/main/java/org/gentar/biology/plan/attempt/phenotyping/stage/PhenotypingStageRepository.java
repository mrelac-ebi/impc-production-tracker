package org.gentar.biology.plan.attempt.phenotyping.stage;

import org.gentar.biology.plan.attempt.phenotyping.PhenotypingAttempt;
import org.gentar.biology.plan.attempt.phenotyping.stage.type.PhenotypingStageType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PhenotypingStageRepository extends CrudRepository<PhenotypingStage, Long>
{
    @Query("SELECT max(ps.psn) FROM PhenotypingStage ps")
    String getMaxPsn();

    PhenotypingStage findByPsn(String psn);

    List<PhenotypingStage> findAll();

    List<PhenotypingStage> findAllByPhenotypingAttemptAndPhenotypingStageType(
        PhenotypingAttempt phenotypingAttempt, PhenotypingStageType phenotypingStageType);
}

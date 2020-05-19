package org.gentar.biology.plan;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.gentar.biology.plan.attempt.breeding.BreedingAttemptDTO;
import org.gentar.biology.plan.attempt.crispr.CrisprAttemptDTO;
import org.gentar.biology.plan.attempt.phenotyping.PhenotypingAttemptDTO;
import org.gentar.biology.plan.plan_starting_point.PlanStartingPointDTO;
import org.gentar.util.JsonHelper;
import org.junit.jupiter.api.Test;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

class PlanBasicDataDTOTest
{
    @Test
    public void testCrisprPlanBasicDataDTO() throws JsonProcessingException
    {
        PlanBasicDataDTO planBasicDataDTO = new PlanBasicDataDTO();
        planBasicDataDTO.setPlanCommonDataDTO(new PlanCommonDataDTO());
        CrisprAttemptDTO crisprAttemptDTO = new CrisprAttemptDTO();
        planBasicDataDTO.setCrisprAttemptDTO(crisprAttemptDTO);

        String json = JsonHelper.toJson(planBasicDataDTO);
        assertThat(json, is(notNullValue()));
        assertThat(json, is("{\"funderNames\":null,\"workUnitName\":null,\"workGroupName\":null," +
            "\"comment\":null,\"productsAvailableForGeneralPublic\":null," +
            "\"crisprAttempt\":{\"miDate\":null,\"experimental\":null,\"comment\":null," +
            "\"mutagenesisExternalRef\":null,\"attemptExternalRef\":null,\"nucleases\":null," +
            "\"guides\":null,\"mutagenesisDonors\":null,\"reagents\":null,\"genotypePrimers\":null," +
            "\"totalEmbryosInjected\":null,\"totalEmbryosSurvived\":null,\"embryo2Cell\":null," +
            "\"assay\":null,\"strainInjectedName\":null}}"));
    }

    @Test
    public void testBreedingPlanBasicDataDTO() throws JsonProcessingException
    {
        PlanBasicDataDTO planBasicDataDTO = new PlanBasicDataDTO();
        planBasicDataDTO.setPlanCommonDataDTO(new PlanCommonDataDTO());
        BreedingAttemptDTO breedingAttemptDTO = new BreedingAttemptDTO();
        planBasicDataDTO.setBreedingAttemptDTO(breedingAttemptDTO);
        planBasicDataDTO.setPlanStartingPointDTOS(Collections.singletonList(new PlanStartingPointDTO()));

        String json = JsonHelper.toJson(planBasicDataDTO);
        assertThat(json, is(notNullValue()));
        assertThat(json, is("{\"funderNames\":null,\"workUnitName\":null,\"workGroupName\":null," +
            "\"comment\":null,\"productsAvailableForGeneralPublic\":null," +
            "\"BreedingStartingPoints\":[{\"outcomeTpo\":null}]," +
            "\"breedingAttempt\":{\"numberOfCreMatingsStarted\":null," +
            "\"numberOfCreMatingsSuccessful\":null,\"creExcesion\":null,\"tatCre\":null," +
            "\"deleterStrainName\":null}}"));
    }

    @Test
    public void testPhenotypingPlanBasicDataDTO() throws JsonProcessingException
    {
        PlanBasicDataDTO planBasicDataDTO = new PlanBasicDataDTO();
        planBasicDataDTO.setPlanCommonDataDTO(new PlanCommonDataDTO());
        PhenotypingAttemptDTO phenotypingAttemptDTO = new PhenotypingAttemptDTO();
        planBasicDataDTO.setPhenotypingAttemptDTO(phenotypingAttemptDTO);
        planBasicDataDTO.setPlanStartingPointDTO(new PlanStartingPointDTO());

        String json = JsonHelper.toJson(planBasicDataDTO);

        assertThat(json, is(notNullValue()));
        assertThat(json, is("{\"funderNames\":null,\"workUnitName\":null,\"workGroupName\":null," +
            "\"comment\":null,\"productsAvailableForGeneralPublic\":null," +
            "\"phenotypingStartingPoint\":{\"outcomeTpo\":null}," +
            "\"phenotypingAttempt\":{\"phenotypingExternalRef\":null," +
            "\"phenotypingBackgroundStrainName\":null,\"phenotypingStages\":null}}"));
    }
}
package org.gentar.report.mgiPhenotypingColony.mutation;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MgiPhenotypingColonyReportMutationServiceImpl implements MgiPhenotypingColonyReportMutationService
{

    private final MgiPhenotypingColonyReportMutationRepository mgiPhenotypingColonyReportMutationRepository;

    public MgiPhenotypingColonyReportMutationServiceImpl(
            MgiPhenotypingColonyReportMutationRepository mgiPhenotypingColonyReportMutationRepository )
    {
        this.mgiPhenotypingColonyReportMutationRepository = mgiPhenotypingColonyReportMutationRepository;
    }


    public List<MgiPhenotypingColonyReportMutationGeneProjection> getSelectedMutationGeneProjections( List mutationIds){
        return mgiPhenotypingColonyReportMutationRepository.findSelectedMutationGeneProjections(mutationIds);
    }
}

package org.gentar.biology.plan.engine.crispr.processors;

import org.gentar.biology.plan.Plan;
import org.gentar.biology.plan.attempt.crispr.CrisprAttempt;
import org.gentar.biology.plan.engine.PlanStateSetter;
import org.gentar.statemachine.AbstractProcessor;
import org.gentar.statemachine.ProcessData;
import org.springframework.stereotype.Component;

@Component
public class EmbryosObtainedProcessor extends AbstractProcessor
{
    public EmbryosObtainedProcessor(PlanStateSetter planStateSetter)
    {
        super(planStateSetter);
    }

    @Override
    protected boolean canExecuteTransition(ProcessData entity)
    {
        return areThereEmbryos((Plan) entity);
    }

    private boolean areThereEmbryos(Plan plan)
    {
        boolean result = false;
        CrisprAttempt crisprAttempt = plan.getCrisprAttempt();
        if (crisprAttempt != null)
        {
            int totalEmbryosInjected =
                crisprAttempt.getTotalEmbryosInjected() == null ? 0 :
                    crisprAttempt.getTotalEmbryosInjected();
            int totalEmbryosSurvived =
                crisprAttempt.getTotalEmbryosSurvived() == null ? 0 :
                    crisprAttempt.getTotalEmbryosSurvived();
            result = totalEmbryosInjected > 0 || totalEmbryosSurvived > 0;
        }
        return result;
    }
}
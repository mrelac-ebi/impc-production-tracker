package uk.ac.ebi.impc_prod_tracker.common.diff;

import lombok.Data;
import org.junit.Test;

import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsNull.notNullValue;

public class PropertyEvaluatorTest
{
    private PropertyEvaluator propertyEvaluator;

    @Test
    public void testWithParentDataNull()
    {
        ClassA classA = new ClassA("a", "b");
        PropertyDefinition dataInput = new PropertyDefinition();
        dataInput.setName("p1");
        dataInput.setType(String.class);
        propertyEvaluator = new PropertyEvaluator(dataInput, classA, null);
        propertyEvaluator.evaluate();
        PropertyValueData dataOutput = propertyEvaluator.getData();

        assertThat("map:", dataOutput, is(notNullValue()));
        assertThat("map:", dataOutput.getName(), is("p1"));
        assertThat("map:", dataOutput.getType(), is(String.class));
        assertThat("map:", dataOutput.getValue(), is(notNullValue()));
        assertThat("map:", dataOutput.isSimpleValue(), is(true));
    }

    @Test
    public void testWithParentDataNotNull()
    {
        ClassA classA = new ClassA("a", "b");

        PropertyDefinition dataInput = new PropertyDefinition();
        dataInput.setName("p1");
        dataInput.setType(String.class);

        propertyEvaluator = new PropertyEvaluator(dataInput, classA, "parentPropertyName");
        propertyEvaluator.evaluate();
        PropertyValueData outputData = propertyEvaluator.getData();


        assertThat("map:", outputData, is(notNullValue()));
        assertThat("map:", outputData.getName(), is("parentPropertyName.p1"));
        assertThat("map:", outputData.getType(), is(String.class));
        assertThat("map:", outputData.getValue(), is(notNullValue()));
        assertThat("map:", outputData.isSimpleValue(), is(true));
    }

    @Test
    public void testSkipProperty()
    {
        ClassA classA = new ClassA("a", "b");

        PropertyDefinition dataInput = new PropertyDefinition();
        dataInput.setName("p3");
        dataInput.setType(List.class);

        propertyEvaluator = new PropertyEvaluator(dataInput, classA, null);
        propertyEvaluator.evaluate();
        PropertyValueData dataOutput = propertyEvaluator.getData();
        System.out.println(dataOutput);

        assertThat("map:", dataOutput, is(nullValue()));
    }

    @Data
    public static class ClassA
    {
        private String p1;
        private String p2;
        private List<String> p3;

        ClassA(String p1, String p2)
        {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
}
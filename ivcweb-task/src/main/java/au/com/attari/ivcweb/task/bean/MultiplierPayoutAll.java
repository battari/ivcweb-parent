/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package au.com.attari.ivcweb.task.bean;

import java.math.BigDecimal;

/**
 *
 * @author battari
 */
public class MultiplierPayoutAll {

    // Row 0 is investor's after tax required return
    // Column 0 is companies return on equity
    public static BigDecimal[][] multipliers = {
    	
        {  new BigDecimal("0.00"),  new BigDecimal("0.08"),  new BigDecimal("0.09"),  new BigDecimal("0.10"),  new BigDecimal("0.11"),  new BigDecimal("0.12"),  new BigDecimal("0.13"),  new BigDecimal("0.14")  },
        {  new BigDecimal("0.05"), new BigDecimal("0.625"), new BigDecimal("0.556"), new BigDecimal("0.500"), new BigDecimal("0.455"), new BigDecimal("0.427"), new BigDecimal("0.358"), new BigDecimal("0.357")  },
        { new BigDecimal("0.075"), new BigDecimal("0.938"), new BigDecimal("0.833"), new BigDecimal("0.75") , new BigDecimal("0.682"), new BigDecimal("0.625"), new BigDecimal("0.577"), new BigDecimal("0.538")  },
        { new BigDecimal("0.100"), new BigDecimal("1.250"), new BigDecimal("1.110"), new BigDecimal("1.00") , new BigDecimal("0.909"), new BigDecimal("0.833"), new BigDecimal("0.769"), new BigDecimal("0.714")  },
        { new BigDecimal("0.125"), new BigDecimal("1.583"), new BigDecimal("1.389"), new BigDecimal("1.25") , new BigDecimal("1.136"), new BigDecimal("1.042"), new BigDecimal("0.962"), new BigDecimal("0.893")  },
        { new BigDecimal("0.150"), new BigDecimal("1.875"), new BigDecimal("1.667"), new BigDecimal("1.5")  , new BigDecimal("1.364"), new BigDecimal("1.25") , new BigDecimal("1.154"), new BigDecimal("1.071")  },
        { new BigDecimal("0.175"), new BigDecimal("2.188"), new BigDecimal("1.944"), new BigDecimal("1.75") , new BigDecimal("1.591"), new BigDecimal("1.458"), new BigDecimal("1.346"), new BigDecimal("1.25")   },
        { new BigDecimal("0.200"), new BigDecimal("2.500"), new BigDecimal("2.222"), new BigDecimal("2.000"), new BigDecimal("1.818"), new BigDecimal("1.667"), new BigDecimal("1.538"), new BigDecimal("1.429")  },
        { new BigDecimal("0.225"), new BigDecimal("2.813"), new BigDecimal("2.500"), new BigDecimal("2.250"), new BigDecimal("2.045"), new BigDecimal("1.875"), new BigDecimal("1.731"), new BigDecimal("1.607")  },
        { new BigDecimal("0.250"), new BigDecimal("3.125"), new BigDecimal("2.778"), new BigDecimal("2.500"), new BigDecimal("2.273"), new BigDecimal("2.083"), new BigDecimal("1.923"), new BigDecimal("1.786")  },
        { new BigDecimal("0.275"), new BigDecimal("3.438"), new BigDecimal("3.056"), new BigDecimal("2.750"), new BigDecimal("2.500"), new BigDecimal("2.292"), new BigDecimal("2.115"), new BigDecimal("1.964")  },
        { new BigDecimal("0.300"), new BigDecimal("3.750"), new BigDecimal("3.333"), new BigDecimal("3.000"), new BigDecimal("2.727"), new BigDecimal("2.500"), new BigDecimal("2.308"), new BigDecimal("2.143")  },
        { new BigDecimal("0.325"), new BigDecimal("4.063"), new BigDecimal("3.611"), new BigDecimal("3.250"), new BigDecimal("2.955"), new BigDecimal("2.708"), new BigDecimal("2.500"), new BigDecimal("2.321")  },
        { new BigDecimal("0.350"), new BigDecimal("4.375"), new BigDecimal("3.889"), new BigDecimal("3.500"), new BigDecimal("3.182"), new BigDecimal("2.917"), new BigDecimal("2.692"), new BigDecimal("2.500")  },
        { new BigDecimal("0.375"), new BigDecimal("4.688"), new BigDecimal("4.167"), new BigDecimal("3.750"), new BigDecimal("3.409"), new BigDecimal("3.125"), new BigDecimal("2.885"), new BigDecimal("2.679")  },
        { new BigDecimal("0.400"), new BigDecimal("5.000"), new BigDecimal("4.444"), new BigDecimal("4.000"), new BigDecimal("3.636"), new BigDecimal("3.333"), new BigDecimal("3.077"), new BigDecimal("2.857")  },
        { new BigDecimal("0.425"), new BigDecimal("5.313"), new BigDecimal("4.722"), new BigDecimal("4.250"), new BigDecimal("3.864"), new BigDecimal("3.542"), new BigDecimal("3.269"), new BigDecimal("3.036")  },
        { new BigDecimal("0.450"), new BigDecimal("5.625"), new BigDecimal("5.000"), new BigDecimal("4.500"), new BigDecimal("4.091"), new BigDecimal("3.750"), new BigDecimal("3.462"), new BigDecimal("3.214")  },
        { new BigDecimal("0.475"), new BigDecimal("5.938"), new BigDecimal("5.278"), new BigDecimal("4.750"), new BigDecimal("4.318"), new BigDecimal("3.958"), new BigDecimal("3.654"), new BigDecimal("3.393")  },
        { new BigDecimal("0.500"), new BigDecimal("6.250"), new BigDecimal("5.556"), new BigDecimal("5.000"), new BigDecimal("4.545"), new BigDecimal("4.167"), new BigDecimal("3.846"), new BigDecimal("3.571")  },
        { new BigDecimal("0.525"), new BigDecimal("6.563"), new BigDecimal("5.833"), new BigDecimal("5.250"), new BigDecimal("4.773"), new BigDecimal("4.375"), new BigDecimal("4.038"), new BigDecimal("3.750")  },
        { new BigDecimal("0.550"), new BigDecimal("6.875"), new BigDecimal("6.111"), new BigDecimal("5.500"), new BigDecimal("5.000"), new BigDecimal("4.583"), new BigDecimal("4.231"), new BigDecimal("3.929")  },
        { new BigDecimal("0.575"), new BigDecimal("7.188"), new BigDecimal("6.389"), new BigDecimal("5.750"), new BigDecimal("5.227"), new BigDecimal("4.792"), new BigDecimal("4.423"), new BigDecimal("4.107")  },
        { new BigDecimal("0.600"), new BigDecimal("7.500"), new BigDecimal("6.667"), new BigDecimal("6.000"), new BigDecimal("5.455"), new BigDecimal("5.000"), new BigDecimal("4.615"), new BigDecimal("4.286")  },
    	/*************
        {  0.00f,  0.08f,  0.09f,  0.10f,  0.11f,  0.12f,  0.13f,  0.14f  },
        {  0.05f, 0.625f, 0.556f, 0.500f, 0.455f, 0.427f, 0.358f, 0.357f  },
        { 0.075f, 0.938f, 0.833f, 0.75f , 0.682f, 0.625f, 0.577f, 0.538f  },
        { 0.100f, 1.250f, 1.110f, 1.00f , 0.909f, 0.833f, 0.769f, 0.714f  },
        { 0.125f, 1.583f, 1.389f, 1.25f , 1.136f, 1.042f, 0.962f, 0.893f },
        { 0.150f, 1.875f, 1.667f, 1.5f  , 1.364f, 1.25f , 1.154f, 1.071f  },
        { 0.175f, 2.188f, 1.944f, 1.75f , 1.591f, 1.458f, 1.346f, 1.25f   },
        { 0.200f, 2.500f, 2.222f, 2.000f, 1.818f, 1.667f, 1.538f, 1.429f  },
        { 0.225f, 2.813f, 2.500f, 2.250f, 2.045f, 1.875f, 1.731f, 1.607f  },
        { 0.250f, 3.125f, 2.778f, 2.500f, 2.273f, 2.083f, 1.923f, 1.786f  },
        { 0.275f, 3.438f, 3.056f, 2.750f, 2.500f, 2.292f, 2.115f, 1.964f  },
        { 0.300f, 3.750f, 3.333f, 3.000f, 2.727f, 2.500f, 2.308f, 2.143f  },
        { 0.325f, 4.063f, 3.611f, 3.250f, 2.955f, 2.708f, 2.500f, 2.321f  },
        { 0.350f, 4.375f, 3.889f, 3.500f, 3.182f, 2.917f, 2.692f, 2.500f  },
        { 0.375f, 4.688f, 4.167f, 3.750f, 3.409f, 3.125f, 2.885f, 2.679f  },
        { 0.400f, 5.000f, 4.444f, 4.000f, 3.636f, 3.333f, 3.077f, 2.857f  },
        { 0.425f, 5.313f, 4.722f, 4.250f, 3.864f, 3.542f, 3.269f, 3.036f  },
        { 0.450f, 5.625f, 5.000f, 4.500f, 4.091f, 3.750f, 3.462f, 3.214f  },
        { 0.475f, 5.938f, 5.278f, 4.750f, 4.318f, 3.958f, 3.654f, 3.393f  },
        { 0.500f, 6.250f, 5.556f, 5.000f, 4.545f, 4.167f, 3.846f, 3.571f  },
        { 0.525f, 6.563f, 5.833f, 5.250f, 4.773f, 4.375f, 4.038f, 3.750f  },
        { 0.550f, 6.875f, 6.111f, 5.500f, 5.000f, 4.583f, 4.231f, 3.929f  },
        { 0.575f, 7.188f, 6.389f, 5.750f, 5.227f, 4.792f, 4.423f, 4.107f  },
        { 0.600f, 7.500f, 6.667f, 6.000f, 5.455f, 5.000f, 4.615f, 4.286f  },
        **************/
    };

}

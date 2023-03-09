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
public class MultiplierPayoutNone {

    // Row 0 is investor's after tax required return
    // Column 0 is companies return on equity
    public static BigDecimal[][] multipliers = {
    	
        {  new BigDecimal("0.00"),  new BigDecimal("0.08"),  new BigDecimal("0.09"),  new BigDecimal("0.10"),  new BigDecimal("0.11"),  new BigDecimal("0.12"),  new BigDecimal("0.13"),  new BigDecimal("0.14")  },
        {  new BigDecimal("0.05"),  new BigDecimal("0.429"), new BigDecimal("0.347"), new BigDecimal("0.287"), new BigDecimal("0.242"), new BigDecimal("0.207"), new BigDecimal("0.179"), new BigDecimal("0.157")  },
        {  new BigDecimal("0.075"), new BigDecimal("0.890"), new BigDecimal("0.720"), new BigDecimal("0.596"), new BigDecimal("0.502"), new BigDecimal("0.429"), new BigDecimal("0.372"), new BigDecimal("0.325")  },
        { new BigDecimal("0.100"), new BigDecimal("1.494"), new BigDecimal("1.209"), new BigDecimal("1.00") , new BigDecimal("0.842"), new BigDecimal("0.720"), new BigDecimal("0.624"), new BigDecimal("0.546")  },
        { new BigDecimal("0.125"), new BigDecimal("2.233"), new BigDecimal("1.806"), new BigDecimal("1.494"), new BigDecimal("1.259"), new BigDecimal("1.076"), new BigDecimal("0.932"), new BigDecimal("0.815")  },
        { new BigDecimal("0.150"), new BigDecimal("3.100"), new BigDecimal("2.508"), new BigDecimal("2.075"), new BigDecimal("1.748"), new BigDecimal("1.494"), new BigDecimal("1.294"), new BigDecimal("1.132")  },
        { new BigDecimal("0.175"), new BigDecimal("4.092"), new BigDecimal("3.310"), new BigDecimal("2.738"), new BigDecimal("2.307"), new BigDecimal("1.972"), new BigDecimal("1.808"), new BigDecimal("1.494")  },
        { new BigDecimal("0.200"), new BigDecimal("5.203"), new BigDecimal("4.209"), new BigDecimal("3.482"), new BigDecimal("2.933"), new BigDecimal("2.508"), new BigDecimal("2.171"), new BigDecimal("1.900")  },
        { new BigDecimal("0.225"), new BigDecimal("6.432"), new BigDecimal("5.203"), new BigDecimal("3.305"), new BigDecimal("3.626"), new BigDecimal("3.100"), new BigDecimal("2.684"), new BigDecimal("2.349")  },
        { new BigDecimal("0.250"), new BigDecimal("7.776"), new BigDecimal("6.290"), new BigDecimal("5.203"), new BigDecimal("4.383"), new BigDecimal("3.748"), new BigDecimal("3.245"), new BigDecimal("2.840")  },
        { new BigDecimal("0.275"), new BigDecimal("9.231"), new BigDecimal("7.467"), new BigDecimal("6.177"), new BigDecimal("5.203"), new BigDecimal("4.449"), new BigDecimal("3.852"), new BigDecimal("3.371")  },
        { new BigDecimal("0.300"),new BigDecimal("10.796"), new BigDecimal("8.733"), new BigDecimal("7.225"), new BigDecimal("6.086"), new BigDecimal("5.203"), new BigDecimal("4.505"), new BigDecimal("3.943")  },
        { new BigDecimal("0.325"),new BigDecimal("12.469"),new BigDecimal("10.087"), new BigDecimal("8.344"), new BigDecimal("7.029"), new BigDecimal("6.010"), new BigDecimal("5.203"), new BigDecimal("4.554")  },
        { new BigDecimal("0.350"),new BigDecimal("14.248"),new BigDecimal("11.526"), new BigDecimal("9.535"), new BigDecimal("8.032"), new BigDecimal("6.867"), new BigDecimal("5.946"), new BigDecimal("5.203")  },
        { new BigDecimal("0.375"),new BigDecimal("16.132"),new BigDecimal("13.050"),new BigDecimal("10.796"), new BigDecimal("0.094"), new BigDecimal("7.776"), new BigDecimal("6.732"), new BigDecimal("5.891")  },
        { new BigDecimal("0.400"),new BigDecimal("18.119"),new BigDecimal("14.658"),new BigDecimal("12.126"),new BigDecimal("10.214"), new BigDecimal("8.733"), new BigDecimal("7.562"), new BigDecimal("6.617")  },
        { new BigDecimal("0.425"),new BigDecimal("20.209"),new BigDecimal("16.348"),new BigDecimal("13.524"),new BigDecimal("11.392"), new BigDecimal("9.740"), new BigDecimal("8.433"), new BigDecimal("7.380")  },
        { new BigDecimal("0.450"),new BigDecimal("22.399"),new BigDecimal("18.119"),new BigDecimal("14.989"),new BigDecimal("12.626"),new BigDecimal("10.796"), new BigDecimal("9.347"), new BigDecimal("8.180")  },
        { new BigDecimal("0.475"),new BigDecimal("24.688"),new BigDecimal("19.972"),new BigDecimal("16.521"),new BigDecimal("13.917"),new BigDecimal("11.899"),new BigDecimal("10.303"), new BigDecimal("9.016")  },
        { new BigDecimal("0.500"),new BigDecimal("27.076"),new BigDecimal("21.903"),new BigDecimal("18.119"),new BigDecimal("15.263"),new BigDecimal("13.050"),new BigDecimal("11.299"), new BigDecimal("9.888")  },
        { new BigDecimal("0.525"),new BigDecimal("29.561"),new BigDecimal("23.914"),new BigDecimal("19.783"),new BigDecimal("16.664"),new BigDecimal("14.248"),new BigDecimal("12.336"),new BigDecimal("10.796")  },
        { new BigDecimal("0.550"),new BigDecimal("32.143"),new BigDecimal("26.003"),new BigDecimal("21.511"),new BigDecimal("18.119"),new BigDecimal("15.493"),new BigDecimal("13.414"),new BigDecimal("11.739")  },
        { new BigDecimal("0.575"),new BigDecimal("34.821"),new BigDecimal("28.169"),new BigDecimal("23.302"),new BigDecimal("19.629"),new BigDecimal("16.783"),new BigDecimal("14.531"),new BigDecimal("12.717")  },
        { new BigDecimal("0.600"),new BigDecimal("37.593"),new BigDecimal("30.411"),new BigDecimal("25.158"),new BigDecimal("21.192"),new BigDecimal("18.119"),new BigDecimal("15.688"),new BigDecimal("13.729")  },
    	/****************
        {  0.00f,  0.08f,  0.09f,  0.10f,  0.11f,  0.12f,  0.13f,  0.14f  },
        {  0.05f,  0.429f, 0.347f, 0.287f, 0.242f, 0.207f, 0.179f, 0.157f  },
        {  0.075f, 0.890f, 0.720f, 0.596f, 0.502f, 0.429f, 0.372f, 0.325f  },
        { 0.100f, 1.494f, 1.209f, 1.00f , 0.842f, 0.720f, 0.624f, 0.546f  },
        { 0.125f, 2.233f, 1.806f, 1.494f, 1.259f, 1.076f, 0.932f, 0.815f  },
        { 0.150f, 3.100f, 2.508f, 2.075f, 1.748f, 1.494f, 1.294f, 1.132f  },
        { 0.175f, 4.092f, 3.310f, 2.738f, 2.307f, 1.972f, 1.808f, 1.494f  },
        { 0.200f, 5.203f, 4.209f, 3.482f, 2.933f, 2.508f, 2.171f, 1.900f  },
        { 0.225f, 6.432f, 5.203f, 3.305f, 3.626f, 3.100f, 2.684f, 2.349f  },
        { 0.250f, 7.776f, 6.290f, 5.203f, 4.383f, 3.748f, 3.245f, 2.840f  },
        { 0.275f, 9.231f, 7.467f, 6.177f, 5.203f, 4.449f, 3.852f, 3.371f  },
        { 0.300f,10.796f, 8.733f, 7.225f, 6.086f, 5.203f, 4.505f, 3.943f  },
        { 0.325f,12.469f,10.087f, 8.344f, 7.029f, 6.010f, 5.203f, 4.554f  },
        { 0.350f,14.248f,11.526f, 9.535f, 8.032f, 6.867f, 5.946f, 5.203f  },
        { 0.375f,16.132f,13.050f,10.796f, 0.094f, 7.776f, 6.732f, 5.891f  },
        { 0.400f,18.119f,14.658f,12.126f,10.214f, 8.733f, 7.562f, 6.617f  },
        { 0.425f,20.209f,16.348f,13.524f,11.392f, 9.740f, 8.433f, 7.380f  },
        { 0.450f,22.399f,18.119f,14.989f,12.626f,10.796f, 9.347f, 8.180f  },
        { 0.475f,24.688f,19.972f,16.521f,13.917f,11.899f,10.303f, 9.016f  },
        { 0.500f,27.076f,21.903f,18.119f,15.263f,13.050f,11.299f, 9.888f  },
        { 0.525f,29.561f,23.914f,19.783f,16.664f,14.248f,12.336f,10.796f  },
        { 0.550f,32.143f,26.003f,21.511f,18.119f,15.493f,13.414f,11.739f  },
        { 0.575f,34.821f,28.169f,23.302f,19.629f,16.783f,14.531f,12.717f  },
        { 0.600f,37.593f,30.411f,25.158f,21.192f,18.119f,15.688f,13.729f  },
        ******************/
    };

}

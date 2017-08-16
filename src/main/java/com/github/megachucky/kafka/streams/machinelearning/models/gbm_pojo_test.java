package com.github.megachucky.kafka.streams.machinelearning.models;

import hex.genmodel.GenModel;
import hex.genmodel.annotations.ModelPojo;

@ModelPojo(name="gbm_pojo_test", algorithm="gbm")
public class gbm_pojo_test extends GenModel {
public hex.ModelCategory getModelCategory() { return hex.ModelCategory.Binomial; }

public boolean isSupervised() { return true; }
public int nfeatures() { return 11; }
public int nclasses() { return 2; }

// Names of columns used by model.
public static final String[] NAMES = NamesHolder_gbm_pojo_test.VALUES;
// Number of output classes included in training data response column.
public static final int NCLASSES = 2;

// Column domains. The last array contains domain of response column.
public static final String[][] DOMAINS = new String[][] {
  /* Year */ null,
  /* Month */ null,
  /* DayofMonth */ null,
  /* DayOfWeek */ null,
  /* CRSDepTime */ null,
  /* CRSArrTime */ null,
  /* UniqueCarrier */ gbm_pojo_test_ColInfo_6.VALUES,
  /* FlightNum */ null,
  /* CRSElapsedTime */ null,
  /* Origin */ gbm_pojo_test_ColInfo_9.VALUES,
  /* Dest */ gbm_pojo_test_ColInfo_10.VALUES,
  /* IsDepDelayed */ gbm_pojo_test_ColInfo_11.VALUES
};
// Prior class distribution
public static final double[] PRIOR_CLASS_DISTRIB = {0.47494201646277684,0.5250579835372231};
// Class distribution used for model building
public static final double[] MODEL_CLASS_DISTRIB = {0.47494201646277684,0.5250579835372231};

public gbm_pojo_test() { super(NAMES,DOMAINS); }
public String getUUID() { return Long.toString(-3463760155119157872L); }

// Pass in data in a double[], pre-aligned to the Model's requirements.
// Jam predictions into the preds[] array; preds[0] is reserved for the
// main prediction (class for classifiers or value for regression),
// and remaining columns hold a probability distribution for classifiers.
public final double[] score0( double[] data, double[] preds ) {
  java.util.Arrays.fill(preds,0);
  gbm_pojo_test_Forest_0.score0(data,preds);
  gbm_pojo_test_Forest_1.score0(data,preds);
  gbm_pojo_test_Forest_2.score0(data,preds);
  gbm_pojo_test_Forest_3.score0(data,preds);
  gbm_pojo_test_Forest_4.score0(data,preds);
  gbm_pojo_test_Forest_5.score0(data,preds);
  gbm_pojo_test_Forest_6.score0(data,preds);
  gbm_pojo_test_Forest_7.score0(data,preds);
  gbm_pojo_test_Forest_8.score0(data,preds);
  gbm_pojo_test_Forest_9.score0(data,preds);
  preds[2] = preds[1] + 0.10031597534710991;
  preds[2] = 1./(1. + Math.min(1e19, Math.exp(-(preds[2]))));
  preds[1] = 1.0-preds[2];
  preds[0] = hex.genmodel.GenModel.getPrediction(preds, PRIOR_CLASS_DISTRIB, data, 0.44517576623644245);
  return preds;
}
}
//The class representing training column names
class NamesHolder_gbm_pojo_test implements java.io.Serializable {
public static final String[] VALUES = new String[11];
static {
  NamesHolder_gbm_pojo_test_0.fill(VALUES);
}
static final class NamesHolder_gbm_pojo_test_0 implements java.io.Serializable {
  static final void fill(String[] sa) {
    sa[0] = "Year";
    sa[1] = "Month";
    sa[2] = "DayofMonth";
    sa[3] = "DayOfWeek";
    sa[4] = "CRSDepTime";
    sa[5] = "CRSArrTime";
    sa[6] = "UniqueCarrier";
    sa[7] = "FlightNum";
    sa[8] = "CRSElapsedTime";
    sa[9] = "Origin";
    sa[10] = "Dest";
  }
}
}
//The class representing column UniqueCarrier
class gbm_pojo_test_ColInfo_6 implements java.io.Serializable {
public static final String[] VALUES = new String[10];
static {
  gbm_pojo_test_ColInfo_6_0.fill(VALUES);
}
static final class gbm_pojo_test_ColInfo_6_0 implements java.io.Serializable {
  static final void fill(String[] sa) {
    sa[0] = "AA";
    sa[1] = "CO";
    sa[2] = "DL";
    sa[3] = "HP";
    sa[4] = "PI";
    sa[5] = "PS";
    sa[6] = "TW";
    sa[7] = "UA";
    sa[8] = "US";
    sa[9] = "WN";
  }
}
}
//The class representing column Origin
class gbm_pojo_test_ColInfo_9 implements java.io.Serializable {
public static final String[] VALUES = new String[132];
static {
  gbm_pojo_test_ColInfo_9_0.fill(VALUES);
}
static final class gbm_pojo_test_ColInfo_9_0 implements java.io.Serializable {
  static final void fill(String[] sa) {
    sa[0] = "ABE";
    sa[1] = "ABQ";
    sa[2] = "ACY";
    sa[3] = "ALB";
    sa[4] = "AMA";
    sa[5] = "ANC";
    sa[6] = "ATL";
    sa[7] = "AUS";
    sa[8] = "AVP";
    sa[9] = "BDL";
    sa[10] = "BGM";
    sa[11] = "BHM";
    sa[12] = "BIL";
    sa[13] = "BNA";
    sa[14] = "BOI";
    sa[15] = "BOS";
    sa[16] = "BTV";
    sa[17] = "BUF";
    sa[18] = "BUR";
    sa[19] = "BWI";
    sa[20] = "CAE";
    sa[21] = "CHO";
    sa[22] = "CHS";
    sa[23] = "CLE";
    sa[24] = "CLT";
    sa[25] = "CMH";
    sa[26] = "COS";
    sa[27] = "CRP";
    sa[28] = "CRW";
    sa[29] = "CVG";
    sa[30] = "DAL";
    sa[31] = "DAY";
    sa[32] = "DCA";
    sa[33] = "DEN";
    sa[34] = "DFW";
    sa[35] = "DSM";
    sa[36] = "DTW";
    sa[37] = "EGE";
    sa[38] = "ELP";
    sa[39] = "ERI";
    sa[40] = "EWR";
    sa[41] = "EYW";
    sa[42] = "FLL";
    sa[43] = "GEG";
    sa[44] = "GNV";
    sa[45] = "GRR";
    sa[46] = "GSO";
    sa[47] = "HNL";
    sa[48] = "HOU";
    sa[49] = "HPN";
    sa[50] = "HRL";
    sa[51] = "IAD";
    sa[52] = "IAH";
    sa[53] = "ICT";
    sa[54] = "IND";
    sa[55] = "ISP";
    sa[56] = "JAN";
    sa[57] = "JAX";
    sa[58] = "JFK";
    sa[59] = "KOA";
    sa[60] = "LAN";
    sa[61] = "LAS";
    sa[62] = "LAX";
    sa[63] = "LBB";
    sa[64] = "LEX";
    sa[65] = "LGA";
    sa[66] = "LIH";
    sa[67] = "LIT";
    sa[68] = "LYH";
    sa[69] = "MAF";
    sa[70] = "MCI";
    sa[71] = "MCO";
    sa[72] = "MDT";
    sa[73] = "MDW";
    sa[74] = "MEM";
    sa[75] = "MFR";
    sa[76] = "MHT";
    sa[77] = "MIA";
    sa[78] = "MKE";
    sa[79] = "MLB";
    sa[80] = "MRY";
    sa[81] = "MSP";
    sa[82] = "MSY";
    sa[83] = "MYR";
    sa[84] = "OAK";
    sa[85] = "OGG";
    sa[86] = "OKC";
    sa[87] = "OMA";
    sa[88] = "ONT";
    sa[89] = "ORD";
    sa[90] = "ORF";
    sa[91] = "PBI";
    sa[92] = "PDX";
    sa[93] = "PHF";
    sa[94] = "PHL";
    sa[95] = "PHX";
    sa[96] = "PIT";
    sa[97] = "PSP";
    sa[98] = "PVD";
    sa[99] = "PWM";
    sa[100] = "RDU";
    sa[101] = "RIC";
    sa[102] = "RNO";
    sa[103] = "ROA";
    sa[104] = "ROC";
    sa[105] = "RSW";
    sa[106] = "SAN";
    sa[107] = "SAT";
    sa[108] = "SAV";
    sa[109] = "SBN";
    sa[110] = "SCK";
    sa[111] = "SDF";
    sa[112] = "SEA";
    sa[113] = "SFO";
    sa[114] = "SJC";
    sa[115] = "SJU";
    sa[116] = "SLC";
    sa[117] = "SMF";
    sa[118] = "SNA";
    sa[119] = "SRQ";
    sa[120] = "STL";
    sa[121] = "STT";
    sa[122] = "STX";
    sa[123] = "SWF";
    sa[124] = "SYR";
    sa[125] = "TLH";
    sa[126] = "TPA";
    sa[127] = "TRI";
    sa[128] = "TUL";
    sa[129] = "TUS";
    sa[130] = "TYS";
    sa[131] = "UCA";
  }
}
}
//The class representing column Dest
class gbm_pojo_test_ColInfo_10 implements java.io.Serializable {
public static final String[] VALUES = new String[134];
static {
  gbm_pojo_test_ColInfo_10_0.fill(VALUES);
}
static final class gbm_pojo_test_ColInfo_10_0 implements java.io.Serializable {
  static final void fill(String[] sa) {
    sa[0] = "ABE";
    sa[1] = "ABQ";
    sa[2] = "ACY";
    sa[3] = "ALB";
    sa[4] = "AMA";
    sa[5] = "ANC";
    sa[6] = "ATL";
    sa[7] = "AUS";
    sa[8] = "AVL";
    sa[9] = "AVP";
    sa[10] = "BDL";
    sa[11] = "BGM";
    sa[12] = "BHM";
    sa[13] = "BNA";
    sa[14] = "BOI";
    sa[15] = "BOS";
    sa[16] = "BTV";
    sa[17] = "BUF";
    sa[18] = "BUR";
    sa[19] = "BWI";
    sa[20] = "CAE";
    sa[21] = "CAK";
    sa[22] = "CHA";
    sa[23] = "CHO";
    sa[24] = "CHS";
    sa[25] = "CLE";
    sa[26] = "CLT";
    sa[27] = "CMH";
    sa[28] = "COS";
    sa[29] = "CRP";
    sa[30] = "CVG";
    sa[31] = "DAL";
    sa[32] = "DAY";
    sa[33] = "DCA";
    sa[34] = "DEN";
    sa[35] = "DFW";
    sa[36] = "DSM";
    sa[37] = "DTW";
    sa[38] = "ELM";
    sa[39] = "ELP";
    sa[40] = "ERI";
    sa[41] = "EUG";
    sa[42] = "EWR";
    sa[43] = "EYW";
    sa[44] = "FAT";
    sa[45] = "FAY";
    sa[46] = "FLL";
    sa[47] = "FNT";
    sa[48] = "GEG";
    sa[49] = "GRR";
    sa[50] = "GSO";
    sa[51] = "GSP";
    sa[52] = "HNL";
    sa[53] = "HOU";
    sa[54] = "HPN";
    sa[55] = "HRL";
    sa[56] = "HTS";
    sa[57] = "IAD";
    sa[58] = "IAH";
    sa[59] = "ICT";
    sa[60] = "ILM";
    sa[61] = "IND";
    sa[62] = "ISP";
    sa[63] = "JAN";
    sa[64] = "JAX";
    sa[65] = "JFK";
    sa[66] = "KOA";
    sa[67] = "LAS";
    sa[68] = "LAX";
    sa[69] = "LBB";
    sa[70] = "LEX";
    sa[71] = "LGA";
    sa[72] = "LIH";
    sa[73] = "LIT";
    sa[74] = "LYH";
    sa[75] = "MAF";
    sa[76] = "MCI";
    sa[77] = "MCO";
    sa[78] = "MDT";
    sa[79] = "MDW";
    sa[80] = "MHT";
    sa[81] = "MIA";
    sa[82] = "MKE";
    sa[83] = "MRY";
    sa[84] = "MSP";
    sa[85] = "MSY";
    sa[86] = "MYR";
    sa[87] = "OAJ";
    sa[88] = "OAK";
    sa[89] = "OGG";
    sa[90] = "OKC";
    sa[91] = "OMA";
    sa[92] = "ONT";
    sa[93] = "ORD";
    sa[94] = "ORF";
    sa[95] = "ORH";
    sa[96] = "PBI";
    sa[97] = "PDX";
    sa[98] = "PHF";
    sa[99] = "PHL";
    sa[100] = "PHX";
    sa[101] = "PIT";
    sa[102] = "PNS";
    sa[103] = "PSP";
    sa[104] = "PVD";
    sa[105] = "PWM";
    sa[106] = "RDU";
    sa[107] = "RIC";
    sa[108] = "RNO";
    sa[109] = "ROA";
    sa[110] = "ROC";
    sa[111] = "RSW";
    sa[112] = "SAN";
    sa[113] = "SAT";
    sa[114] = "SBN";
    sa[115] = "SCK";
    sa[116] = "SDF";
    sa[117] = "SEA";
    sa[118] = "SFO";
    sa[119] = "SJC";
    sa[120] = "SJU";
    sa[121] = "SLC";
    sa[122] = "SMF";
    sa[123] = "SNA";
    sa[124] = "SRQ";
    sa[125] = "STL";
    sa[126] = "STT";
    sa[127] = "SWF";
    sa[128] = "SYR";
    sa[129] = "TOL";
    sa[130] = "TPA";
    sa[131] = "TUL";
    sa[132] = "TUS";
    sa[133] = "UCA";
  }
}
}
//The class representing column IsDepDelayed
class gbm_pojo_test_ColInfo_11 implements java.io.Serializable {
public static final String[] VALUES = new String[2];
static {
  gbm_pojo_test_ColInfo_11_0.fill(VALUES);
}
static final class gbm_pojo_test_ColInfo_11_0 implements java.io.Serializable {
  static final void fill(String[] sa) {
    sa[0] = "NO";
    sa[1] = "YES";
  }
}
}

class gbm_pojo_test_Forest_0 {
public static void score0(double[] fdata, double[] preds) {
  preds[1] += gbm_pojo_test_Tree_0_class_0.score0(fdata);
}
}
class gbm_pojo_test_Tree_0_class_0 {
static final double score0(double[] data) {
  double pred =      (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT0, 132, 0, data[9])) ? 
       (data[2 /* DayofMonth */] <5.5f ? 
           (Double.isNaN(data[0]) || data[0 /* Year */] <2007.5f ? 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT1, 131, 0, data[9])) ? 
                   (data[5 /* CRSArrTime */] <1214.5f ? 
                      -0.08490811f : 
                      -0.0074444837f) : 
                   (data[5 /* CRSArrTime */] <1325.5f ? 
                      -0.0026011502f : 
                      0.0672389f)) : 
               (data[5 /* CRSArrTime */] <1104.5f ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(132, 1, data[10]) || (GenModel.bitSetIsInRange(132, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT2, 132, 1, data[10])) ? 
                      -0.041237872f : 
                      0.08666507f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(66, 51, data[9]) && !GenModel.bitSetContains(GRPSPLIT3, 66, 51, data[9])) ? 
                      0.070153005f : 
                      0.16985005f))) : 
           (data[0 /* Year */] <1998.0f ? 
               (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(131, 0, data[9]) || (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT4, 131, 0, data[9])) ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(134, 0, data[10]) || (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT5, 134, 0, data[10])) ? 
                      -0.07675884f : 
                      -0.015552446f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT6, 134, 0, data[10])) ? 
                      -0.025045818f : 
                      0.041781697f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT7, 134, 0, data[10])) ? 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(131, 0, data[9]) || (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT8, 131, 0, data[9])) ? 
                      -0.1403306f : 
                      -0.08559531f) : 
                   (Double.isNaN(data[5]) || data[5 /* CRSArrTime */] <1622.5f ? 
                      -0.09153927f : 
                      -0.02366724f)))) : 
       (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT9, 134, 0, data[10])) ? 
           (data[2 /* DayofMonth */] <7.5f ? 
               (data[4 /* CRSDepTime */] <917.5f ? 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(124, 3, data[9]) || (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT10, 124, 3, data[9])) ? 
                      -0.046503622f : 
                      0.12153205f) : 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(124, 3, data[9]) || (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT11, 124, 3, data[9])) ? 
                      0.03362562f : 
                      0.10945965f)) : 
               (data[0 /* Year */] <1997.5f ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT12, 124, 3, data[9])) ? 
                      -0.055411227f : 
                      0.034853708f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT13, 124, 3, data[9])) ? 
                      -0.12390993f : 
                      -0.06337517f))) : 
           (data[2 /* DayofMonth */] <7.5f ? 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(129, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT14, 129, 3, data[9])) ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(132, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT15, 132, 0, data[10])) ? 
                      -0.0022618792f : 
                      0.06443829f) : 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(129, 3, data[9]) || (GenModel.bitSetIsInRange(129, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT16, 129, 3, data[9])) ? 
                      0.10094735f : 
                      0.17672203f)) : 
               (Double.isNaN(data[0]) || data[0 /* Year */] <1998.0f ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(129, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT17, 129, 3, data[9])) ? 
                      0.013672127f : 
                      0.07838874f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(129, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT18, 129, 3, data[9])) ? 
                      -0.08370597f : 
                      0.0076616695f)))));
  return pred;
} // constant pool size = 456B, number of visited nodes = 31, static init size = 1980B
// {00011110 01100000 10010100 01000111 00100011 00100100 11000111 10000110 11011011 01010100 00000001 11111010 11100111 11010010 11010011 00111010 0001}
public static final byte[] GRPSPLIT0 = new byte[] {120, 6, 41, -30, -60, 36, -29, 97, -37, 42, -128, 95, -25, 75, -53, 92, 8};
// {11000000 00000101 01000000 10000000 01011100 10010000 00000000 00000000 00000000 00001011 01100000 00000001 00011000 00000001 00101100 10000000 011}
public static final byte[] GRPSPLIT1 = new byte[] {3, -96, 2, 1, 58, 9, 0, 0, 0, -48, 6, -128, 24, -128, 52, 1, 6};
// {...1 0-bits... 11111101 11110111 11011111 11111101 11111101 11111011 11110111 11111111 11001111 11101111 11111111 11111111 11101111 11101111 11111001 10011111 111}
public static final byte[] GRPSPLIT2 = new byte[] {-65, -17, -5, -65, -65, -33, -17, -1, -13, -9, -1, -1, -9, -9, -97, -7, 15};
// {...51 0-bits... 11111101 1111011}
public static final byte[] GRPSPLIT3 = new byte[] {-65, -17, -1, -1, -9, -1, -1, -1, 3};
// {10000000 00000001 01000010 10000000 11010000 10000000 00010000 00000000 00000000 00001000 00000000 00000001 00001000 00100100 00000100 00000000 000}
public static final byte[] GRPSPLIT4 = new byte[] {1, -128, 66, 1, 11, 1, 8, 0, 0, 16, 0, -128, 16, 36, 32, 0, 0};
// {00000000 00000000 01000000 00000000 00000000 00100000 00000000 00000000 00001001 00000000 00000000 11000000 00010000 00000000 00000010 00100000 000010}
public static final byte[] GRPSPLIT5 = new byte[] {0, 0, 2, 0, 0, 4, 0, 0, -112, 0, 0, 3, 8, 0, 64, 4, 16};
// {00000000 00000000 00011010 10000000 01000000 00100000 00101000 01000000 00000001 00100000 00000001 00000010 00001100 00100001 00000010 00000000 000000}
public static final byte[] GRPSPLIT6 = new byte[] {0, 0, 88, 1, 2, 4, 20, 2, -128, 4, -128, 64, 48, -124, 64, 0, 0};
// {00010100 00100111 10010000 10000000 00000000 00100010 10000000 00001000 10010001 00000110 10001100 10000110 11011000 10000111 00000110 10110100 101000}
public static final byte[] GRPSPLIT7 = new byte[] {40, -28, 9, 1, 0, 68, 1, 16, -119, 96, 49, 97, 27, -31, 96, 45, 5};
// {00000000 00010101 00100000 10001000 10000100 10000000 00000000 00000000 00000000 00000000 00000000 00000000 00001000 00000000 00100000 00000000 000}
public static final byte[] GRPSPLIT8 = new byte[] {0, -88, 4, 17, 33, 1, 0, 0, 0, 0, 0, 0, 16, 0, 4, 0, 0};
// {11111101 10101101 00011111 10011001 00001101 10111011 10111101 00001011 01111111 11111101 00000011 11111011 10111110 10111111 11001111 01111100 111101}
public static final byte[] GRPSPLIT9 = new byte[] {-65, -75, -8, -103, -80, -35, -67, -48, -2, -65, -64, -33, 125, -3, -13, 62, 47};
// {...3 0-bits... 10000000 00000000 00000000 00010000 00000000 00000000 00000000 00000000 00011010 00000000 00000000 10000001 00000000 10000000 00000000 0}
public static final byte[] GRPSPLIT10 = new byte[] {1, 0, 0, 8, 0, 0, 0, 0, 88, 0, 0, -127, 0, 1, 0, 0};
// {...3 0-bits... 00010000 00000000 00000000 00000000 00000001 00000000 00000000 00110010 00001010 00000000 00000011 10000001 00011010 10000100 00000000 0}
public static final byte[] GRPSPLIT11 = new byte[] {8, 0, 0, 0, -128, 0, 0, 76, 80, 0, -64, -127, 88, 33, 0, 2};
// {...3 0-bits... 11111111 11111111 01111111 11011111 11111111 11111111 11111111 11011111 11111111 11111111 11111111 11101111 11011101 11111111 11111111 1}
public static final byte[] GRPSPLIT12 = new byte[] {-1, -1, -2, -5, -1, -1, -1, -5, -1, -1, -1, -9, -69, -1, -1, 5};
// {...3 0-bits... 11111111 11111111 01111111 11110110 11111111 11111111 11101111 11101101 11101111 11011111 11111110 11111010 11111001 11111011 11110111 1}
public static final byte[] GRPSPLIT13 = new byte[] {-1, -1, -2, 111, -1, -1, -9, -73, -9, -5, 127, 95, -97, -33, -17, 7};
// {...3 0-bits... 10111100 11111111 01111101 11000111 11111111 11111011 11111011 11111111 01111111 01111111 11111111 11111111 11011011 11101111 01101111 111111}
public static final byte[] GRPSPLIT14 = new byte[] {61, -1, -66, -29, -1, -33, -33, -1, -2, -2, -1, -1, -37, -9, -10, -1, 0};
// {11110110 11111110 11111111 11111111 11111011 11111101 11111111 11111101 11101110 10110011 11111111 01011101 11111011 01011111 00110100 11011111 1100}
public static final byte[] GRPSPLIT15 = new byte[] {111, 127, -1, -1, -33, -65, -1, -65, 119, -51, -1, -70, -33, -6, 44, -5, 3};
// {...3 0-bits... 00000000 00000000 00100000 00000000 00001000 00000000 00000000 00000000 01000010 00100000 00000000 00000000 00010000 00000000 00000000 110000}
public static final byte[] GRPSPLIT16 = new byte[] {0, 0, 4, 0, 16, 0, 0, 0, 66, 4, 0, 0, 8, 0, 0, 3, 0};
// {...3 0-bits... 01111110 11111111 11111101 11111111 11111110 11111111 11110111 11011111 11111101 11111111 11111100 11111110 11111111 11101011 01101111 111011}
public static final byte[] GRPSPLIT17 = new byte[] {126, -1, -65, -1, 127, -1, -17, -5, -65, -1, 63, 127, -1, -41, -10, -9, 0};
// {...3 0-bits... 00110000 00000100 10000000 00100001 00000001 00000010 00000000 00000000 00001000 00000000 00000010 10010000 00100110 00000000 10000001 010100}
public static final byte[] GRPSPLIT18 = new byte[] {12, 32, 1, -124, -128, 64, 0, 0, 16, 0, 64, 9, 100, 0, -127, 10, 0};
}


class gbm_pojo_test_Forest_1 {
public static void score0(double[] fdata, double[] preds) {
  preds[1] += gbm_pojo_test_Tree_1_class_0.score0(fdata);
}
}
class gbm_pojo_test_Tree_1_class_0 {
static final double score0(double[] data) {
  double pred =      (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT0, 132, 0, data[9])) ? 
       (data[2 /* DayofMonth */] <5.5f ? 
           (Double.isNaN(data[0]) || data[0 /* Year */] <2007.5f ? 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT1, 131, 0, data[9])) ? 
                   (data[4 /* CRSDepTime */] <975.5f ? 
                      -0.095463976f : 
                      -0.014495662f) : 
                   (!Double.isNaN(data[6 /* UniqueCarrier */]) && (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT2, 32, 0, data[6])) ? 
                      -0.049982212f : 
                      0.04667776f)) : 
               (data[5 /* CRSArrTime */] <1104.5f ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(132, 1, data[10]) || (GenModel.bitSetIsInRange(132, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT3, 132, 1, data[10])) ? 
                      -0.031231511f : 
                      0.086104415f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(66, 51, data[9]) && !GenModel.bitSetContains(GRPSPLIT4, 66, 51, data[9])) ? 
                      0.06345302f : 
                      0.15539458f))) : 
           (data[0 /* Year */] <1998.0f ? 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT5, 131, 0, data[9])) ? 
                   (data[5 /* CRSArrTime */] <1274.5f ? 
                      -0.09608598f : 
                      -0.04030581f) : 
                   (Double.isNaN(data[5]) || data[5 /* CRSArrTime */] <1548.5f ? 
                      -0.020994632f : 
                      0.038413852f)) : 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT6, 131, 0, data[9])) ? 
                   (Double.isNaN(data[5]) || data[5 /* CRSArrTime */] <1878.5f ? 
                      -0.12219516f : 
                      -0.06311011f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT7, 134, 0, data[10])) ? 
                      -0.10240176f : 
                      -0.032094374f)))) : 
       (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT8, 134, 0, data[10])) ? 
           (data[2 /* DayofMonth */] <7.5f ? 
               (data[4 /* CRSDepTime */] <918.5f ? 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(129, 3, data[9]) || (GenModel.bitSetIsInRange(129, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT9, 129, 3, data[9])) ? 
                      -0.044791684f : 
                      0.11350671f) : 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(129, 3, data[9]) || (GenModel.bitSetIsInRange(129, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT10, 129, 3, data[9])) ? 
                      0.03744709f : 
                      0.10671422f)) : 
               (data[0 /* Year */] <1997.5f ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(129, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT11, 129, 3, data[9])) ? 
                      -0.05136065f : 
                      0.034544434f) : 
                   (data[5 /* CRSArrTime */] <1441.5f ? 
                      -0.110554494f : 
                      -0.05872382f))) : 
           (data[2 /* DayofMonth */] <7.5f ? 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT12, 124, 3, data[9])) ? 
                   (data[5 /* CRSArrTime */] <1180.0f ? 
                      8.2124857E-4f : 
                      0.061129592f) : 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(124, 3, data[9]) || (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT13, 124, 3, data[9])) ? 
                      0.0957122f : 
                      0.1668169f)) : 
               (Double.isNaN(data[0]) || data[0 /* Year */] <1998.0f ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT14, 124, 3, data[9])) ? 
                      0.020376673f : 
                      0.07489993f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT15, 124, 3, data[9])) ? 
                      -0.06373164f : 
                      0.015438366f)))));
  return pred;
} // constant pool size = 389B, number of visited nodes = 31, static init size = 1578B
// {00011110 01100000 10010100 01000111 00100111 00100100 11000111 10000110 11011011 01010100 00000001 11111010 11100111 11010010 11010011 00111010 0001}
public static final byte[] GRPSPLIT0 = new byte[] {120, 6, 41, -30, -28, 36, -29, 97, -37, 42, -128, 95, -25, 75, -53, 92, 8};
// {11000000 00000101 01000001 10000000 01011000 10010000 00000000 00000000 00000000 00001011 01100000 00000001 00011000 00100001 00101100 10000000 011}
public static final byte[] GRPSPLIT1 = new byte[] {3, -96, -126, 1, 26, 9, 0, 0, 0, -48, 6, -128, 24, -124, 52, 1, 6};
// {11001011 11000000 00000000 00000000}
public static final byte[] GRPSPLIT2 = new byte[] {-45, 3, 0, 0};
// {...1 0-bits... 11111101 11110111 11011111 11111101 11111101 11111011 11110111 11111111 11001111 11101111 11111111 11101111 11101111 11101111 11111001 10011111 111}
public static final byte[] GRPSPLIT3 = new byte[] {-65, -17, -5, -65, -65, -33, -17, -1, -13, -9, -1, -9, -9, -9, -97, -7, 15};
// {...51 0-bits... 11111101 1111011}
public static final byte[] GRPSPLIT4 = new byte[] {-65, -17, -1, -1, -9, -1, -1, -1, 3};
// {10000000 00000001 01000011 10000000 11011000 10000010 00010000 00000000 00000000 10001000 00000000 00000001 00001000 00100100 00000100 10000000 010}
public static final byte[] GRPSPLIT5 = new byte[] {1, -128, -62, 1, 27, 65, 8, 0, 0, 17, 0, -128, 16, 36, 32, 1, 2};
// {01000000 00000101 00000001 10001000 11010000 00000001 00000000 00100000 00000000 10000000 00000000 00000000 00001000 00000000 00001100 10000000 000}
public static final byte[] GRPSPLIT6 = new byte[] {2, -96, -128, 17, 11, -128, 0, 4, 0, 1, 0, 0, 16, 0, 48, 1, 0};
// {01101101 01111111 10110111 10000111 11001011 11111011 11011111 10111011 11110110 11110111 10111101 11000111 11111101 10000011 01111110 11110101 111011}
public static final byte[] GRPSPLIT7 = new byte[] {-74, -2, -19, -31, -45, -33, -5, -35, 111, -17, -67, -29, -65, -63, 126, -81, 55};
// {11111101 10101101 00011111 10011011 00001101 10011010 10111101 00001011 01111111 11111101 00000011 11111001 10111110 10111111 01001111 01111100 001101}
public static final byte[] GRPSPLIT8 = new byte[] {-65, -75, -8, -39, -80, 89, -67, -48, -2, -65, -64, -97, 125, -3, -14, 62, 44};
// {...3 0-bits... 10000000 00000000 00000000 00010000 00000000 00000000 00000000 00000000 00011010 00000000 00000000 10000001 00000000 10000000 00000000 000000}
public static final byte[] GRPSPLIT9 = new byte[] {1, 0, 0, 8, 0, 0, 0, 0, 88, 0, 0, -127, 0, 1, 0, 0, 0};
// {...3 0-bits... 00000000 00000000 00000000 00001000 00110001 00000000 00000000 00110000 00001010 00000000 00000011 10000011 00011110 10000100 00000000 010000}
public static final byte[] GRPSPLIT10 = new byte[] {0, 0, 0, 16, -116, 0, 0, 12, 80, 0, -64, -63, 120, 33, 0, 2, 0};
// {...3 0-bits... 11111111 11111111 01111111 11011111 11111111 11111111 11110111 11011111 11110111 11111111 11111111 11101111 11011101 11111111 11111111 111011}
public static final byte[] GRPSPLIT11 = new byte[] {-1, -1, -2, -5, -1, -1, -17, -5, -17, -1, -1, -9, -69, -1, -1, -9, 1};
// {...3 0-bits... 10111100 11111111 01111101 11000111 11111111 11111011 11101011 11111111 01111111 01111111 11111110 11111111 11011011 11101011 01101111 1}
public static final byte[] GRPSPLIT12 = new byte[] {61, -1, -66, -29, -1, -33, -41, -1, -2, -2, 127, -1, -37, -41, -10, 15};
// {...3 0-bits... 00000000 00000000 00100000 00000000 00001000 00000000 00000000 00000000 01000010 00100000 00000000 00000000 00010000 00000000 00000000 1}
public static final byte[] GRPSPLIT13 = new byte[] {0, 0, 4, 0, 16, 0, 0, 0, 66, 4, 0, 0, 8, 0, 0, 1};
// {...3 0-bits... 01111110 11111111 11111101 11111111 11111110 11111111 11111111 11011111 11111101 11111111 11111100 11111110 11111011 11101011 01101111 1}
public static final byte[] GRPSPLIT14 = new byte[] {126, -1, -65, -1, 127, -1, -1, -5, -65, -1, 63, 127, -33, -41, -10, 7};
// {...3 0-bits... 00110000 00000100 10000000 00100000 00000001 00000010 00000000 00000000 00001000 00000000 00000010 10010000 00100100 00000000 10000001 0}
public static final byte[] GRPSPLIT15 = new byte[] {12, 32, 1, 4, -128, 64, 0, 0, 16, 0, 64, 9, 36, 0, -127, 10};
}


class gbm_pojo_test_Forest_2 {
public static void score0(double[] fdata, double[] preds) {
  preds[1] += gbm_pojo_test_Tree_2_class_0.score0(fdata);
}
}
class gbm_pojo_test_Tree_2_class_0 {
static final double score0(double[] data) {
  double pred =      (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT0, 132, 0, data[9])) ? 
       (Double.isNaN(data[0]) || data[0 /* Year */] <2006.5f ? 
           (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT1, 131, 0, data[9])) ? 
               (data[0 /* Year */] <1987.5f ? 
                   (Double.isNaN(data[7]) || data[7 /* FlightNum */] <1535.5f ? 
                      0.07354705f : 
                      -0.038398083f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT2, 133, 1, data[10])) ? 
                      -0.12525378f : 
                      -0.065879285f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT3, 134, 0, data[10])) ? 
                   (Double.isNaN(data[7]) || data[7 /* FlightNum */] <679.5f ? 
                      -0.0399611f : 
                      -0.08144096f) : 
                   (!Double.isNaN(data[6 /* UniqueCarrier */]) && (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT4, 32, 0, data[6])) ? 
                      -0.0800385f : 
                      0.008836972f))) : 
           (data[5 /* CRSArrTime */] <1242.5f ? 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(129, 1, data[9]) && !GenModel.bitSetContains(GRPSPLIT5, 129, 1, data[9])) ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(132, 1, data[10]) || (GenModel.bitSetIsInRange(132, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT6, 132, 1, data[10])) ? 
                      -0.13485901f : 
                      0.077487275f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(132, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT7, 132, 1, data[10])) ? 
                      -0.003762017f : 
                      0.07857362f)) : 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(129, 1, data[9]) && !GenModel.bitSetContains(GRPSPLIT8, 129, 1, data[9])) ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(132, 1, data[10]) || (GenModel.bitSetIsInRange(132, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT9, 132, 1, data[10])) ? 
                      0.0014935309f : 
                      0.15552887f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(132, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT10, 132, 1, data[10])) ? 
                      0.10809928f : 
                      0.15701f)))) : 
       (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT11, 134, 0, data[10])) ? 
           (data[2 /* DayofMonth */] <5.5f ? 
               (data[5 /* CRSArrTime */] <1081.5f ? 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(129, 3, data[9]) || (GenModel.bitSetIsInRange(129, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT12, 129, 3, data[9])) ? 
                      -0.049810037f : 
                      0.12804708f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(129, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT13, 129, 3, data[9])) ? 
                      0.033265512f : 
                      0.10477275f)) : 
               (data[0 /* Year */] <1997.5f ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(129, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT14, 129, 3, data[9])) ? 
                      -0.03614877f : 
                      0.027475996f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(129, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT15, 129, 3, data[9])) ? 
                      -0.09453595f : 
                      -0.03963656f))) : 
           (data[2 /* DayofMonth */] <7.5f ? 
               (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(124, 3, data[9]) || (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT16, 124, 3, data[9])) ? 
                   (data[4 /* CRSDepTime */] <1216.5f ? 
                      0.035695385f : 
                      0.08741104f) : 
                   (!Double.isNaN(data[6 /* UniqueCarrier */]) && (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT17, 32, 0, data[6])) ? 
                      -0.037707943f : 
                      0.11799982f)) : 
               (Double.isNaN(data[0]) || data[0 /* Year */] <1998.0f ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT18, 124, 3, data[9])) ? 
                      0.02139124f : 
                      0.07292731f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT19, 124, 3, data[9])) ? 
                      -0.0563367f : 
                      0.011105511f)))));
  return pred;
} // constant pool size = 457B, number of visited nodes = 31, static init size = 1986B
// {00010110 01100000 10010100 01000111 00100111 00100100 11000111 10000110 11011011 01010100 00000001 11111010 11100011 11010110 11010011 00111010 0001}
public static final byte[] GRPSPLIT0 = new byte[] {104, 6, 41, -30, -28, 36, -29, 97, -37, 42, -128, 95, -57, 107, -53, 92, 8};
// {11000000 00000101 01000011 10000000 11010000 10000000 00000000 00000000 00000000 10000001 00001000 00000000 00011100 00100000 00000100 10000000 011}
public static final byte[] GRPSPLIT1 = new byte[] {3, -96, -62, 1, 11, 1, 0, 0, 0, -127, 16, 0, 56, 4, 32, 1, 6};
// {...1 0-bits... 01111101 10111011 11111111 11011111 10111101 11111110 11101111 10111111 01011111 11100111 00101111 11011111 11111111 10101110 11110101 11110111 1111}
public static final byte[] GRPSPLIT2 = new byte[] {-66, -35, -1, -5, -67, 127, -9, -3, -6, -25, -12, -5, -1, 117, -81, -17, 31};
// {00000000 00100011 10001010 10000000 01000000 00100010 00001000 00100000 01001010 00100110 10001101 00000010 11011100 10100011 01000110 10100100 101010}
public static final byte[] GRPSPLIT3 = new byte[] {0, -60, 81, 1, 2, 68, 16, 4, 82, 100, -79, 64, 59, -59, 98, 37, 21};
// {11101111 11000000 00000000 00000000}
public static final byte[] GRPSPLIT4 = new byte[] {-9, 3, 0, 0};
// {...1 0-bits... 11101111 11011011 11111111 11011111 11111111 11111111 10011111 11111101 11110111 11111111 11111011 11111111 11111111 11111111 11101110 11111110}
public static final byte[] GRPSPLIT5 = new byte[] {-9, -37, -1, -5, -1, -1, -7, -65, -17, -1, -33, -1, -1, -1, 119, 127, 0};
// {...1 0-bits... 00000000 00001100 00000000 00000000 00000000 00000001 00000000 00000000 00010000 10000000 00000000 00000000 10010000 00000001 00000000 00000000 001}
public static final byte[] GRPSPLIT6 = new byte[] {0, 48, 0, 0, 0, -128, 0, 0, 8, 1, 0, 0, 9, -128, 0, 0, 4};
// {...1 0-bits... 11111111 11111111 10011111 01111111 11111101 11111011 11111111 11110111 11011111 11001111 11110110 11111111 11101111 11111111 01111101 11011111 101}
public static final byte[] GRPSPLIT7 = new byte[] {-1, -1, -7, -2, -65, -33, -1, -17, -5, -13, 111, -1, -9, -1, -66, -5, 5};
// {...1 0-bits... 11111101 11111111 00111111 11011111 11111111 11011111 10011111 01111101 11111111 11111111 11111111 11111111 11111111 11111111 11111110 11111110}
public static final byte[] GRPSPLIT8 = new byte[] {-65, -1, -4, -5, -1, -5, -7, -66, -1, -1, -1, -1, -1, -1, 127, 127, 0};
// {...1 0-bits... 00000010 00001000 00000000 10100000 00000000 00000000 00000010 00000000 00011000 00000000 00000000 00100000 10000000 00000000 00010010 00001000 011}
public static final byte[] GRPSPLIT9 = new byte[] {64, 16, 0, 5, 0, 0, 64, 0, 24, 0, 0, 4, 1, 0, 72, 16, 6};
// {...1 0-bits... 01101111 11111111 11011111 11111101 11110111 11111110 11110111 11111110 11011111 11100111 11111111 11101111 11111110 11101111 01111111 11010111 101}
public static final byte[] GRPSPLIT10 = new byte[] {-10, -1, -5, -65, -17, 127, -17, 127, -5, -25, -1, -9, 127, -9, -2, -21, 13};
// {11111101 10101100 00011111 10011011 01001101 10011010 10111101 00001001 01111111 11111101 00000011 11111001 10111110 10111111 01001011 01111100 001101}
public static final byte[] GRPSPLIT11 = new byte[] {-65, 53, -8, -39, -78, 89, -67, -112, -2, -65, -64, -97, 125, -3, -46, 62, 44};
// {...3 0-bits... 10000000 00000000 00000000 00010000 00000000 00000000 00000000 00000000 00011010 00000000 00000000 10000000 00000000 00000100 00000000 000000}
public static final byte[] GRPSPLIT12 = new byte[] {1, 0, 0, 8, 0, 0, 0, 0, 88, 0, 0, 1, 0, 32, 0, 0, 0};
// {...3 0-bits... 11111111 11111111 01111101 11101111 11111110 11011011 11101111 11111101 11111111 11011111 11110111 01101111 11111011 11111001 01111111 111011}
public static final byte[] GRPSPLIT13 = new byte[] {-1, -1, -66, -9, 127, -37, -9, -65, -1, -5, -17, -10, -33, -97, -2, -9, 0};
// {...3 0-bits... 11111111 11111111 01111111 11011111 11111111 11111111 11100111 11011111 11110111 11111111 11111111 11101111 11111101 11111111 11111111 111011}
public static final byte[] GRPSPLIT14 = new byte[] {-1, -1, -2, -5, -1, -1, -25, -5, -17, -1, -1, -9, -65, -1, -1, -9, 1};
// {...3 0-bits... 10010000 00000000 00000010 00000000 00100001 00100000 00000000 00000000 00001000 00000000 00000010 01010000 00000100 10000000 10010000 010000}
public static final byte[] GRPSPLIT15 = new byte[] {9, 0, 64, 0, -124, 4, 0, 0, 16, 0, 64, 10, 32, 1, 9, 2, 0};
// {...3 0-bits... 00010000 00000000 00100000 00000000 00001001 00000010 00101000 00010100 01001010 00100000 00001100 10000011 00010000 00000000 00001001 1}
public static final byte[] GRPSPLIT16 = new byte[] {8, 0, 4, 0, -112, 64, 20, 40, 82, 4, 48, -63, 8, 0, -112, 3};
// {11111110 11000000 00000000 00000000}
public static final byte[] GRPSPLIT17 = new byte[] {127, 3, 0, 0};
// {...3 0-bits... 01111110 11111111 01111101 11111111 11111110 11111111 11111111 11011111 11111101 11111111 11111100 11111110 11111011 11101011 01101111 0}
public static final byte[] GRPSPLIT18 = new byte[] {126, -1, -66, -1, 127, -1, -1, -5, -65, -1, 63, 127, -33, -41, -10, 6};
// {...3 0-bits... 00110010 00000100 10000000 00100000 00000001 00000010 00000000 00000000 00001000 00000000 00000010 10010000 00000100 00000000 10000001 0}
public static final byte[] GRPSPLIT19 = new byte[] {76, 32, 1, 4, -128, 64, 0, 0, 16, 0, 64, 9, 32, 0, -127, 10};
}


class gbm_pojo_test_Forest_3 {
public static void score0(double[] fdata, double[] preds) {
  preds[1] += gbm_pojo_test_Tree_3_class_0.score0(fdata);
}
}
class gbm_pojo_test_Tree_3_class_0 {
static final double score0(double[] data) {
  double pred =      (data[2 /* DayofMonth */] <6.5f ? 
       (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT0, 132, 0, data[9])) ? 
           (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT1, 134, 0, data[10])) ? 
               (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT2, 132, 0, data[9])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT3, 132, 0, data[9])) ? 
                      -0.14475606f : 
                      -0.07131989f) : 
                   (data[5 /* CRSArrTime */] <1088.0f ? 
                      -0.06947046f : 
                      0.032513283f)) : 
               (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT4, 132, 0, data[9])) ? 
                   (data[5 /* CRSArrTime */] <1327.5f ? 
                      -0.058298577f : 
                      0.007936995f) : 
                   (data[5 /* CRSArrTime */] <1143.5f ? 
                      0.008775314f : 
                      0.076927535f))) : 
           (data[5 /* CRSArrTime */] <1180.0f ? 
               (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(131, 0, data[9]) || (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT5, 131, 0, data[9])) ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(133, 0, data[10]) || (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT6, 133, 0, data[10])) ? 
                      -0.041947298f : 
                      0.03570212f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT7, 133, 0, data[10])) ? 
                      0.023365906f : 
                      0.09398554f)) : 
               (Double.isNaN(data[0]) || data[0 /* Year */] <2007.5f ? 
                   (!Double.isNaN(data[6 /* UniqueCarrier */]) && (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT8, 32, 0, data[6])) ? 
                      -0.010184068f : 
                      0.0750181f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT9, 131, 0, data[9])) ? 
                      0.11923278f : 
                      0.15482864f)))) : 
       (Double.isNaN(data[0]) || data[0 /* Year */] <1998.0f ? 
           (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT10, 132, 0, data[9])) ? 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT11, 134, 0, data[10])) ? 
                   (Double.isNaN(data[6 /* UniqueCarrier */]) || !GenModel.bitSetIsInRange(32, 0, data[6]) || (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT12, 32, 0, data[6])) ? 
                      -0.06809773f : 
                      0.09678831f) : 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT13, 132, 0, data[9])) ? 
                      -0.03513849f : 
                      0.02532822f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT14, 134, 0, data[10])) ? 
                   (Double.isNaN(data[4]) || data[4 /* CRSDepTime */] <1511.5f ? 
                      -0.025363052f : 
                      0.04103205f) : 
                   (!Double.isNaN(data[6 /* UniqueCarrier */]) && (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT15, 32, 0, data[6])) ? 
                      -0.028269341f : 
                      0.05953272f))) : 
           (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT16, 132, 0, data[9])) ? 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT17, 134, 0, data[10])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT18, 132, 0, data[9])) ? 
                      -0.13941258f : 
                      -0.10332655f) : 
                   (data[5 /* CRSArrTime */] <1327.5f ? 
                      -0.09895605f : 
                      -0.03512637f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT19, 134, 0, data[10])) ? 
                   (Double.isNaN(data[5]) || data[5 /* CRSArrTime */] <1770.0f ? 
                      -0.07830333f : 
                      -0.019202774f) : 
                   (data[5 /* CRSArrTime */] <1475.0f ? 
                      -0.022279682f : 
                      0.03281123f)))));
  return pred;
} // constant pool size = 447B, number of visited nodes = 31, static init size = 1926B
// {11010110 01000101 10010100 00000111 01111111 01110100 11000111 10000110 11011011 01001101 00101011 11111111 11111011 01110001 11110101 01111010 0010}
public static final byte[] GRPSPLIT0 = new byte[] {107, -94, 41, -32, -2, 46, -29, 97, -37, -78, -44, -1, -33, -114, -81, 94, 4};
// {10111111 11111110 10101111 11011111 01011110 11111011 11110011 10010111 01001110 11100011 11111001 10111001 11111011 11110110 10111111 10111111 111110}
public static final byte[] GRPSPLIT1 = new byte[] {-3, 127, -11, -5, 122, -33, -49, -23, 114, -57, -97, -99, -33, 111, -3, -3, 31};
// {00001000 10110000 01100000 01000000 00000000 10000000 00001000 01001000 00000100 10000010 01000000 00000000 00000000 00000100 00001000 00000000 0000}
public static final byte[] GRPSPLIT2 = new byte[] {16, 13, 6, 2, 0, 1, 16, 18, 32, 65, 2, 0, 0, 32, 16, 0, 0};
// {11011111 11110111 11110101 11100111 11111111 11111111 11101111 11001110 11011111 11111111 11111111 11111111 11111111 11110111 11111111 11111110 1011}
public static final byte[] GRPSPLIT3 = new byte[] {-5, -17, -81, -25, -1, -1, -9, 115, -5, -1, -1, -1, -1, -17, -1, 127, 13};
// {00000000 00000010 01000001 10000000 10000000 10000000 00110000 01000000 00000000 00000000 00000000 00000000 00000000 10000000 00000000 10000000 0100}
public static final byte[] GRPSPLIT4 = new byte[] {0, 64, -126, 1, 1, 1, 12, 2, 0, 0, 0, 0, 0, 1, 0, 1, 2};
// {10010010 01000100 10000000 00000001 00101010 00110000 01000001 00000010 10000011 01001101 00000000 00010001 00110010 00000001 01000000 00001000 000}
public static final byte[] GRPSPLIT5 = new byte[] {73, 34, 1, -128, 84, 12, -126, 64, -63, -78, 0, -120, 76, -128, 2, 16, 0};
// {01010000 00101100 01010000 00010001 00100001 10000110 10000100 00000000 10010101 00000111 00000100 10011010 10010100 00010001 00001011 00100100 10100}
public static final byte[] GRPSPLIT6 = new byte[] {10, 52, 10, -120, -124, 97, 33, 0, -87, -32, 32, 89, 41, -120, -48, 36, 5};
// {11111111 11010100 11011111 10111111 11111110 11111101 11111111 11111111 01111111 11111111 11111011 01110011 10111110 01010111 00111111 11101011 11000}
public static final byte[] GRPSPLIT7 = new byte[] {-1, 43, -5, -3, 127, -65, -1, -1, -2, -1, -33, -50, 125, -22, -4, -41, 3};
// {01101011 11000000 00000000 00000000}
public static final byte[] GRPSPLIT8 = new byte[] {-42, 3, 0, 0};
// {00000000 00000000 00000000 00000000 00000000 00000000 00000001 10000110 00010000 01000000 00001001 10001000 00000010 00000000 10100000 00000000 000}
public static final byte[] GRPSPLIT9 = new byte[] {0, 0, 0, 0, 0, 0, -128, 97, 8, 2, -112, 17, 64, 0, 5, 0, 0};
// {00011110 01111011 11010110 11010111 01110111 01111100 11110110 10011011 11011111 00111100 00000011 11111011 11100101 11011111 11001011 01111000 1001}
public static final byte[] GRPSPLIT10 = new byte[] {120, -34, 107, -21, -18, 62, 111, -39, -5, 60, -64, -33, -89, -5, -45, 30, 9};
// {11111111 11111111 11101111 11011111 11011111 11111111 11110111 11011011 11111111 01111011 11111011 01111011 11110110 11111011 01111110 11111111 111110}
public static final byte[] GRPSPLIT11 = new byte[] {-1, -1, -9, -5, -5, -1, -17, -37, -1, -34, -33, -34, 111, -33, 126, -1, 31};
// {01000000 00000000 00000000 00000000}
public static final byte[] GRPSPLIT12 = new byte[] {2, 0, 0, 0};
// {00000000 00000000 00100000 00000000 10001000 10000000 00000000 01000000 00000000 00000000 00000000 00000000 00000000 00100000 00000000 00000010 0100}
public static final byte[] GRPSPLIT13 = new byte[] {0, 0, 4, 0, 17, 1, 0, 2, 0, 0, 0, 0, 0, 4, 0, 64, 2};
// {11011111 10101011 00011011 10111111 01101001 11110010 10111111 01001001 00111101 11111101 11101111 11101001 10111110 11011101 11101111 11111110 101101}
public static final byte[] GRPSPLIT14 = new byte[] {-5, -43, -40, -3, -106, 79, -3, -110, -68, -65, -9, -105, 125, -69, -9, 127, 45};
// {11101111 11000000 00000000 00000000}
public static final byte[] GRPSPLIT15 = new byte[] {-9, 3, 0, 0};
// {00000110 01000101 10010000 11000100 10110100 00100001 01000000 00000000 00000001 10000000 00000000 01011010 00001100 11000000 00010010 00101010 0000}
public static final byte[] GRPSPLIT16 = new byte[] {96, -94, 9, 35, 45, -124, 2, 0, -128, 1, 0, 90, 48, 3, 72, 84, 0};
// {00000110 00000111 01000000 01000000 00000000 00100000 10000010 00000000 01010001 00001100 00000000 00100100 01011000 00000000 00100110 00110100 001100}
public static final byte[] GRPSPLIT17 = new byte[] {96, -32, 2, 2, 0, 4, 65, 0, -118, 48, 0, 36, 26, 0, 100, 44, 12};
// {01111111 11111101 11111111 11111111 11111111 10101111 11111111 10111111 11111111 11010011 10111110 11111110 11101111 11100111 11111010 00111111 1111}
public static final byte[] GRPSPLIT18 = new byte[] {-2, -65, -1, -1, -1, -11, -1, -3, -1, -53, 125, 127, -9, -25, 95, -4, 15};
// {00000000 00100001 00010000 00010010 00010000 00110010 10000000 00000000 10011000 00000100 10001100 10000010 10011000 11000011 01000100 11000100 101000}
public static final byte[] GRPSPLIT19 = new byte[] {0, -124, 8, 72, 8, 76, 1, 0, 25, 32, 49, 65, 25, -61, 34, 35, 5};
}


class gbm_pojo_test_Forest_4 {
public static void score0(double[] fdata, double[] preds) {
  preds[1] += gbm_pojo_test_Tree_4_class_0.score0(fdata);
}
}
class gbm_pojo_test_Tree_4_class_0 {
static final double score0(double[] data) {
  double pred =      (data[2 /* DayofMonth */] <6.5f ? 
       (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT0, 132, 0, data[9])) ? 
           (data[5 /* CRSArrTime */] <1032.5f ? 
               (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(133, 1, data[10]) || (GenModel.bitSetIsInRange(133, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT1, 133, 1, data[10])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 1, data[9]) && !GenModel.bitSetContains(GRPSPLIT2, 131, 1, data[9])) ? 
                      -0.12371421f : 
                      -0.062322695f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 1, data[9]) && !GenModel.bitSetContains(GRPSPLIT3, 131, 1, data[9])) ? 
                      -0.07444072f : 
                      0.045755927f)) : 
               (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(133, 1, data[10]) || (GenModel.bitSetIsInRange(133, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT4, 133, 1, data[10])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 1, data[9]) && !GenModel.bitSetContains(GRPSPLIT5, 131, 1, data[9])) ? 
                      -0.13140881f : 
                      0.0013696944f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 1, data[9]) && !GenModel.bitSetContains(GRPSPLIT6, 131, 1, data[9])) ? 
                      -0.040153f : 
                      0.07998007f))) : 
           (data[4 /* CRSDepTime */] <1041.5f ? 
               (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(131, 0, data[9]) || (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT7, 131, 0, data[9])) ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT8, 133, 0, data[10])) ? 
                      -0.073470205f : 
                      0.020269897f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT9, 133, 0, data[10])) ? 
                      0.0081113335f : 
                      0.096475005f)) : 
               (Double.isNaN(data[0]) || data[0 /* Year */] <2007.5f ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT10, 133, 0, data[10])) ? 
                      0.014573026f : 
                      0.07913743f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT11, 131, 0, data[9])) ? 
                      0.11842765f : 
                      0.14836372f)))) : 
       (Double.isNaN(data[0]) || data[0 /* Year */] <1999.5f ? 
           (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT12, 132, 0, data[9])) ? 
               (!Double.isNaN(data[6 /* UniqueCarrier */]) && (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT13, 32, 0, data[6])) ? 
                   (data[4 /* CRSDepTime */] <811.5f ? 
                      -0.13078639f : 
                      -0.04807093f) : 
                   (Double.isNaN(data[0]) || data[0 /* Year */] <1992.5f ? 
                      -0.03275934f : 
                      0.022654513f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT14, 134, 0, data[10])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT15, 132, 0, data[9])) ? 
                      -0.03371911f : 
                      0.017215878f) : 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT16, 132, 0, data[9])) ? 
                      0.025642848f : 
                      0.07064418f))) : 
           (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT17, 132, 0, data[9])) ? 
               (data[5 /* CRSArrTime */] <1438.5f ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(130, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT18, 130, 0, data[9])) ? 
                      -0.13195784f : 
                      -0.08627914f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(132, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT19, 132, 0, data[10])) ? 
                      -0.090834394f : 
                      -0.035032675f)) : 
               (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(134, 0, data[10]) || (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT20, 134, 0, data[10])) ? 
                   (Double.isNaN(data[5]) || data[5 /* CRSArrTime */] <1585.5f ? 
                      -0.070559286f : 
                      -0.018396154f) : 
                   (data[4 /* CRSDepTime */] <1401.5f ? 
                      -0.0020808042f : 
                      0.04909105f)))));
  return pred;
} // constant pool size = 491B, number of visited nodes = 31, static init size = 2190B
// {10010010 01000100 10000100 00000101 01101111 00110100 01000101 00000110 11011011 01001101 00101001 11111011 11110011 01110001 11110001 00111010 0010}
public static final byte[] GRPSPLIT0 = new byte[] {73, 34, 33, -96, -10, 44, -94, 96, -37, -78, -108, -33, -49, -114, -113, 92, 4};
// {...1 0-bits... 11111101 11111101 11111111 00110101 10110111 11111011 11111111 11110000 11001111 01011110 11111111 10010111 11100111 10101000 01101011 01011111 1101}
public static final byte[] GRPSPLIT1 = new byte[] {-65, -65, -1, -84, -19, -33, -1, 15, -13, 122, -1, -23, -25, 21, -42, -6, 27};
// {...1 0-bits... 11101101 11101011 11011100 11111011 11111110 11111111 10011111 11111101 11110110 11111111 11111111 11111111 11110111 11111111 11101110 11111111 11}
public static final byte[] GRPSPLIT2 = new byte[] {-73, -41, 59, -33, 127, -1, -7, -65, 111, -1, -1, -1, -17, -1, 119, -1, 7};
// {...1 0-bits... 10000010 01000010 11100010 00000101 00000000 00000001 00000100 10000000 00000000 00000000 00000000 00000000 00001000 00001000 00001101 00000000 00}
public static final byte[] GRPSPLIT3 = new byte[] {65, 66, 71, -96, 0, -128, 32, 1, 0, 0, 0, 0, 16, 16, -80, 0, 0};
// {...1 0-bits... 10100110 01111100 00000001 00100010 00001000 01000101 00000010 00001010 00011100 01100011 00000000 00111001 10100000 01101111 00011000 00001001 0101}
public static final byte[] GRPSPLIT4 = new byte[] {101, 62, -128, 68, 16, -94, 64, 80, 56, -58, 0, -100, 5, -10, 24, -112, 10};
// {...1 0-bits... 10111111 11111111 11101111 11001111 11111111 11101011 11011111 10011101 10111111 11111111 11010111 11111111 11110111 11101111 11111111 11111101 11}
public static final byte[] GRPSPLIT5 = new byte[] {-3, -1, -9, -13, -1, -41, -5, -71, -3, -1, -21, -1, -17, -9, -1, -65, 7};
// {...1 0-bits... 11111111 11111111 11111111 11111111 11111111 11111001 11101111 11111111 11111111 11111110 11111011 11111111 11110111 11111011 11101011 11111110 11}
public static final byte[] GRPSPLIT6 = new byte[] {-1, -1, -1, -1, -1, -97, -9, -1, -1, 127, -33, -1, -17, -33, -41, 127, 3};
// {10010000 01000000 00000000 00000000 00101000 00100000 01000001 00000010 10000001 01001101 00000000 00000001 10110010 00000000 00010000 00001000 000}
public static final byte[] GRPSPLIT7 = new byte[] {9, 2, 0, 0, 20, 4, -126, 64, -127, -78, 0, -128, 77, 0, 8, 16, 0};
// {10111111 11010110 11011111 11011111 10111110 11011101 11111110 11011111 11111111 11110111 01111011 11101111 11111111 01111101 11111011 10101111 11010}
public static final byte[] GRPSPLIT8 = new byte[] {-3, 107, -5, -5, 125, -69, 127, -5, -1, -17, -34, -9, -1, -66, -33, -11, 11};
// {11111111 11001101 11111111 10111111 11111011 11011111 11110111 01011111 01111111 11110100 11111111 01110011 10111111 01000111 10111110 11101111 11110}
public static final byte[] GRPSPLIT9 = new byte[] {-1, -77, -1, -3, -33, -5, -17, -6, -2, 47, -1, -50, -3, -30, 125, -9, 15};
// {10101111 11111111 10011111 11110111 01101111 10111111 10101101 10011111 01101111 11111111 01010011 01101110 11111111 00100110 01011011 11011111 01100}
public static final byte[] GRPSPLIT10 = new byte[] {-11, -1, -7, -17, -10, -3, -75, -7, -10, -1, -54, 118, -1, 100, -38, -5, 6};
// {00000000 00000000 00000000 00000000 00000000 00000000 00000001 00000110 00010000 01000000 00001001 10001000 00000010 00000000 11000000 00000000 000}
public static final byte[] GRPSPLIT11 = new byte[] {0, 0, 0, 0, 0, 0, -128, 96, 8, 2, -112, 17, 64, 0, 3, 0, 0};
// {00011110 01111011 11010110 11010111 00110111 01111100 11110110 10011011 11011111 00111100 00000011 11111011 11100101 11011100 11000011 01111010 1001}
public static final byte[] GRPSPLIT12 = new byte[] {120, -34, 107, -21, -20, 62, 111, -39, -5, 60, -64, -33, -89, 59, -61, 94, 9};
// {01001111 10000000 00000000 00000000}
public static final byte[] GRPSPLIT13 = new byte[] {-14, 1, 0, 0};
// {11011111 10101011 00011011 10011111 01111001 11111010 10110111 01001001 00111101 11111101 11101111 11101001 10111110 11011101 11101111 11111110 001101}
public static final byte[] GRPSPLIT14 = new byte[] {-5, -43, -40, -7, -98, 95, -19, -110, -68, -65, -9, -105, 125, -69, -9, 127, 44};
// {00000000 00000000 00000010 01000001 00000000 00000000 00000000 00000000 01000011 00000000 00000001 01010000 10000001 00000000 01000000 00000000 0001}
public static final byte[] GRPSPLIT15 = new byte[] {0, 0, 64, -126, 0, 0, 0, 0, -62, 0, -128, 10, -127, 0, 2, 0, 8};
// {00000010 01000000 10000100 10000000 00000001 00000000 00000110 00000010 11001000 00000100 00000000 00010010 11000000 00000000 00000001 00100010 0000}
public static final byte[] GRPSPLIT16 = new byte[] {64, 2, 33, 1, -128, 0, 96, 64, 19, 32, 0, 72, 3, 0, -128, 68, 0};
// {00010110 01000101 10010000 11000100 10010100 00100001 01000000 00100000 00000001 10000000 00000000 01011010 00001100 00010000 00011110 00000000 0000}
public static final byte[] GRPSPLIT17 = new byte[] {104, -94, 9, 35, 41, -124, 2, 4, -128, 1, 0, 90, 48, 8, 120, 0, 0};
// {01000000 10010010 01100010 00000001 01001000 10000000 00001010 00000100 00000010 00000010 01010000 00000000 10000010 00000000 01000000 00000000 01}
public static final byte[] GRPSPLIT18 = new byte[] {2, 73, 70, -128, 18, 1, 80, 32, 64, 64, 10, 0, 65, 0, 2, 0, 2};
// {00000000 00000011 01000000 01000000 00000000 00100000 00000010 01000100 00001001 00000100 00000000 10000100 01011000 00000000 00100111 00110000 0010}
public static final byte[] GRPSPLIT19 = new byte[] {0, -64, 2, 2, 0, 4, 64, 34, -112, 32, 0, 33, 26, 0, -28, 12, 4};
// {00000000 00000001 00010000 00000010 00010000 00110010 00000000 00000000 00001000 00000100 00000100 10000010 10001000 10000011 01000110 01000100 101000}
public static final byte[] GRPSPLIT20 = new byte[] {0, -128, 8, 64, 8, 76, 0, 0, 16, 32, 32, 65, 17, -63, 98, 34, 5};
}


class gbm_pojo_test_Forest_5 {
public static void score0(double[] fdata, double[] preds) {
  preds[1] += gbm_pojo_test_Tree_5_class_0.score0(fdata);
}
}
class gbm_pojo_test_Tree_5_class_0 {
static final double score0(double[] data) {
  double pred =      (data[2 /* DayofMonth */] <6.5f ? 
       (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT0, 132, 0, data[9])) ? 
           (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT1, 134, 0, data[10])) ? 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT2, 132, 0, data[9])) ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT3, 134, 0, data[10])) ? 
                      -0.17535403f : 
                      -0.11951502f) : 
                   (Double.isNaN(data[4]) || data[4 /* CRSDepTime */] <1272.0f ? 
                      -0.08784636f : 
                      -0.022800786f)) : 
               (data[4 /* CRSDepTime */] <1180.0f ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(130, 1, data[10]) || (GenModel.bitSetIsInRange(130, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT4, 130, 1, data[10])) ? 
                      -0.05467794f : 
                      0.123772666f) : 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(130, 1, data[10]) || (GenModel.bitSetIsInRange(130, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT5, 130, 1, data[10])) ? 
                      -6.35277E-4f : 
                      0.1540596f))) : 
           (data[5 /* CRSArrTime */] <1327.5f ? 
               (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(131, 0, data[9]) || (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT6, 131, 0, data[9])) ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT7, 133, 0, data[10])) ? 
                      -0.04405936f : 
                      0.019320041f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT8, 133, 0, data[10])) ? 
                      0.005152084f : 
                      0.07956682f)) : 
               (Double.isNaN(data[0]) || data[0 /* Year */] <2003.5f ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT9, 133, 0, data[10])) ? 
                      -0.052023977f : 
                      0.059355058f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(130, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT10, 130, 0, data[9])) ? 
                      0.043730453f : 
                      0.1109079f)))) : 
       (Double.isNaN(data[0]) || data[0 /* Year */] <1998.0f ? 
           (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT11, 132, 0, data[9])) ? 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT12, 134, 0, data[10])) ? 
                   (data[5 /* CRSArrTime */] <1106.5f ? 
                      -0.107170016f : 
                      -0.045113467f) : 
                   (Double.isNaN(data[4]) || data[4 /* CRSDepTime */] <1364.5f ? 
                      -0.04108156f : 
                      0.0054534846f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT13, 134, 0, data[10])) ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT14, 134, 0, data[10])) ? 
                      -0.083889164f : 
                      -0.010010638f) : 
                   (!Double.isNaN(data[6 /* UniqueCarrier */]) && (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT15, 32, 0, data[6])) ? 
                      -0.02485342f : 
                      0.046775978f))) : 
           (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT16, 132, 0, data[9])) ? 
               (Double.isNaN(data[5]) || data[5 /* CRSArrTime */] <1881.0f ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(130, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT17, 130, 0, data[9])) ? 
                      -0.10566193f : 
                      -0.06981868f) : 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(131, 0, data[10]) || (GenModel.bitSetIsInRange(131, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT18, 131, 0, data[10])) ? 
                      -0.08136055f : 
                      -0.015339674f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT19, 134, 0, data[10])) ? 
                   (Double.isNaN(data[0]) || data[0 /* Year */] <2004.5f ? 
                      -0.07521195f : 
                      -0.01897317f) : 
                   (data[4 /* CRSDepTime */] <1180.0f ? 
                      -0.042753506f : 
                      0.014415721f)))));
  return pred;
} // constant pool size = 473B, number of visited nodes = 31, static init size = 2082B
// {11010110 01000101 11010100 11000111 01111111 11110100 11000111 11000110 11011011 11001111 00101011 11111111 11111011 11110111 11110101 11111010 0110}
public static final byte[] GRPSPLIT0 = new byte[] {107, -94, 43, -29, -2, 47, -29, 99, -37, -13, -44, -1, -33, -17, -81, 95, 6};
// {11111111 11111110 11101111 11011111 11011110 11011111 11110111 11111111 11011110 11100111 11111011 10111001 11111111 11110111 10111111 11111111 111110}
public static final byte[] GRPSPLIT1 = new byte[] {-1, 127, -9, -5, 123, -5, -17, -1, 123, -25, -33, -99, -1, -17, -3, -1, 31};
// {11111110 11110101 11010111 11111111 01111111 11111111 11011111 11001110 11011011 11111111 11111111 11111111 11111111 11110111 11111111 11111110 1111}
public static final byte[] GRPSPLIT2 = new byte[] {127, -81, -21, -1, -2, -1, -5, 115, -37, -1, -1, -1, -1, -17, -1, 127, 15};
// {00000000 00000000 00010000 00100000 00000001 00000000 00001000 00000000 00000000 00000000 00000000 00000100 00000000 00000000 00000000 00000000 000001}
public static final byte[] GRPSPLIT3 = new byte[] {0, 0, 8, 4, -128, 0, 16, 0, 0, 0, 0, 32, 0, 0, 0, 0, 32};
// {...1 0-bits... 00000000 00001100 10000001 00000000 00000000 00000001 00000000 00000000 00001000 00000000 00000000 00000000 00000000 00000000 00000000 01000000 0}
public static final byte[] GRPSPLIT4 = new byte[] {0, 48, -127, 0, 0, -128, 0, 0, 16, 0, 0, 0, 0, 0, 0, 2, 0};
// {...1 0-bits... 00000010 00001100 00000000 00000000 00000000 00000000 00000010 00001001 00001000 00000010 00000000 00000000 10000000 00000101 00011000 10001000 0}
public static final byte[] GRPSPLIT5 = new byte[] {64, 48, 0, 0, 0, 0, 64, -112, 16, 64, 0, 0, 1, -96, 24, 17, 2};
// {10010010 01000100 00000000 00000001 00101000 00100000 01000001 00000010 11000011 01001101 00100000 00000001 11110010 00000000 01010000 00001000 001}
public static final byte[] GRPSPLIT6 = new byte[] {73, 34, 0, -128, 20, 4, -126, 64, -61, -78, 4, -128, 79, 0, 10, 16, 4};
// {10110111 11111110 10000111 10001111 10111011 11011101 11111111 11011100 01110110 11100111 01111101 01001111 11111011 01110000 00111011 11101111 11100}
public static final byte[] GRPSPLIT7 = new byte[] {-19, 127, -31, -15, -35, -69, -1, 59, 110, -25, -66, -14, -33, 14, -36, -9, 7};
// {01111111 11001100 11011111 10011111 11111011 11011111 11100111 01011011 01111111 11111100 01111011 01110011 11111111 11100111 10111111 11111111 01100}
public static final byte[] GRPSPLIT8 = new byte[] {-2, 51, -5, -7, -33, -5, -25, -38, -2, 63, -34, -50, -1, -25, -3, -1, 6};
// {10101111 11111001 11011101 11110111 01101111 10111011 10111101 10010111 10101111 11111111 11110011 01111101 10111101 01111111 11011011 11101011 11101}
public static final byte[] GRPSPLIT9 = new byte[] {-11, -97, -69, -17, -10, -35, -67, -23, -11, -1, -49, -66, -67, -2, -37, -41, 23};
// {11111111 10111110 10101111 11111101 11101111 11101111 01111101 00111111 11111111 11111111 11011111 11111111 11111111 10111111 10111111 01111111 10}
public static final byte[] GRPSPLIT10 = new byte[] {-1, 125, -11, -65, -9, -9, -66, -4, -1, -1, -5, -1, -1, -3, -3, -2, 1};
// {00011110 01111011 11010110 11010111 11110111 01111110 11110110 10011011 11011111 00111100 00000011 11111011 11100101 11111111 11001111 01111000 1001}
public static final byte[] GRPSPLIT11 = new byte[] {120, -34, 107, -21, -17, 126, 111, -39, -5, 60, -64, -33, -89, -1, -13, 30, 9};
// {11111111 11111111 10101111 11011111 11111111 11111111 11110111 11011011 11111111 01111011 11111011 11111011 11110111 11111111 11111110 11111111 111111}
public static final byte[] GRPSPLIT12 = new byte[] {-1, -1, -11, -5, -1, -1, -17, -37, -1, -34, -33, -33, -17, -1, 127, -1, 63};
// {11011111 10101011 00011011 10111111 01101101 11110010 10111111 01001001 00111101 11111101 11101111 01111101 10111110 11011101 11101111 11111110 101101}
public static final byte[] GRPSPLIT13 = new byte[] {-5, -43, -40, -3, -74, 79, -3, -110, -68, -65, -9, -66, 125, -69, -9, 127, 45};
// {11111111 11111111 01111111 11111111 11101101 11110111 11111111 01101101 11111111 11111111 11111111 11111111 11111110 11111111 11101111 11111111 111111}
public static final byte[] GRPSPLIT14 = new byte[] {-1, -1, -2, -1, -73, -17, -1, -74, -1, -1, -1, -1, 127, -1, -9, -1, 63};
// {11101111 11000000 00000000 00000000}
public static final byte[] GRPSPLIT15 = new byte[] {-9, 3, 0, 0};
// {01010110 01000101 10010000 11001100 11110100 00100001 01000000 00100000 00000001 10000000 01000000 01011010 00001100 11010000 11011110 10101010 0000}
public static final byte[] GRPSPLIT16 = new byte[] {106, -94, 9, 51, 47, -124, 2, 4, -128, 1, 2, 90, 48, 11, 123, 85, 0};
// {00000000 10010000 01100001 00000001 00001000 10000000 00000010 00000100 00000000 00000010 00010001 00100000 10100000 00000000 00100000 00000000 01}
public static final byte[] GRPSPLIT17 = new byte[] {0, 9, -122, -128, 16, 1, 64, 32, 0, 64, -120, 4, 5, 0, 4, 0, 2};
// {00000010 00000000 01000000 00000000 00000100 00100010 00000000 01000000 01000001 00000100 00000000 00001100 01011000 00000000 00000101 01100000 000}
public static final byte[] GRPSPLIT18 = new byte[] {64, 0, 2, 0, 32, 68, 0, 2, -126, 32, 0, 48, 26, 0, -96, 6, 0};
// {00000000 00100101 00010000 00010010 01000000 00110010 10000000 00101000 10010001 00000100 10001100 10000010 11011000 11000011 01000110 11010100 101000}
public static final byte[] GRPSPLIT19 = new byte[] {0, -92, 8, 72, 2, 76, 1, 20, -119, 32, 49, 65, 27, -61, 98, 43, 5};
}


class gbm_pojo_test_Forest_6 {
public static void score0(double[] fdata, double[] preds) {
  preds[1] += gbm_pojo_test_Tree_6_class_0.score0(fdata);
}
}
class gbm_pojo_test_Tree_6_class_0 {
static final double score0(double[] data) {
  double pred =      (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT0, 132, 0, data[9])) ? 
       (Double.isNaN(data[0]) || data[0 /* Year */] <2006.5f ? 
           (data[5 /* CRSArrTime */] <1444.5f ? 
               (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(134, 0, data[10]) || (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT1, 134, 0, data[10])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT2, 132, 0, data[9])) ? 
                      -0.07994515f : 
                      -0.038213454f) : 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT3, 132, 0, data[9])) ? 
                      -0.03815278f : 
                      0.013343556f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT4, 134, 0, data[10])) ? 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT5, 132, 0, data[9])) ? 
                      -0.05748715f : 
                      0.062956914f) : 
                   (!Double.isNaN(data[6 /* UniqueCarrier */]) && (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT6, 32, 0, data[6])) ? 
                      -0.059515014f : 
                      0.013447878f))) : 
           (data[4 /* CRSDepTime */] <1124.5f ? 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(129, 1, data[9]) && !GenModel.bitSetContains(GRPSPLIT7, 129, 1, data[9])) ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(132, 1, data[10]) || (GenModel.bitSetIsInRange(132, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT8, 132, 1, data[10])) ? 
                      -0.1732277f : 
                      -0.04905467f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(132, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT9, 132, 1, data[10])) ? 
                      -0.14933641f : 
                      0.037337136f)) : 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(129, 1, data[9]) && !GenModel.bitSetContains(GRPSPLIT10, 129, 1, data[9])) ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(132, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT11, 132, 1, data[10])) ? 
                      -0.051000718f : 
                      0.06068832f) : 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(132, 1, data[10]) || (GenModel.bitSetIsInRange(132, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT12, 132, 1, data[10])) ? 
                      0.09433502f : 
                      0.13511497f)))) : 
       (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT13, 134, 0, data[10])) ? 
           (data[2 /* DayofMonth */] <5.5f ? 
               (data[5 /* CRSArrTime */] <1081.5f ? 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(124, 3, data[9]) || (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT14, 124, 3, data[9])) ? 
                      -0.03974043f : 
                      0.09754446f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT15, 133, 0, data[10])) ? 
                      -0.006901633f : 
                      0.07263307f)) : 
               (Double.isNaN(data[0]) || data[0 /* Year */] <1997.5f ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT16, 133, 0, data[10])) ? 
                      -0.029186554f : 
                      0.021131588f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(124, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT17, 124, 3, data[9])) ? 
                      -0.07091319f : 
                      -0.027239723f))) : 
           (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT18, 132, 0, data[9])) ? 
               (Double.isNaN(data[5]) || data[5 /* CRSArrTime */] <1640.5f ? 
                   (Double.isNaN(data[0]) || data[0 /* Year */] <1998.0f ? 
                      0.027061231f : 
                      -0.021917144f) : 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(131, 1, data[10]) || (GenModel.bitSetIsInRange(131, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT19, 131, 1, data[10])) ? 
                      0.025699666f : 
                      0.083474666f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT20, 134, 0, data[10])) ? 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT21, 132, 0, data[9])) ? 
                      0.013190969f : 
                      0.073076054f) : 
                   (data[5 /* CRSArrTime */] <921.5f ? 
                      0.0071072467f : 
                      0.075247064f)))));
  return pred;
} // constant pool size = 507B, number of visited nodes = 31, static init size = 2286B
// {00010110 01100000 10010100 01000111 00100111 00100100 11000111 10000110 11011011 01010100 00000001 11111010 11100011 11010100 01010011 00111010 0000}
public static final byte[] GRPSPLIT0 = new byte[] {104, 6, 41, -30, -28, 36, -29, 97, -37, 42, -128, 95, -57, 43, -54, 92, 0};
// {01010110 00000100 00000100 10000000 01000000 00100000 10000000 00000000 10010000 00001000 00000110 00000010 10010101 10010011 01000011 10100000 001010}
public static final byte[] GRPSPLIT1 = new byte[] {106, 32, 32, 1, 2, 4, 1, 0, 9, 16, 96, 64, -87, -55, -62, 5, 20};
// {00000000 00000101 00000011 10100000 11011000 10000011 00000000 00101000 00000000 10000011 01011000 00000001 00000000 00000000 10001000 00000000 0000}
public static final byte[] GRPSPLIT2 = new byte[] {0, -96, -64, 5, 27, -63, 0, 20, 0, -63, 26, -128, 0, 0, 17, 0, 0};
// {11000000 00000101 00000000 10000000 11000000 00000000 00000000 01000000 00000000 00001000 00100000 00000000 00000100 00100000 00000100 00000000 0010}
public static final byte[] GRPSPLIT3 = new byte[] {3, -96, 0, 1, 3, 0, 0, 2, 0, 16, 4, 0, 32, 4, 32, 0, 4};
// {00101100 11111011 10111111 11000111 11001010 11111011 01110111 10110111 00001111 01110111 10011001 11000111 11111101 10100111 01111110 11011101 111010}
public static final byte[] GRPSPLIT4 = new byte[] {52, -33, -3, -29, 83, -33, -18, -19, -16, -18, -103, -29, -65, -27, 126, -69, 23};
// {00000000 00000100 00100010 00000000 00000000 10010000 00000000 00000000 00000000 00000000 00000000 00000000 00001100 00000000 00001000 00000000 0000}
public static final byte[] GRPSPLIT5 = new byte[] {0, 32, 68, 0, 0, 9, 0, 0, 0, 0, 0, 0, 48, 0, 16, 0, 0};
// {10001111 11000000 00000000 00000000}
public static final byte[] GRPSPLIT6 = new byte[] {-15, 3, 0, 0};
// {...1 0-bits... 11101111 11111011 11111111 11011111 11111111 11111111 10011111 11111101 11110111 11111111 11111111 11111111 11111111 11111111 11111111 11111110}
public static final byte[] GRPSPLIT7 = new byte[] {-9, -33, -1, -5, -1, -1, -7, -65, -17, -1, -1, -1, -1, -1, -1, 127, 0};
// {...1 0-bits... 00000010 00000000 00000000 00000010 00000000 00000001 00000000 00000000 00010000 00000000 00000000 00000000 00000000 00000000 00000000 10000000 010}
public static final byte[] GRPSPLIT8 = new byte[] {64, 0, 0, 64, 0, -128, 0, 0, 8, 0, 0, 0, 0, 0, 0, 1, 2};
// {...1 0-bits... 11101111 11111111 11111111 01111111 11111111 11111011 11111111 11111111 11111111 11011111 11110111 11111111 11111110 11111111 01101111 11011111 111}
public static final byte[] GRPSPLIT9 = new byte[] {-9, -1, -1, -2, -1, -33, -1, -1, -1, -5, -17, -1, 127, -1, -10, -5, 15};
// {...1 0-bits... 11111101 11111111 00111111 11011111 11111111 11111111 10011111 01111101 11111111 11111111 11111011 11111111 11111111 11111111 11111110 11111110}
public static final byte[] GRPSPLIT10 = new byte[] {-65, -1, -4, -5, -1, -1, -7, -66, -1, -1, -33, -1, -1, -1, 127, 127, 0};
// {...1 0-bits... 01111111 11101111 11111111 11111111 10110101 11111111 11110111 11111111 11011111 01000111 11111111 10111011 11111111 11111111 01111111 01111111 111}
public static final byte[] GRPSPLIT11 = new byte[] {-2, -9, -1, -1, -83, -1, -17, -1, -5, -30, -1, -35, -1, -1, -2, -2, 15};
// {...1 0-bits... 01111111 11111011 10011111 11111101 11110111 11111110 11111111 11111110 11001111 11110101 11111111 11101111 11111110 11101111 11111111 10011111 101}
public static final byte[] GRPSPLIT12 = new byte[] {-2, -33, -7, -65, -17, 127, -1, 127, -13, -81, -1, -9, 127, -9, -1, -7, 13};
// {01000001 00101000 00010001 00010001 01001101 10010010 10111101 00000001 01011101 00011101 00000000 10111001 10111100 00110011 01001011 01110100 001100}
public static final byte[] GRPSPLIT13 = new byte[] {-126, 20, -120, -120, -78, 73, -67, -128, -70, -72, 0, -99, 61, -52, -46, 46, 12};
// {...3 0-bits... 10000010 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00011010 00000000 00000000 10000000 00000000 10000000 00000000 0}
public static final byte[] GRPSPLIT14 = new byte[] {65, 0, 0, 0, 0, 0, 0, 0, 88, 0, 0, 1, 0, 1, 0, 0};
// {11110111 11111111 11111111 11011011 01111111 10111111 11111101 10011111 01111111 11111111 10010111 11111111 11111110 01111111 11011111 11111111 11111}
public static final byte[] GRPSPLIT15 = new byte[] {-17, -1, -1, -37, -2, -3, -65, -7, -2, -1, -23, -1, 127, -2, -5, -1, 31};
// {10110010 01000001 00000000 00100000 10100000 00100001 00000000 00000100 10000000 00000000 01000000 00000100 01000000 10001000 10000100 00000000 10000}
public static final byte[] GRPSPLIT16 = new byte[] {77, -126, 0, 4, 5, -124, 0, 32, 1, 0, 2, 32, 2, 17, 33, 0, 1};
// {...3 0-bits... 11111111 11111111 01111111 11110110 11111111 11111111 11101111 11101101 11101111 11011111 11111011 01111010 11111101 11111101 11110111 1}
public static final byte[] GRPSPLIT17 = new byte[] {-1, -1, -2, 111, -1, -1, -9, -73, -9, -5, -33, 94, -65, -65, -17, 7};
// {11101111 10011111 11101111 10111001 11011101 11011111 11111111 01111011 11101111 11101111 11111110 10011101 11011111 00111111 10101101 11111111 1111}
public static final byte[] GRPSPLIT18 = new byte[] {-9, -7, -9, -99, -69, -5, -1, -34, -9, -9, 127, -71, -5, -4, -75, -1, 15};
// {...1 0-bits... 11111111 10111111 11111111 11111111 01110111 11111110 11110111 11111101 01000111 11111111 11111111 10011101 11000111 11011101 11111001 11010111 11}
public static final byte[] GRPSPLIT19 = new byte[] {-1, -3, -1, -1, -18, 127, -17, -65, -30, -1, -1, -71, -29, -69, -97, -21, 3};
// {11111110 11111111 11111111 11111111 11111011 11111111 11100111 11111111 11111111 11100110 11111111 01111111 11111011 11101111 10111110 11111011 111111}
public static final byte[] GRPSPLIT20 = new byte[] {127, -1, -1, -1, -33, -1, -25, -1, -1, 103, -1, -2, -33, -9, 125, -33, 63};
// {11111101 11111111 11111111 11111111 11111111 11111111 01111110 11111101 10111100 11111111 11111111 01101111 01111111 11111111 11111111 11111101 1111}
public static final byte[] GRPSPLIT21 = new byte[] {-65, -1, -1, -1, -1, -1, 126, -65, 61, -1, -1, -10, -2, -1, -1, -65, 15};
}


class gbm_pojo_test_Forest_7 {
public static void score0(double[] fdata, double[] preds) {
  preds[1] += gbm_pojo_test_Tree_7_class_0.score0(fdata);
}
}
class gbm_pojo_test_Tree_7_class_0 {
static final double score0(double[] data) {
  double pred =      (data[2 /* DayofMonth */] <6.5f ? 
       (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT0, 132, 0, data[9])) ? 
           (data[5 /* CRSArrTime */] <1041.5f ? 
               (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(133, 1, data[10]) || (GenModel.bitSetIsInRange(133, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT1, 133, 1, data[10])) ? 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT2, 132, 0, data[9])) ? 
                      -0.077967025f : 
                      0.025046602f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT3, 132, 0, data[9])) ? 
                      -0.07011788f : 
                      0.035904076f)) : 
               (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(133, 1, data[10]) || (GenModel.bitSetIsInRange(133, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT4, 133, 1, data[10])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT5, 132, 0, data[9])) ? 
                      -0.042469706f : 
                      0.016547324f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT6, 132, 0, data[9])) ? 
                      -0.01932079f : 
                      0.076523185f))) : 
           (data[4 /* CRSDepTime */] <829.5f ? 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(128, 3, data[9]) && !GenModel.bitSetContains(GRPSPLIT7, 128, 3, data[9])) ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT8, 133, 0, data[10])) ? 
                      -0.16542368f : 
                      -0.0136284735f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT9, 133, 0, data[10])) ? 
                      -0.01292895f : 
                      0.0797437f)) : 
               (Double.isNaN(data[0]) || data[0 /* Year */] <2007.5f ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT10, 133, 0, data[10])) ? 
                      -0.0094185425f : 
                      0.061720364f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT11, 133, 0, data[10])) ? 
                      0.08849967f : 
                      0.12887704f)))) : 
       (Double.isNaN(data[0]) || data[0 /* Year */] <1998.0f ? 
           (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT12, 132, 0, data[9])) ? 
               (data[0 /* Year */] <1987.5f ? 
                   (data[4 /* CRSDepTime */] <1211.5f ? 
                      -0.05261361f : 
                      0.059977558f) : 
                   (Double.isNaN(data[2]) || data[2 /* DayofMonth */] <20.5f ? 
                      -0.01599408f : 
                      -0.055944283f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT13, 134, 0, data[10])) ? 
                   (Double.isNaN(data[0]) || data[0 /* Year */] <1993.5f ? 
                      -0.0101492535f : 
                      0.057988476f) : 
                   (!Double.isNaN(data[6 /* UniqueCarrier */]) && (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT14, 32, 0, data[6])) ? 
                      -0.022203758f : 
                      0.04871426f))) : 
           (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT15, 134, 0, data[10])) ? 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT16, 132, 0, data[9])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT17, 132, 0, data[9])) ? 
                      -0.13240871f : 
                      -0.086979926f) : 
                   (Double.isNaN(data[0]) || data[0 /* Year */] <2004.5f ? 
                      -0.06318535f : 
                      -0.011012612f)) : 
               (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT18, 132, 0, data[9])) ? 
                   (data[5 /* CRSArrTime */] <1327.5f ? 
                      -0.06991185f : 
                      -0.02522777f) : 
                   (data[4 /* CRSDepTime */] <1143.5f ? 
                      -0.025657265f : 
                      0.028593613f)))));
  return pred;
} // constant pool size = 454B, number of visited nodes = 31, static init size = 1968B
// {00010010 01000000 10000100 00000101 01101111 00110000 01000101 10000110 11001011 01001101 00100000 11111011 11100011 01010001 11100001 00111010 0010}
public static final byte[] GRPSPLIT0 = new byte[] {72, 2, 33, -96, -10, 12, -94, 97, -45, -78, 4, -33, -57, -118, -121, 92, 4};
// {...1 0-bits... 11111111 11111101 00111111 00110101 10111111 11111011 11111111 10110000 11101111 01011110 11111111 10000011 11100111 10111000 01101101 01011111 1101}
public static final byte[] GRPSPLIT1 = new byte[] {-1, -65, -4, -84, -3, -33, -1, 13, -9, 122, -1, -63, -25, 29, -74, -6, 27};
// {10000000 00000100 00000000 01000000 00000000 00000010 00000010 01000000 00000000 00000000 00000011 00000000 00000000 10000000 00000000 00000000 1100}
public static final byte[] GRPSPLIT2 = new byte[] {1, 32, 0, 2, 0, 64, 64, 2, 0, 0, -64, 0, 0, 1, 0, 0, 3};
// {11110111 01101101 11111111 10101111 11111111 11111101 11001111 11111110 11111011 11111111 11111100 11111111 11110111 01111111 11110111 11111011 0011}
public static final byte[] GRPSPLIT3 = new byte[] {-17, -74, -1, -11, -1, -65, -13, 127, -33, -1, 63, -1, -17, -2, -17, -33, 12};
// {...1 0-bits... 10100110 01010100 00000001 00100010 00000000 01000001 00000010 00001010 00011100 01100011 00000000 00111001 10000010 01100110 00011010 00001001 0101}
public static final byte[] GRPSPLIT4 = new byte[] {101, 42, -128, 68, 0, -126, 64, 80, 56, -58, 0, -100, 65, 102, 88, -112, 10};
// {01011110 11101101 11110111 11100111 01101111 11110100 11100101 11000110 11011111 11001111 11101011 11111111 11111011 11110111 11111101 11111110 0111}
public static final byte[] GRPSPLIT5 = new byte[] {122, -73, -17, -25, -10, 47, -89, 99, -5, -13, -41, -1, -33, -17, -65, 127, 14};
// {11111111 11101111 11111111 11111111 11111111 11111000 11111111 11111110 11111111 11111111 01111101 11111111 11111011 11111101 11111101 11111111 0110}
public static final byte[] GRPSPLIT6 = new byte[] {-1, -9, -1, -1, -1, 31, -1, 127, -1, -1, -66, -1, -33, -65, -65, -1, 6};
// {...3 0-bits... 11111111 11111111 11111111 11111101 10111111 11111111 11111011 11011101 11111111 11111110 11111001 10101011 11111101 01111001 11111111 11101}
public static final byte[] GRPSPLIT7 = new byte[] {-1, -1, -1, -65, -3, -1, -33, -69, -1, 127, -97, -43, -65, -98, -1, -9};
// {10111111 11111110 11111111 11101111 11111110 11111111 11111111 11011101 11111111 11110111 01111011 11111111 10111111 01011111 01111001 11101111 11110}
public static final byte[] GRPSPLIT8 = new byte[] {-3, 127, -1, -9, 127, -1, -1, -69, -1, -17, -34, -1, -3, -6, -98, -9, 15};
// {11111111 11010000 11011111 11101111 11011110 11111101 11111111 11111111 01111111 11110110 11111111 01111111 10111111 01010111 11111111 11001011 11100}
public static final byte[] GRPSPLIT9 = new byte[] {-1, 11, -5, -9, 123, -65, -1, -1, -2, 111, -1, -2, -3, -22, -1, -45, 7};
// {11111111 11001101 10011111 11110111 01111111 10111111 10101101 00011111 01111111 11111111 11110111 11101110 11111111 01100110 11011011 11111111 01111}
public static final byte[] GRPSPLIT10 = new byte[] {-1, -77, -7, -17, -2, -3, -75, -8, -2, -1, -17, 119, -1, 102, -37, -1, 30};
// {10111111 11110011 11001111 10101110 11111111 11111111 01111110 11111011 11101111 11111011 01111011 11111111 11101111 01110110 10111110 11101011 11011}
public static final byte[] GRPSPLIT11 = new byte[] {-3, -49, -13, 117, -1, -1, 126, -33, -9, -33, -34, -1, -9, 110, 125, -41, 27};
// {00011110 01111011 11010110 11010111 01110111 01111100 11110110 10011011 11011111 00111100 00000011 11111011 11000101 11111101 11001011 01111000 1001}
public static final byte[] GRPSPLIT12 = new byte[] {120, -34, 107, -21, -18, 62, 111, -39, -5, 60, -64, -33, -93, -65, -45, 30, 9};
// {01000000 00000001 00011001 00010000 01100000 00000010 10111000 01000000 00001001 00101101 01000001 00000000 10111100 10011001 10000111 00000100 101000}
public static final byte[] GRPSPLIT13 = new byte[] {2, -128, -104, 8, 6, 64, 29, 2, -112, -76, -126, 0, 61, -103, -31, 32, 5};
// {11101111 11000000 00000000 00000000}
public static final byte[] GRPSPLIT14 = new byte[] {-9, 3, 0, 0};
// {00000100 00100101 00010000 00010010 00010000 00110010 10000010 00101000 11000001 00000100 10001000 00000110 11011000 11000011 01100100 11100100 101000}
public static final byte[] GRPSPLIT15 = new byte[] {32, -92, 8, 72, 8, 76, 65, 20, -125, 32, 17, 96, 27, -61, 38, 39, 5};
// {00010010 11010101 11010000 10001000 11001110 10100000 01000000 00000000 01000001 00000000 00010000 01110010 10101000 00000000 11110010 00001000 0000}
public static final byte[] GRPSPLIT16 = new byte[] {72, -85, 11, 17, 115, 5, 2, 0, -126, 0, 8, 78, 21, 0, 79, 16, 0};
// {01111111 11111111 11111111 10111111 11111111 11101111 11111111 11011111 11111111 01111011 10110111 01111111 11101101 00000111 11111010 00111111 1111}
public static final byte[] GRPSPLIT17 = new byte[] {-2, -1, -1, -3, -1, -9, -1, -5, -1, -34, -19, -2, -73, -32, 95, -4, 15};
// {00000110 01000100 10010000 10000000 10101000 10110001 00000000 01100000 00000001 10000010 01000000 01111011 00000000 10010000 10010110 10101010 0000}
public static final byte[] GRPSPLIT18 = new byte[] {96, 34, 9, 1, 21, -115, 0, 6, -128, 65, 2, -34, 0, 9, 105, 85, 0};
}


class gbm_pojo_test_Forest_8 {
public static void score0(double[] fdata, double[] preds) {
  preds[1] += gbm_pojo_test_Tree_8_class_0.score0(fdata);
}
}
class gbm_pojo_test_Tree_8_class_0 {
static final double score0(double[] data) {
  double pred =      (data[2 /* DayofMonth */] <6.5f ? 
       (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT0, 132, 0, data[9])) ? 
           (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT1, 134, 0, data[10])) ? 
               (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT2, 132, 0, data[9])) ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(134, 0, data[10]) || (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT3, 134, 0, data[10])) ? 
                      -0.08960409f : 
                      -0.036350418f) : 
                   (data[0 /* Year */] <1993.5f ? 
                      -0.05960466f : 
                      0.03154628f)) : 
               (data[5 /* CRSArrTime */] <1014.5f ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(132, 1, data[10]) || (GenModel.bitSetIsInRange(132, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT4, 132, 1, data[10])) ? 
                      -0.045764044f : 
                      0.052963074f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT5, 132, 0, data[9])) ? 
                      -0.018311732f : 
                      0.037378646f))) : 
           (!Double.isNaN(data[6 /* UniqueCarrier */]) && (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT6, 32, 0, data[6])) ? 
               (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT7, 132, 0, data[9])) ? 
                   (data[2 /* DayofMonth */] <2.5f ? 
                      -0.12705249f : 
                      -0.05079043f) : 
                   (Double.isNaN(data[0]) || data[0 /* Year */] <1999.0f ? 
                      0.074032426f : 
                      -0.03185142f)) : 
               (data[4 /* CRSDepTime */] <1106.5f ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT8, 133, 0, data[10])) ? 
                      -0.014158565f : 
                      0.044793554f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT9, 133, 0, data[10])) ? 
                      0.03150996f : 
                      0.08309605f)))) : 
       (Double.isNaN(data[0]) || data[0 /* Year */] <1999.5f ? 
           (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT10, 132, 0, data[9])) ? 
               (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(134, 0, data[10]) || (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT11, 134, 0, data[10])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT12, 132, 0, data[9])) ? 
                      -0.13306955f : 
                      -0.037829727f) : 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT13, 132, 0, data[9])) ? 
                      -0.021149727f : 
                      0.028858071f)) : 
               (!Double.isNaN(data[6 /* UniqueCarrier */]) && (GenModel.bitSetIsInRange(32, 0, data[6]) && !GenModel.bitSetContains(GRPSPLIT14, 32, 0, data[6])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT15, 132, 0, data[9])) ? 
                      -0.07009283f : 
                      -0.004869465f) : 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(134, 0, data[10]) || (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT16, 134, 0, data[10])) ? 
                      0.015709344f : 
                      0.054861084f))) : 
           (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT17, 132, 0, data[9])) ? 
               (data[5 /* CRSArrTime */] <1438.5f ? 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(130, 0, data[9]) || (GenModel.bitSetIsInRange(130, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT18, 130, 0, data[9])) ? 
                      -0.10968897f : 
                      -0.0684205f) : 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(131, 0, data[10]) || (GenModel.bitSetIsInRange(131, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT19, 131, 0, data[10])) ? 
                      -0.06700152f : 
                      -0.0053774575f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT20, 134, 0, data[10])) ? 
                   (Double.isNaN(data[0]) || data[0 /* Year */] <2004.5f ? 
                      -0.07106419f : 
                      -0.005718407f) : 
                   (data[5 /* CRSArrTime */] <1548.5f ? 
                      -0.028868884f : 
                      0.019552873f)))));
  return pred;
} // constant pool size = 478B, number of visited nodes = 31, static init size = 2112B
// {01010010 01000000 10000100 00000101 01101111 00110000 01000101 00000110 11001011 01001101 00100001 11111011 11110011 01010001 11100001 00111010 0010}
public static final byte[] GRPSPLIT0 = new byte[] {74, 2, 33, -96, -10, 12, -94, 96, -45, -78, -124, -33, -49, -118, -121, 92, 4};
// {11110111 11111110 10110101 11011011 01011111 11111111 11110111 10010101 01011111 10111111 11111101 10011101 11111111 11111110 10111111 11101111 111110}
public static final byte[] GRPSPLIT1 = new byte[] {-17, 127, -83, -37, -6, -1, -17, -87, -6, -3, -65, -71, -1, 127, -3, -9, 31};
// {00000000 00010100 01000000 01000000 00000000 10000010 00000000 00000000 00000000 00000000 00001000 00000000 00001000 00000100 00001110 00000000 0000}
public static final byte[] GRPSPLIT2 = new byte[] {0, 40, 2, 2, 0, 65, 0, 0, 0, 0, 16, 0, 16, 32, 112, 0, 0};
// {00001000 00000000 01001010 00000000 00000000 00000000 00000000 00100000 00000000 01000000 00000010 01100010 00000000 00000001 01000000 00000000 000000}
public static final byte[] GRPSPLIT3 = new byte[] {16, 0, 82, 0, 0, 0, 0, 4, 0, 2, 64, 70, 0, -128, 2, 0, 0};
// {...1 0-bits... 10100000 00010000 00000000 00100000 10000010 00000000 00000010 00000000 00000000 00110010 00001000 00000001 10000000 00110000 00000000 01001000 000}
public static final byte[] GRPSPLIT4 = new byte[] {5, 8, 0, 4, 65, 0, 64, 0, 0, 76, 16, -128, 1, 12, 0, 18, 0};
// {01011110 11101111 11011111 11100111 11111111 11111100 11110111 11111110 11111111 11101111 00101011 11111111 11111011 11111101 11110101 11111111 0110}
public static final byte[] GRPSPLIT5 = new byte[] {122, -9, -5, -25, -1, 63, -17, 127, -1, -9, -44, -1, -33, -65, -81, -1, 6};
// {11101111 11000000 00000000 00000000}
public static final byte[] GRPSPLIT6 = new byte[] {-9, 3, 0, 0};
// {00000010 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000 0000}
public static final byte[] GRPSPLIT7 = new byte[] {64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
// {11111111 11100100 11011111 10001111 10101010 11011111 11100110 01011011 01111111 11110101 01111011 11101011 10111111 01100101 11111111 11101011 11110}
public static final byte[] GRPSPLIT8 = new byte[] {-1, 39, -5, -15, 85, -5, 103, -38, -2, -81, -34, -41, -3, -90, -1, -41, 15};
// {10100101 10111111 10011111 11110111 01100111 10111111 10111101 10011111 11111111 11111111 10010011 11101010 10111011 00101110 11011001 11111111 01101}
public static final byte[] GRPSPLIT9 = new byte[] {-91, -3, -7, -17, -26, -3, -67, -7, -1, -1, -55, 87, -35, 116, -101, -1, 22};
// {00010010 01000001 11000110 11000101 11100001 00100010 00010110 00000010 11001011 00011100 00000001 01110011 11000101 10000000 11000011 00111010 0000}
public static final byte[] GRPSPLIT10 = new byte[] {72, -126, 99, -93, -121, 68, 104, 64, -45, 56, -128, -50, -93, 1, -61, 92, 0};
// {11111111 11111111 10001111 11011111 11011111 11111111 11110111 11011011 11111111 01111011 11111011 01111011 11110110 11111011 01111110 11111111 110110}
public static final byte[] GRPSPLIT11 = new byte[] {-1, -1, -15, -5, -5, -1, -17, -37, -1, -34, -33, -34, 111, -33, 126, -1, 27};
// {11111111 11111111 11111111 11111111 11111111 11111111 11111111 10111111 11011111 11111111 11111111 11111111 11111111 10111111 11111111 11111011 1011}
public static final byte[] GRPSPLIT12 = new byte[] {-1, -1, -1, -1, -1, -1, -1, -3, -5, -1, -1, -1, -1, -3, -1, -33, 13};
// {00000000 00000000 00110000 00000000 00001000 10000000 00000000 01000000 00000000 00000000 00000000 00000000 00000000 01100000 00000000 00000000 0100}
public static final byte[] GRPSPLIT13 = new byte[] {0, 0, 12, 0, 16, 1, 0, 2, 0, 0, 0, 0, 0, 6, 0, 0, 2};
// {11101111 11000000 00000000 00000000}
public static final byte[] GRPSPLIT14 = new byte[] {-9, 3, 0, 0};
// {11111111 11111111 11111111 11111111 10111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111 11111111 1111}
public static final byte[] GRPSPLIT15 = new byte[] {-1, -1, -1, -1, -3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 15};
// {01000000 00000000 00001001 10010000 01000000 00000010 10100000 01000000 00000000 00101100 01000000 00000000 10101000 00000000 10000110 00000100 001000}
public static final byte[] GRPSPLIT16 = new byte[] {2, 0, -112, 9, 2, 64, 5, 2, 0, 52, 2, 0, 21, 0, 97, 32, 4};
// {00010110 01010101 10110000 11001100 11010100 00100001 01000000 00100000 00000001 10000000 01000000 01011010 00001100 00010000 00011110 00000000 0000}
public static final byte[] GRPSPLIT17 = new byte[] {104, -86, 13, 51, 43, -124, 2, 4, -128, 1, 2, 90, 48, 8, 120, 0, 0};
// {01000000 10000010 01000010 00000001 00001000 10000000 00001010 01000100 00000010 00001010 00010000 00000000 10000010 00100000 01000000 00000000 01}
public static final byte[] GRPSPLIT18 = new byte[] {2, 65, 66, -128, 16, 1, 80, 34, 64, 80, 8, 0, 65, 4, 2, 0, 2};
// {00000010 00000000 01000000 01000000 00000000 00100000 00000010 01000100 00001000 00000000 00000000 00001100 01010000 00000000 00100000 00100000 001}
public static final byte[] GRPSPLIT19 = new byte[] {64, 0, 2, 2, 0, 4, 64, 34, 16, 0, 0, 48, 10, 0, 4, 4, 4};
// {00000000 00100101 00010000 00110010 01000000 00110010 10000000 00100000 10010000 00000100 10001100 10000110 10001100 11000011 01000110 11000100 101000}
public static final byte[] GRPSPLIT20 = new byte[] {0, -92, 8, 76, 2, 76, 1, 4, 9, 32, 49, 97, 49, -61, 98, 35, 5};
}


class gbm_pojo_test_Forest_9 {
public static void score0(double[] fdata, double[] preds) {
  preds[1] += gbm_pojo_test_Tree_9_class_0.score0(fdata);
}
}
class gbm_pojo_test_Tree_9_class_0 {
static final double score0(double[] data) {
  double pred =      (data[2 /* DayofMonth */] <6.5f ? 
       (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT0, 132, 0, data[9])) ? 
           (data[5 /* CRSArrTime */] <1041.5f ? 
               (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(133, 1, data[10]) || (GenModel.bitSetIsInRange(133, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT1, 133, 1, data[10])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 1, data[9]) && !GenModel.bitSetContains(GRPSPLIT2, 131, 1, data[9])) ? 
                      -0.15235789f : 
                      -0.048692f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(131, 1, data[9]) && !GenModel.bitSetContains(GRPSPLIT3, 131, 1, data[9])) ? 
                      -0.06283237f : 
                      0.03258739f)) : 
               (Double.isNaN(data[0]) || data[0 /* Year */] <2007.5f ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT4, 133, 1, data[10])) ? 
                      -0.034710344f : 
                      0.022741744f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 1, data[10]) && !GenModel.bitSetContains(GRPSPLIT5, 133, 1, data[10])) ? 
                      0.033610754f : 
                      0.11563232f))) : 
           (data[2 /* DayofMonth */] <1.5f ? 
               (Double.isNaN(data[0]) || data[0 /* Year */] <1998.0f ? 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT6, 133, 0, data[10])) ? 
                      -0.094179556f : 
                      0.06645071f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT7, 133, 0, data[10])) ? 
                      -0.12691893f : 
                      0.017173646f)) : 
               (data[4 /* CRSDepTime */] <717.5f ? 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(131, 0, data[9]) || (GenModel.bitSetIsInRange(131, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT8, 131, 0, data[9])) ? 
                      -0.052207164f : 
                      0.082908764f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT9, 133, 0, data[10])) ? 
                      0.02356362f : 
                      0.07073981f)))) : 
       (Double.isNaN(data[0]) || data[0 /* Year */] <1998.0f ? 
           (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT10, 132, 0, data[9])) ? 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT11, 134, 0, data[10])) ? 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT12, 132, 0, data[9])) ? 
                      -0.13247876f : 
                      -0.03522225f) : 
                   (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT13, 132, 0, data[9])) ? 
                      -0.033556167f : 
                      0.013424614f)) : 
               (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(134, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT14, 134, 0, data[10])) ? 
                   (Double.isNaN(data[5]) || data[5 /* CRSArrTime */] <1678.0f ? 
                      -0.049635213f : 
                      0.027109658f) : 
                   (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(132, 0, data[9]) || (GenModel.bitSetIsInRange(132, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT15, 132, 0, data[9])) ? 
                      0.017906439f : 
                      0.049174905f))) : 
           (Double.isNaN(data[5]) || data[5 /* CRSArrTime */] <1631.5f ? 
               (Double.isNaN(data[9 /* Origin */]) || !GenModel.bitSetIsInRange(130, 0, data[9]) || (GenModel.bitSetIsInRange(130, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT16, 130, 0, data[9])) ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(133, 0, data[10]) || (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT17, 133, 0, data[10])) ? 
                      -0.07433795f : 
                      -0.040629175f) : 
                   (data[0 /* Year */] <2000.5f ? 
                      0.020971054f : 
                      -0.029973244f)) : 
               (!Double.isNaN(data[9 /* Origin */]) && (GenModel.bitSetIsInRange(130, 0, data[9]) && !GenModel.bitSetContains(GRPSPLIT18, 130, 0, data[9])) ? 
                   (Double.isNaN(data[10 /* Dest */]) || !GenModel.bitSetIsInRange(133, 0, data[10]) || (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT19, 133, 0, data[10])) ? 
                      -0.06287862f : 
                      -0.004335585f) : 
                   (!Double.isNaN(data[10 /* Dest */]) && (GenModel.bitSetIsInRange(133, 0, data[10]) && !GenModel.bitSetContains(GRPSPLIT20, 133, 0, data[10])) ? 
                      -0.04766358f : 
                      0.024850857f)))));
  return pred;
} // constant pool size = 504B, number of visited nodes = 31, static init size = 2268B
// {10010010 00000000 10000100 00000101 01101111 00110000 01000101 00000110 11001011 01001101 00100000 11110011 11100011 01010001 11100001 00111010 0010}
public static final byte[] GRPSPLIT0 = new byte[] {73, 0, 33, -96, -10, 12, -94, 96, -45, -78, 4, -49, -57, -118, -121, 92, 4};
// {...1 0-bits... 11011111 10111101 10111111 00110101 10111111 11111011 11111101 11110000 11101111 01011110 11111011 10000111 11100111 10111001 01101101 01011111 1101}
public static final byte[] GRPSPLIT1 = new byte[] {-5, -67, -3, -84, -3, -33, -65, 15, -9, 122, -33, -31, -25, -99, -74, -6, 27};
// {...1 0-bits... 11101111 11101011 11111111 11111011 11111111 11111111 10111111 11111101 11011111 11111111 11111111 11101111 11111111 10111111 11111010 11111111 11}
public static final byte[] GRPSPLIT2 = new byte[] {-9, -41, -1, -33, -1, -1, -3, -65, -5, -1, -1, -9, -1, -3, 95, -1, 7};
// {...1 0-bits... 11101110 11011011 11111011 01011111 11111110 11111011 10011110 11111111 11110111 11111111 11111001 11111111 11101110 11111111 11101111 11111110 01}
public static final byte[] GRPSPLIT3 = new byte[] {119, -37, -33, -6, 127, -33, 121, -1, -17, -1, -97, -1, 119, -1, -9, 127, 6};
// {...1 0-bits... 11101111 11111101 00100011 10110110 00111111 11110111 11101111 01101010 10011101 01101111 11110110 00101011 11111010 11101110 11111111 00011111 1111}
public static final byte[] GRPSPLIT4 = new byte[] {-9, -65, -60, 109, -4, -17, -9, 86, -71, -10, 111, -44, 95, 119, -1, -8, 15};
// {...1 0-bits... 01111111 11110111 11011111 11111111 10111111 11111011 11111111 11111111 11111111 11101111 11111111 11111011 01111111 11111111 01110111 11110111 1111}
public static final byte[] GRPSPLIT5 = new byte[] {-2, -17, -5, -1, -3, -33, -1, -1, -1, -9, -1, -33, -2, -1, -18, -17, 31};
// {11111111 11001010 01111111 11011111 01111011 01111101 11110111 01011111 01111111 11111111 10111111 11111100 11111111 11101101 11111110 11101011 00110}
public static final byte[] GRPSPLIT6 = new byte[] {-1, 83, -2, -5, -34, -66, -17, -6, -2, -1, -3, 63, -1, -73, 127, -41, 12};
// {10111100 11110101 10111111 11110111 10111111 10111111 11111111 11011111 11110110 11111111 00011111 01101011 11110011 00101111 10010000 10011011 11001}
public static final byte[] GRPSPLIT7 = new byte[] {61, -81, -3, -17, -3, -3, -1, -5, 111, -1, -8, -42, -49, -12, 9, -39, 19};
// {00000000 00000000 00000000 00000001 00100010 00110000 01000000 00000000 10000001 01001001 00000000 00100000 00100010 00010000 11000000 00001010 000}
public static final byte[] GRPSPLIT8 = new byte[] {0, 0, 0, -128, 68, 12, 2, 0, -127, -110, 0, 4, 68, 8, 3, 80, 0};
// {01111111 10001101 11011111 10110111 01110111 10011111 00101100 00011111 11111111 11111111 11110011 11101111 10111010 01101111 11011111 11101111 01110}
public static final byte[] GRPSPLIT9 = new byte[] {-2, -79, -5, -19, -18, -7, 52, -8, -1, -1, -49, -9, 93, -10, -5, -9, 14};
// {00010010 01000001 11010110 11000101 01100001 00100000 00000110 00000010 11001011 00011100 00000001 01010011 11000101 11000000 11000011 00011000 0000}
public static final byte[] GRPSPLIT10 = new byte[] {72, -126, 107, -93, -122, 4, 96, 64, -45, 56, -128, -54, -93, 3, -61, 24, 0};
// {11111111 11111111 11101111 11011111 10011111 11111111 11110111 11011011 11111111 01111011 11111011 01111011 11110110 11111011 01111110 11111111 011111}
public static final byte[] GRPSPLIT11 = new byte[] {-1, -1, -9, -5, -7, -1, -17, -37, -1, -34, -33, -34, 111, -33, 126, -1, 62};
// {11111111 11111111 11111111 11111111 11111111 11111111 11111111 10111111 11011111 11111111 11111111 11011111 11111111 11111111 11111111 11111011 1011}
public static final byte[] GRPSPLIT12 = new byte[] {-1, -1, -1, -1, -1, -1, -1, -3, -5, -1, -1, -5, -1, -1, -1, -33, 13};
// {10000000 00000000 00100000 00001000 10001000 10000010 00010001 01100100 00000000 00000000 00001000 00100000 00101000 00100010 00000100 00000010 0100}
public static final byte[] GRPSPLIT13 = new byte[] {1, 0, 4, 16, 17, 65, -120, 38, 0, 0, 16, 4, 20, 68, 32, 64, 2};
// {11111111 11101011 00111011 10111111 01101101 11110011 11111111 01001101 11111101 11111101 11101111 11111111 11111110 11011111 11101111 11111110 101111}
public static final byte[] GRPSPLIT14 = new byte[] {-1, -41, -36, -3, -74, -49, -1, -78, -65, -65, -9, -1, 127, -5, -9, 127, 61};
// {00000010 01000000 10000100 11000100 00000001 00000000 00000110 00000000 11001010 00000100 00000001 00010000 11000001 00000000 00000001 00000000 0000}
public static final byte[] GRPSPLIT15 = new byte[] {64, 2, 33, 35, -128, 0, 96, 0, 83, 32, -128, 8, -125, 0, -128, 0, 0};
// {00010110 00000100 00010000 01000000 10111100 00000001 01000000 00000000 00000001 10000000 00000000 00001000 00000000 10000000 00011000 00001010 00}
public static final byte[] GRPSPLIT16 = new byte[] {104, 32, 8, 2, 61, -128, 2, 0, -128, 1, 0, 16, 0, 1, 24, 80, 0};
// {01000110 01000101 00000000 00010000 00010100 00110000 10000000 00100100 11010000 00001100 11001010 00000010 01001000 00011001 10000000 10000000 00100}
public static final byte[] GRPSPLIT17 = new byte[] {98, -94, 0, 8, 40, 12, 1, 36, 11, 48, 83, 64, 18, -104, 1, 1, 4};
// {01000010 01000101 11010011 00100100 11000000 10100000 00000000 00100000 00000001 00000000 01100000 01111010 00001100 11010000 10010010 10100010 00}
public static final byte[] GRPSPLIT18 = new byte[] {66, -94, -53, 36, 3, 5, 0, 4, -128, 0, 6, 94, 48, 11, 73, 69, 0};
// {00000000 00100100 01010000 10000000 00000000 00100000 00100010 00001000 00001001 00000110 10000000 00001110 11010000 10000111 00100000 01110100 10100}
public static final byte[] GRPSPLIT19 = new byte[] {0, 36, 10, 1, 0, 4, 68, 16, -112, 96, 1, 112, 11, -31, 4, 46, 5};
// {11111111 11111101 11111111 11111111 11111011 11111111 01111111 11011011 11111111 11110101 10111011 11010111 11111011 11110111 11111110 11101011 11100}
public static final byte[] GRPSPLIT20 = new byte[] {-1, -65, -1, -1, -33, -1, -2, -37, -1, -81, -35, -21, -33, -17, 127, -41, 7};
}




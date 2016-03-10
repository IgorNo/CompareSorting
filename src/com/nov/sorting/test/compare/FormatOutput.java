package com.nov.sorting.test.compare;

public class FormatOutput {

	public static String fixLength(String strIn,int nFix){
		
		String strOut=strIn;
		if (nFix > strIn.length()) {
			for (int i=0; i<(nFix-strIn.length());i++) strOut+=" "; 
		} else strOut = strIn.substring(0, nFix);
		
		return strOut;
	}
	
	public static String genLength(String strIn,int n){
		
		String strOut=strIn;
		for (int i=1; i<n;i++) strOut+=strIn; 

		return strOut;
	}
	
}

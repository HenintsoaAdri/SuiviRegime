package s6.suiviRegime.utilitaire;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class StringUtil {
	private StringUtil(){}
	private static class Holder
	{		
		private final static StringUtil instance = new StringUtil();
	}
	public static StringUtil getInstance(){
		return Holder.instance;
	}
	
	public boolean fullLetter(String string){
		boolean isLetter=true;
		int i=0;
		while(isLetter&&i<string.length()){
			isLetter = Character.isLetter(string.charAt(i));
			if(Character.isSpaceChar(string.charAt(i)))isLetter = true;
			i++;
		}
		return isLetter;
	}
	public boolean fullNumber(String string){
		boolean isNumber=true;
		int i=0;
		while(isNumber&&i<string.length()){
			isNumber = Character.isDigit(string.charAt(i));
			i++;
		}
		return isNumber;
	}
	public boolean isEmail(String string){
		String [] split1 = string.split("@");
		if(split1.length != 2){
			return false;
		}
		String [] split2 = split1[1].split("\\.");
		if(split2.length != 2){
			return false;
		}
		return true;
	}
	public boolean isTelephone(String string){
		if(string.startsWith("\\+")){
			return fullNumber(string.substring(1));
		}
		return fullNumber(string.substring(1));
	}
	public boolean correctMdp(String string){
		
		int compteurMaj = 0;
		int compteurDigit = 0;
		int compteurSpec = 0;
		int i = 0;
		while(i<string.length()){
			char c = string.charAt(i);
			int cint = (int)c;
			if(Character.isDigit(c)){
				compteurDigit ++;
			}
			if(Character.isUpperCase(c)){
				compteurMaj ++;
			}
			if(cint <48 || (cint > 57 && cint < 65) || (cint > 90 && cint < 97) || cint > 122)
		    {
				compteurSpec ++;
		    }
		}
		return compteurMaj>0&&compteurDigit>0&&compteurSpec>0&&string.length()>=8;
	}

    public String firstUpper(String string){
    	return string.replaceFirst(String.valueOf(string.charAt(0)), String.valueOf(string.charAt(0)).toUpperCase());
    }
    public boolean isPrimitif(String type){
		switch (type) {
			case "int": return true;
			case "double": return true;
			case "float": return true;
			case "Date" : return true;
			case "String" :return true;
		}
		return false;
	}
    public String formatFloatSign(float nombre){
    	DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
    	format.setPositivePrefix("+");
    	format.setMaximumFractionDigits(2);
    	return format.format(nombre);
    }
    public String formatFloat(float nombre){
    	NumberFormat format = NumberFormat.getInstance();
    	format.setMaximumFractionDigits(2);
    	return format.format(nombre);
    }
}

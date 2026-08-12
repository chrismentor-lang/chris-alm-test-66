import java.text.*;

		try{
			if(${TargetElementType}.equals("S")){
				return _input1;
			};
			NumberFormat formatter = NumberFormat.getInstance(Locale.${NumericLocale});
			return formatter.parse(_input1);

		}
		catch(Exception e){
			return _input1;
		}
